package org.amad.tsp;

import javax.swing.SwingUtilities;
import org.amad.tsp.ui.MainWindow;

public class Main {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                MainWindow win = new MainWindow();
                win.setVisible(true);
            	//new FixedPane();
            }
        });
    }
}
