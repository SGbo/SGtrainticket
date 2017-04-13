package de.grueter.trainticket;

public class Ticket {
	private TicketType type;
	
	Ticket(TicketType type) {
		this.type = type;
	}
	
	public TicketType getType() {
		return type;
	}
}
