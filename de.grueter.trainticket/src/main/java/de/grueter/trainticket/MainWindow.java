package de.grueter.trainticket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 7679547344531585088L;
	private TicketController ticketController;
	private TicketModel ticketModel;

	public MainWindow() {
		ticketModel = TicketModel.getInstance();		
		ticketController = new TicketController(ticketModel, this);
		
		ticketController.buyTicket(ticketModel.getTicketTypeList().get(0), 100.0f);
		JOptionPane.showMessageDialog(this, ticketModel.getBoughtTicketList().get(0).getType().getName());
		
        //headers for the table
        String[] columns = new String[] {
            "Id", "Name", "Hourly Rate", "Part Time"
        };
         
        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
            {1, "John", 40.0, false },
            {2, "Rambo", 70.0, false },
            {3, "Zorro", 60.0, true },
        };
        //create table with data
        JTable table = new JTable(data, columns);
         
        //add the table to the frame
        this.add(new JScrollPane(table));
         
        this.setTitle("TrainTicket");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        this.pack();
        this.setVisible(true);
    }
}
