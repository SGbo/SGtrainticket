package de.grueter.trainticket;

public class TicketType {
	private String id;
	private String name;
	private float price;
	
	public TicketType(String id, String name, float price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}
}
