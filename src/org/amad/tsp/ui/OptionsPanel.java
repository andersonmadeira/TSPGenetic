package org.amad.tsp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OptionsPanel extends JPanel {
	private TSPViewer targetViewer;
	
	public OptionsPanel(TSPViewer viewer) {
		// TODO Auto-generated constructor stub
		targetViewer = viewer;
		
		add(new JButton("Start"));
		add(new JButton("Pause"));
		add(new JButton("Stop"));
		JButton newButton = new JButton("New Simulation");
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				targetViewer.generateNewSimulation();
			}
		});
		add(newButton);
		JButton aboutButton = new JButton("About");
		aboutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, 
						"Anderson de Oliveira Madeira, 2014\nhttp://andersonmadeira.github.io/",  
						"About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		add(aboutButton);
	}
}
