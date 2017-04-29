package de.grueter.trainticket.shop;

import javax.swing.table.DefaultTableModel;

public class ShopController {
	ShopModel shopModel;
	ShopView shopView;

	public ShopController(ShopModel shopModel, ShopView shopView) {
		this.shopModel = shopModel;
		this.shopView = shopView;
	}

	public void addProductToBasket(String product, String price) {
		/* product is already in basket? -> increase count of it */
		for (int i = 0; i < shopModel.getBasketModel().getRowCount(); i++) {
			String iProduct = (String) shopModel.getBasketModel().getValueAt(i, 1);
			if (iProduct == product) {
				int count = (int) shopModel.getBasketModel().getValueAt(i, 0);
				shopModel.getBasketModel().setValueAt(++count, i, 0);
				return;
			}
		}

		/* product was not found in basket! -> add new row for product */
		Object row[] = new Object[3];
		row[0] = 1;
		row[1] = product;
		row[2] = price;
		shopModel.getBasketModel().addRow(row);
	}

	public void removeProductFromBasket(int row) {
		DefaultTableModel model = shopModel.getBasketModel();
		try {
			int count = (int) model.getValueAt(row, 0);
			if (count > 1) {
				model.setValueAt(--count, row, 0);
			} else {
				shopModel.getBasketModel().removeRow(row);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			shopView.showErrorMessage("Index out of bounds!");
		}
	}
}
