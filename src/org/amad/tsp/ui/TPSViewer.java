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

@SuppressWarnings("serial")
class TSPViewer extends JComponent {
	
	private BasicStroke defaultStroke;
	private ArrayList<City> cities = null;
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
		
		Color[] colors = {
			new Color(125, 167, 116),
			new Color(42, 179, 231),
			new Color(70, 67, 123),
			new Color(130, 100, 84),
			new Color(252, 211, 61),
			new Color(241, 98, 69),
			new Color(217, 146, 54),
			new Color(63, 121, 186),
			new Color(31, 21, 1),
			Color.red
		};

        Graphics2D g2d = (Graphics2D) g;
        
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);
        g2d.setStroke(defaultStroke);
        
        for(int i = 0; i < 10; i++) {
        	City s = cities.get(i);
        	for(int j = 0; j < 10; j++) {
        		if (i == j) break;
        		City d = cities.get(j);
        		g2d.setColor(Color.GRAY);
        		g2d.drawLine(s.getCenterX(), s.getCenterY(), 
        					 d.getCenterX(), d.getCenterY());
        	}
        }
        
        g2d.setFont(new Font("Monospace", Font.BOLD, 15));
        for(int i = 0; i < 10; i++) {
        	g2d.setColor(colors[i]);
        	drawCity(g2d, cities.get(i));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }
    
    public void generateNewSimulation() {
		// TODO Auto-generated method stub
    	City.resetCityCount();
    	if (logger != null) logger.clear();
    	createNodes();
    	if (logger != null) logDistances();
    	repaint();
	}
    
    private void createNodes() {
		// TODO Auto-generated method stub
    	cities = new ArrayList<City>();
    	City new_c = null;
        
        for(int i = 0; i < 10; i++) {
        	new_c = new City((int) (Math.random() * (DEFAULT_WIDTH - 100)), 
        					   (int) (Math.random() * (DEFAULT_HEIGHT - 100)));
        	if (logger != null) {
        		logger.pushText("City "+new_c.getId()+": "+new_c.toString()+"\n");
        	}
        	cities.add(new_c);
        }
	}
    
    private void drawCity(Graphics2D g2d, City c) {
		// TODO Auto-generated method stub
    	g2d.fillOval(c.getX(), c.getY(), 
    			City.getDefaultRadius(), City.getDefaultRadius());
    	g2d.setColor(Color.white);
    	
    	g2d.drawString(String.valueOf(c.getId()), c.getCenterX()-5, c.getCenterY()+5);
	}
    
    public void logDistances() {
    	if (logger == null) return;
		ArrayList<City> copyList = (ArrayList<City>) cities.clone();
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
    
    public void setLogger(LoggerPanel logger) {
		this.logger = logger;
	}
    
    public LoggerPanel getLogger() {
		return logger;
	}
}