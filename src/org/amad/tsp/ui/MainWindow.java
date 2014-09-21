package org.amad.tsp.ui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

import org.amad.tsp.Simulation;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private Simulation sim = null;

	public MainWindow() {
		Container con = getContentPane();
		sim = new Simulation(); 
		sim.setLogger(new LoggerPanel());
		sim.setViewer(new TSPViewer(sim.getLogger()));
		
		con.add(new OptionsPanel(sim), BorderLayout.NORTH);
		con.add(sim.getViewer(), BorderLayout.CENTER);
        con.add(sim.getLogger(), BorderLayout.SOUTH);
        
        setTitle("The Salesman Problem - alpha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700, 600);
        setResizable(true);        
    }
}