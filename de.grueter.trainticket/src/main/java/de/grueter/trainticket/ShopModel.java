package de.grueter.trainticket;

import javax.swing.table.DefaultTableModel;

public final class ShopModel {
	private static ShopModel instance;
	private DefaultTableModel productModel;
	private DefaultTableModel basketModel;

	// singleton
	public static ShopModel getInstance() {
		if (instance == null) {
			instance = new ShopModel();
		}

		return instance;
	}

	// hidden constructor
	private ShopModel() {
		initTableModels();
	}

	private void initTableModels() {
		/* PRODUCTS */
		String productHeadings[] = new String[] { "Produkt", "Preis" };

		Object productArray[][] = new Object[][] { { "Einzelticket Kurzstrecke", "1.60 €" },
				{ "EinzelTicket A", "2.70 €" }, { "EinzelTicket B", "5.80 €" }, { "EinzelTicket C", "12.10 €" },
				{ "EinzelTicket D", "15.00 €" }, { "TagesTicket A", "6.80 €" }, { "TagesTicket B", "13.90 €" },
				{ "TagesTicket C", "23.60 €" }, { "TagesTicket D", "28.40 €" } };

		productModel = new DefaultTableModel(productArray, productHeadings);

		/* BASKET */
		basketModel = new DefaultTableModel();
		basketModel.addColumn("Anzahl");
		basketModel.addColumn("Produkt");
		basketModel.addColumn("Preis / Stück");
	}

	public DefaultTableModel getProductModel() {
		return productModel;
	}

	public DefaultTableModel getBasketModel() {
		return basketModel;
	}

	public void addProductToBasket(String product, String price) {
		/* product is already in basket? -> increase count of it */
		for (int i = 0; i < basketModel.getRowCount(); i++) {
			String iProduct = (String) basketModel.getValueAt(i, 1);
			if (iProduct == product) {
				int count = (int) basketModel.getValueAt(i, 0);
				basketModel.setValueAt(++count, i, 0);
				return;
			}
		}

		/* product was not found in basket! -> add new row for product */
		Object row[] = new Object[3];
		row[0] = 1;
		row[1] = product;
		row[2] = price;
		basketModel.addRow(row);
	}

	public void removeProductFromBasket(int row) throws ArrayIndexOutOfBoundsException {
		int count = (int) basketModel.getValueAt(row, 0);
		if (count > 1) {
			basketModel.setValueAt(--count, row, 0);
		} else {
			basketModel.removeRow(row);
		}
	}
	
	public void buyBasketItems() {
		basketModel.setRowCount(0);
	}
}
