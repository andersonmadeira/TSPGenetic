package org.amad.tsp.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * Used for simulation logs. Other classes, specially Simulation and TSPViewer use it
 * for debug and logging.
 * @author Anderson Madeira
 */
@SuppressWarnings("serial")
public class LoggerPanel extends JPanel {
	private JTextArea log = null;
	
	// only ui package needs this class
	public LoggerPanel() {
		Box box = Box.createVerticalBox();
		box.setAlignmentX(SwingConstants.LEFT);
		
		log = new JTextArea("Simulation Log:\n");
        log.setEditable(false);
		log.setRows(4);
		log.setColumns(44);
		log.setFont(new Font("Monospace", Font.PLAIN, 15));
		log.setSize(new Dimension(400, 10));
		log.setMargin(new Insets(5, 5, 5, 5));
        JScrollPane scroll = new JScrollPane(log, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        										JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        box.add(scroll, BorderLayout.SOUTH);
        add(box);
	}
	
	public void pushText(String text) {
		log.append(text);
	}
	
	public void clear() {
		log.setText("Simulation log:\n");
	}
}