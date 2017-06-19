package de.grueter.trainticket;

import java.awt.Color;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ShopView extends JPanel {
	private static final long serialVersionUID = 8924702937949509342L;

	private ShopController shopController;
	private ShopModel shopModel;

	private JLabel sumLabel;
	private JButton moveToBasketButton;
	private JButton removeButton;
	private JButton payButton;
	private JTable productTable;
	private JTable basketTable;

	public ShopView() {
		shopModel = ShopModel.getInstance();
		shopController = new ShopController(shopModel, this);

		init();
	}

	void init() {
		setLayout(null);

		/* PRODUCT TABLE */
		productTable = createProductTable();
		productTable.getSelectionModel().addListSelectionListener(lse -> {
			ListSelectionModel model = (ListSelectionModel) lse.getSource();

			/* disable buy-button when nothing is selected */
			moveToBasketButton.setEnabled(!model.isSelectionEmpty());
		});
		JScrollPane productScrollPane = new JScrollPane(productTable);
		add(productScrollPane);

		/* BASKET TABLE */
		basketTable = createBasketTable();
		basketTable.getSelectionModel().addListSelectionListener(lse -> {
			ListSelectionModel model = (ListSelectionModel) lse.getSource();

			/* disable remove-button when nothing is selected */
			removeButton.setEnabled(!model.isSelectionEmpty());
		});
		JScrollPane basketScrollPane = new JScrollPane(basketTable);
		add(basketScrollPane);

		/* BUY BUTTON */
		moveToBasketButton = createBuyButton();
		moveToBasketButton.addActionListener(ae -> {
			String product = (String) productTable.getValueAt(productTable.getSelectedRow(), 0);
			String price = (String) productTable.getValueAt(productTable.getSelectedRow(), 1);

			shopController.addProductToBasket(product, price);
		});
		add(moveToBasketButton);

		/* DELETE BUTTON */
		removeButton = createRemoveButton();
		removeButton.setEnabled(false);
		removeButton.addActionListener(ae -> {
			int index = basketTable.getSelectedRow();
			shopController.removeProductFromBasket(index);
		});
		add(removeButton);
		
		/* PAY BUTTON */
		payButton = new JButton("Jetzt Bezahlen");
		payButton.addActionListener(ae -> {
			shopController.buyBasketItems();
		});
		add(payButton);

		/* SUM LABEL */
		sumLabel = createSumLabel();
		add(sumLabel);

		productScrollPane.setBounds(10, 10, 400, 400);
		basketScrollPane.setBounds(420, 10, 370, 340);
		moveToBasketButton.setBounds(210, 420, 200, 50);
		removeButton.setBounds(420, 360, 200, 50);
		sumLabel.setBounds(630, 360, 160, 50);
		payButton.setBounds(420, 420, 200, 50);

		recalcSum(shopModel.getBasketModel());
	}

	private JTable createProductTable() {
		/* PRODUCT TABLE */
		JTable productTable = new JTable(shopModel.getProductModel()) {
			private static final long serialVersionUID = 7848124144129998978L;

			// don't allow to edit cells
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// allow single-row-selection only
		productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		productTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		productTable.setRowHeight(30);

		// Compare prices (e.g. "12.00 €")
		Comparator<String> priceComparator = new Comparator<String>() {
			public int compare(String s1, String s2) {
				try {
					double price1 = Double.parseDouble(s1.split(" ")[0]);
					double price2 = Double.parseDouble(s2.split(" ")[0]);
					return (int) (price1 - price2);
				} catch (Exception e) {
					return 0; // default (both values are the same)
				}
			}
		};

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(productTable.getModel());
		sorter.setComparator(1, priceComparator);
		productTable.setRowSorter(sorter);

		return productTable;
	}

	private JTable createBasketTable() {
		/* BASKET TABLE */
		JTable basketTable = new JTable(shopModel.getBasketModel()) {
			private static final long serialVersionUID = 7848124144129998978L;

			/* don't allow to edit cells */
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		/* allow single-row-selection only */
		basketTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		basketTable.setRowHeight(30);
		basketTable.getModel().addTableModelListener(tme -> {
			onBasketModelEvent(tme);
		});

		return basketTable;
	}

	private JButton createBuyButton() {
		JButton buyButton = new JButton("In den Warenkorb");
		buyButton.setEnabled(false);
		return buyButton;
	}

	private JButton createRemoveButton() {
		JButton deleteButton = new JButton("Entfernen");
		return deleteButton;
	}

	private JLabel createSumLabel() {
		/* SUM LABEL */
		JLabel sumLabel = new JLabel("Summe: ");
		sumLabel.setBackground(new Color(84, 255, 159));
		sumLabel.setOpaque(true);
		sumLabel.setHorizontalAlignment(JLabel.CENTER);

		return sumLabel;
	}

	private void onBasketModelEvent(TableModelEvent tme) {
		int column = tme.getColumn();
		TableModel model = (TableModel) tme.getSource();

		if (column == 0) {
			/* count column has changed! */
			recalcSum(model);
		} else if (tme.getType() == TableModelEvent.INSERT) {
			/* new row has been added */
			recalcSum(model);
		} else if (tme.getType() == TableModelEvent.DELETE) {
			/* row has been deleted */
			recalcSum(model);
		}
	}

	private void recalcSum(TableModel model) {
		double sum = 0.0;
		int countSum = 0;
		for (int i = 0; i < model.getRowCount(); i++) {
			int count = (int) model.getValueAt(i, 0);
			countSum += count;
			String priceText = (String) model.getValueAt(i, 2);
			double price = Double.parseDouble(priceText.split(" ")[0]);
			sum += count * price;
		}
		sumLabel.setText("<html>" + "<body>Summe (" + countSum + " Artikel):" + "<br>" + String.format("%.2f", sum)
				+ " €" + "</body></html>");
	}

	public void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}
