package de.grueter.trainticket;

public class Main {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainWindow mainWindow = new MainWindow();
			}
		});
	}
}
