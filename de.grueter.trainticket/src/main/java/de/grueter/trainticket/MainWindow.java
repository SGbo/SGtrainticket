package de.grueter.trainticket;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 7679547344531585088L;
	//data
	private TicketController ticketController;
	private TicketModel ticketModel;
	private String[] columns;
	//widgets
	private JPanel panel;
	private JPanel categoryPanel;
	private JMenuBar menuBar;
	private JToolBar leftToolbar;
	private JTable table;
	private Object[][] data;
	
	public MainWindow() {
		ticketModel = TicketModel.getInstance();		
		ticketController = new TicketController(ticketModel, this);
		
        initData();
        initGui();
    }
	
	private void initData() {
		//headers for the table
        columns = new String[] {
            "Id", "Name", "Preis", "Gültigkeit"
        };
         
        //actual data for the table in a 2d array
        data = new Object[][] {
            {1, "TICKET 2000", 40.0, "Monat" },
            {2, "BÄRENTICKET", 70.0, "Monat" },
            {3, "JUNGTICKET", 60.0, "Monat" },
            {4, "SCHOKOTICKET", 20.0, "Monat" }
        };
	}
	
	private void initMenuBar() {
		menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem closeItem = new JMenuItem("Close");
		closeItem.addActionListener(al -> {
			System.exit(0);
		});
		fileMenu.add(closeItem);
		menuBar.add(fileMenu);
		
		// add menubar
		setJMenuBar(menuBar);
	}
	
	private JPanel createShopPanel() {
		panel = new JPanel();
		
		GridBagLayout layout = new GridBagLayout();	
		panel.setLayout(layout);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		JTable ticketTable = new JTable(data, columns) {
			private static final long serialVersionUID = 7848124144129998978L;

			public boolean isCellEditable(int row, int column) {                
                return false;
			}
        };
		JScrollPane scrollpane = new JScrollPane(ticketTable);
		panel.add(scrollpane, c);
		
		c.gridx = 1;
		c.gridy = 1;
		JButton buyTicketButton = new JButton("Ticket kaufen");
		panel.add(buyTicketButton, c);
		
		return panel;
	}
	
//	private void initLeftToolbar() {
//		leftToolbar = new JToolBar("MainMenu", JToolBar.VERTICAL);
//		leftToolbar.setSize(300, getHeight());
//		leftToolbar.setLayout(new GridBagLayout());
//		leftToolbar.setFloatable(false);
//		
//		GridBagConstraints c = new GridBagConstraints();
//		c.fill = GridBagConstraints.HORIZONTAL;
//		
//		JButton button;
//		button = new JButton("Home");
//		leftToolbar.add(button, c);
//		
//		button = new JButton("Test");
//		c.gridy = 1;
//		leftToolbar.add(button, c);
//	}
	
	private void initGui() {
		// setup layout
        getContentPane().setLayout(new BorderLayout());
                
        initMenuBar();
//        initLeftToolbar();
        
        JTabbedPane tabPane = new JTabbedPane();
        tabPane.addTab("Ticket Buchen", createShopPanel());
        tabPane.addTab("Verkaufte Tickets pro Monat", new JPanel());
        tabPane.addTab("Verkaufte Tickets nach Kategorie", new JPanel());
        
//        JTable table = new JTable(data, columns);
        
//        add(leftToolbar, BorderLayout.LINE_START);
        add(tabPane, BorderLayout.CENTER);
        
        setTitle("TrainTicket"); // title of window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit window when close is pressed
//        pack(); // resize window to content
        setSize(800, 600);
        setLocationRelativeTo(null); // center window
        setVisible(true);
	}
}
