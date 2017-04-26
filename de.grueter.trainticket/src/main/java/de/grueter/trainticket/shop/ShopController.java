package de.grueter.trainticket.shop;

public class ShopController {
	ShopModel shopModel;
	ShopView shopView;
	
	public ShopController(ShopModel shopModel, ShopView shopView) {
		this.shopModel = shopModel;
		this.shopView = shopView;
	}
}
