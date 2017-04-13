package de.grueter.trainticket;

public class TicketController {
	TicketModel model;
	MainWindow view;
	
	TicketController(TicketModel model, MainWindow view) {
		this.model = model;
		this.view = view;
	}
	
	void buyTicket(TicketType type, float payment) {
		//TODO check if payment is valid
		model.buyTicket(type, payment);
	}
}