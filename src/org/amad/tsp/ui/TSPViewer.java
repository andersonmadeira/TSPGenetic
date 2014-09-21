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
import org.amad.tsp.algo.RoutesInfo;

@SuppressWarnings("serial")
public class TSPViewer extends JComponent {
	
	private BasicStroke defaultStroke;
	private LoggerPanel logger = null;
	
	public static final int DEFAULT_WIDTH = 700;
	public static final int DEFAULT_HEIGHT = 500;
	public static final int DEFAULT_CIRCLE_RADIUS = 30;
	
	public TSPViewer(LoggerPanel l) {
		this.logger = l;
		// TODO Auto-generated constructor stub
		defaultStroke = new BasicStroke(1, BasicStroke.CAP_ROUND,
	            BasicStroke.JOIN_BEVEL);
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        generateNewSimulation();
	}

	public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
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
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }
    
    public void generateNewSimulation() {
		// TODO Auto-generated method stub
    	// 1. Creates the cities, just the data
    	RoutesInfo.createCities(20);
    	if (logger != null) logger.clear();
    	// dump cities and distances to logger
    	logCities();
    	logDistances();
    	// repaint component
    	repaint();
	}
    
    private void drawCity(Graphics2D g2d, City c) {
		// TODO Auto-generated method stub
    	g2d.setColor(c.getColor());
    	g2d.fillOval(c.getX(), c.getY(), 
    			City.getDefaultRadius(), City.getDefaultRadius());
    	g2d.setColor(Color.white);
    	
    	g2d.drawString(String.valueOf(c.getSymbol()), c.getCenterX()-5, c.getCenterY()+5);
	}
    
    /**
     * Log cities to logger for debug
     */
    private void logCities() {
		// TODO Auto-generated method stub
    	if (logger == null) return;
        
        for(int i = 0; i < RoutesInfo.cityCount(); i++)
        	logger.pushText("City "+RoutesInfo.getCity(i).getId()+": "+
        					RoutesInfo.getCity(i).toString()+"\n");
	}
    
    /**
     * Log distances between cities for debug
     */
    public void logDistances() {
    	if (logger == null) return;
		ArrayList<City> copyList = (ArrayList<City>) RoutesInfo.getArrayOfCities();
    	City c = null;
    	
    	logger.pushText(" =>Distances:\n");
    	
    	while(!copyList.isEmpty()) {
    		c = copyList.remove(0);
    		
    		for(int j = 0; j < copyList.size(); j++) {
    			
    			logger.pushText(" from "+c+"("+c.getId()+
    							") to "+copyList.get(j)+"("+copyList.get(j).getId()+"): "+
    							(int)c.distanceTo(copyList.get(j))+"\n");
    		}
    	}
    }
    
    /** 
     * Sets the logger panel associated with this simulation
     * @param logger
     */
    public void setLogger(LoggerPanel logger) {
		this.logger = logger;
	}
    
    /**
     * Gets the logger
     * @return
     */
    public LoggerPanel getLogger() {
		return logger;
	}
}