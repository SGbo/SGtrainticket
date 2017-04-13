package de.grueter.trainticket;

import java.util.ArrayList;
import java.util.List;

public final class TicketModel {
	private static TicketModel instance;
	private List<TicketType> ticketTypeList = new ArrayList<TicketType>();
	private List<Ticket> boughtTicketList = new ArrayList<Ticket>();

	private TicketModel() {
		ticketTypeList.add(new TicketType("E_A", "Einzelticket A", 10.00f));
		ticketTypeList.add(new TicketType("E_B", "Einzelticket B", 20.00f));
		ticketTypeList.add(new TicketType("E_C", "EinzelTicket C", 30.00f));
		ticketTypeList.add(new TicketType("SWT", "Schönes Wochenende Ticket", 40.00f));
	}

	public static TicketModel getInstance() {
		if (instance == null) {
			instance = new TicketModel();
		}

		return instance;
	}

	public List<TicketType> getTicketTypeList() {
		return ticketTypeList;
	}

	public List<Ticket> getBoughtTicketList() {
		return boughtTicketList;
	}

	public float buyTicket(TicketType type, float payment) {
		if (payment >= type.getPrice()) {
			Ticket ticket = new Ticket(type);
			boughtTicketList.add(ticket);
		}

		return payment - type.getPrice();
	}
}
