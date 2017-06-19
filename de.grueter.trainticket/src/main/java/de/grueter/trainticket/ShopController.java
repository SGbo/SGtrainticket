package de.grueter.trainticket;

public class ShopController {
	private ShopModel shopModel;
	private ShopView shopView;

	public ShopController(ShopModel shopModel, ShopView shopView) {
		this.shopModel = shopModel;
		this.shopView = shopView;
	}

	public void addProductToBasket(String product, String price) {
		shopModel.addProductToBasket(product, price);
	}

	public void removeProductFromBasket(int row) {
		try {
			shopModel.removeProductFromBasket(row);
		} catch (ArrayIndexOutOfBoundsException e) {
			shopView.showErrorMessage("Index out of bounds!");
		}
	}
	
	public void buyBasketItems() {
		shopModel.buyBasketItems();
	}
}
