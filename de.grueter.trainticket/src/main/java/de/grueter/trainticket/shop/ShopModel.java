package de.grueter.trainticket.shop;

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
		initProducts();
		initBasket();
	}
	
	private void initProducts() {
		// init product-headings
        String productHeadings[] = new String[] {
            "Id", "Name", "Preis", "Gültigkeit"
        };
         
        // init products
        Object productArray[][] = new Object[][] {
            {1, "TICKET 2000", 40.0, "Monat" },
            {2, "BÄRENTICKET", 70.0, "Monat" },
            {3, "JUNGTICKET", 60.0, "Monat" },
            {4, "SCHOKOTICKET", 20.0, "Monat" }
        };
        
        productModel = new DefaultTableModel(productArray, productHeadings);
	}
	
	private void initBasket() {
		basketModel = new DefaultTableModel();
	}
	
	public DefaultTableModel getProductModel() {
		return productModel;
	}

	public DefaultTableModel getBasketModel() {
		return basketModel;
	}
}
