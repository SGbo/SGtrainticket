package de.grueter.trainticket;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JToolBar;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 7679547344531585088L;
	//data
	private TicketController ticketController;
	private TicketModel ticketModel;
	private String[] columns;
	//widgets
	private JMenuBar menuBar;
	private JToolBar toolbar;
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
            "Id", "Name", "Hourly Rate", "Part Time"
        };
         
        //actual data for the table in a 2d array
        data = new Object[][] {
            {1, "John", 40.0, false },
            {2, "Rambo", 70.0, false },
            {3, "Zorro", 60.0, true },
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
	
	private void initLeftToolbar() {
		toolbar = new JToolBar("MainMenu", JToolBar.VERTICAL);
		toolbar.setSize(300, getHeight());
		toolbar.setLayout(new GridBagLayout());
		toolbar.setFloatable(false);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JButton button;
		button = new JButton("Home");
		toolbar.add(button, c);
		
		button = new JButton("Test");
		c.gridy = 1;
		toolbar.add(button, c);
		
		add(toolbar);
	}
	
	private void initGui() {
		// setup layout
        getContentPane().setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        initMenuBar();
        initLeftToolbar();
        
        JTable table = new JTable(data, columns);
        add(table);
        
//        JButton button1 = new JButton("Hello");
//        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
//        add(button1);
//        
//        JButton button2 = new JButton("World!");
//        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
//        add(button2);
        
        setTitle("TrainTicket"); // title of window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit window when close is pressed
        pack(); // resize window to content
        setLocationRelativeTo(null); // center window
        setVisible(true);
	}
}
