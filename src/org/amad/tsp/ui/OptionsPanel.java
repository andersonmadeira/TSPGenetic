package org.amad.tsp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.amad.tsp.Simulation;

@SuppressWarnings("serial")
public class OptionsPanel extends JPanel {
	private TSPViewer targetViewer;
	private Simulation simulation = null;
	
	public OptionsPanel(Simulation sim) {
		// TODO Auto-generated constructor stub
		this.simulation = sim;
		
		JButton startButton = new JButton("Evolve");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				simulation.run(25);
			}
		});
		add(startButton);
		add(new JButton("Pause"));
		add(new JButton("Stop"));
		JButton newButton = new JButton("New Simulation");
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				simulation.reset();
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
