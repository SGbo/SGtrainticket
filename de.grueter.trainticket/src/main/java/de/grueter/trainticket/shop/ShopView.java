package de.grueter.trainticket.shop;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class ShopView extends JPanel {
	private static final long serialVersionUID = 8924702937949509342L;
	private ShopController shopController;
	private ShopModel shopModel;
	
	public ShopView() {
		shopModel = ShopModel.getInstance();
		shopController = new ShopController(shopModel, this);
		
		init();
	}
	
	void init() {
		setLayout(null);
		
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

		JScrollPane productScrollPane = new JScrollPane(productTable);
		add(productScrollPane);
		
		/* BUY BUTTON*/
		JButton buyButton = new JButton("In den Warenkorb");
		add(buyButton);
		
		/* BASKET TABLE */
		JTable basketTable = new JTable(shopModel.getBasketModel()) {
			private static final long serialVersionUID = 7848124144129998978L;

			// don't allow to edit cells
			@Override
			public boolean isCellEditable(int row, int column) {                
				return false;
			}
		};
		// remove header
		basketTable.setTableHeader(null);
		// allow single-row-selection only
		basketTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane basketScrollPane = new JScrollPane(basketTable);
		basketScrollPane.setBounds(10, 10, 400, 400);
		add(basketScrollPane);
		
		productScrollPane.setBounds(10, 10, 400, 400);
		basketScrollPane.setBounds(420, 10, 200, 400);
		buyButton.setBounds(210, 420, 200, 50);
	}
}
