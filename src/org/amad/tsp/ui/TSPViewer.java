package org.amad.tsp.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JComponent;

import org.amad.tsp.algo.City;
import org.amad.tsp.algo.Population;
import org.amad.tsp.algo.Route;
import org.amad.tsp.algo.RoutesInfo;

@SuppressWarnings("serial")
public class TSPViewer extends JComponent {
	
	private BasicStroke defaultStroke;
	
	public static final int DEFAULT_WIDTH = 700;
	public static final int DEFAULT_HEIGHT = 500;
	public static final int DEFAULT_CIRCLE_RADIUS = 30;
	
	private Graphics2D g2d = null;
	private RenderingHints rh = null;
	private Route best = null;
	
	/**
	 * WARNING: Before creating a viewer you must have a Simulation set (Instance) 
	 * @param l
	 */
	public TSPViewer(LoggerPanel l) {
		// TODO Auto-generated constructor stub
		defaultStroke = new BasicStroke(1, BasicStroke.CAP_ROUND,
	            BasicStroke.JOIN_BEVEL);
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	public void render(Graphics g) {

        g2d = (Graphics2D) g;
        
        rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);
        g2d.setStroke(defaultStroke);
        
        for(int i = 0; i < RoutesInfo.cityCount(); i++) {
        	City s = RoutesInfo.getCity(i);
        	for(int j = 0; j < 10; j++) {
        		if (i == j) break;
        		City d = RoutesInfo.getCity(j);
        		g2d.setColor(Color.GRAY);
        		g2d.drawLine(s.getCenterX(), s.getCenterY(), 
        					 d.getCenterX(), d.getCenterY());
        	}
        }
        
        g2d.setFont(new Font("Monospace", Font.BOLD, 15));
        for(int i = 0; i < RoutesInfo.cityCount(); i++) {
        	drawCity(g2d, RoutesInfo.getCity(i));
        }
        
        if (best == null) return;
        
        City c1 = null, c2 = null;
        
        g2d.setStroke(defaultStroke);
        
    	for (int index = 0; index < best.size()-1; index++) {
			c1 = best.getCity(index);
			c2 = best.getCity(index+1);
			g2d.setColor(Color.red);
    		g2d.drawLine(c1.getCenterX(), c1.getCenterY(), 
    					 c2.getCenterX(), c2.getCenterY());
		}
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }
    
    public void setBestSolution(Route route) {
    	best = route;
    }

    private void drawCity(Graphics2D g2d, City c) {
		// TODO Auto-generated method stub
    	g2d.setColor(c.getColor());
    	g2d.fillOval(c.getX(), c.getY(), 
    			City.getDefaultRadius(), City.getDefaultRadius());
    	g2d.setColor(Color.white);
    	
    	g2d.drawString(String.valueOf(c.getSymbol()), c.getCenterX()-5, c.getCenterY()+5);
	}
}