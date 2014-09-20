package org.amad.tsp.ui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private TSPViewer viewer;
	private LoggerPanel logger;

	public MainWindow() {
		Container con = getContentPane();
		viewer = new TSPViewer(); 
		logger = new LoggerPanel();
		viewer.setLogger(logger);
		
		con.add(new OptionsPanel(viewer), BorderLayout.NORTH);
		con.add(viewer, BorderLayout.CENTER);
        con.add(logger, BorderLayout.SOUTH);
        
        setTitle("The Salesman Problem - alpha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700, 600);
        setResizable(true);
    }
}