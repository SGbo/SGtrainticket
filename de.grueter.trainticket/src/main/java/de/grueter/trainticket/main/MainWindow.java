package de.grueter.trainticket.main;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import de.grueter.trainticket.shop.ShopView;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 7679547344531585088L;
	private JMenuBar menuBar;
	ShopView shopView;
	
	public MainWindow() {
		shopView = new ShopView();
		
        initGui();
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
	
	private void initGui() {
		// setup layout
        getContentPane().setLayout(new BorderLayout());
                
        initMenuBar();
               
        add(shopView, BorderLayout.CENTER);
        
        setTitle("TrainTicket"); // title of window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit window when close is pressed
        setSize(800, 600);
        setLocationRelativeTo(null); // center window
        setVisible(true);
	}
}
