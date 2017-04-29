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
		initTableModels();
	}
	
	private void initTableModels() {
		/* PRODUCTS */
        String productHeadings[] = new String[] {
            "Produkt", "Preis"
        };		
       	
        Object productArray[][] = new Object[][] {
            { "Einzelticket Kurzstrecke",	"1.60 €" },
            { "EinzelTicket A",				"2.70 €" },
            { "EinzelTicket B", 			"5.80 €" },
            { "EinzelTicket C", 			"12.10 €" },
            { "EinzelTicket D", 			"15.00 €" },
            { "TagesTicket A", 				"6.80 €" },
            { "TagesTicket B", 				"13.90 €" },
            { "TagesTicket C", 				"23.60 €" },
            { "TagesTicket D", 				"28.40 €" }
        };
        
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
}
