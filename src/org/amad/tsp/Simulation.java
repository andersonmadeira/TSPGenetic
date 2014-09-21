package org.amad.tsp;

import java.util.ArrayList;

import org.amad.tsp.algo.Algorithm;
import org.amad.tsp.algo.City;
import org.amad.tsp.algo.Population;
import org.amad.tsp.algo.Route;
import org.amad.tsp.algo.RoutesInfo;
import org.amad.tsp.ui.LoggerPanel;
import org.amad.tsp.ui.TSPViewer;

public class Simulation {
	private Population pop = null;
	private LoggerPanel logger = null;
	private TSPViewer viewer = null;
	
	public Simulation() {
		// TODO Auto-generated constructor stub
		reset();
	}
	
	public void reset() {
		// TODO Auto-generated method stub
		// 1. Creates the cities, just the data
    	RoutesInfo.createCities(20);
    	// 2. Create the first population with random solutions 
    	pop = new Population(50);
    	pop.generateRandom();
    	// 3. Redraw stuff
    	if (viewer != null) {
    		viewer.setBestSolution(null);
    		viewer.repaint();
    	}
	}
	
	public void run(int generations) {
		// TODO Auto-generated method stub
		Route best = null;
		
		logger.pushText("+Logging best Solutions:\n");
		
		for (int i = 1; i <= generations; i++) {
			step();
			best = getBestSolution();
			logger.pushText(" "+i+": "+best+"\n");
		}
		
		viewer.setBestSolution(best);
		viewer.repaint();
	}
	
	/**
	 * Step simulation - i.e. evolve population
	 */
	private void step() {
		// TODO Auto-generated method stub
		pop = Algorithm.evolve(pop);
	}
	
	/**
	 * 
	 * @return The best solution from the current population
	 */
	public Route getBestSolution() {
		// TODO Auto-generated method stub
		return pop.getFittest();
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
    
    /**
     * Sets the viewer
     * @param viewer
     */
    public void setViewer(TSPViewer viewer) {
		this.viewer = viewer;
	}
    
    /**
     * Gets the viewer
     * @return
     */
    public TSPViewer getViewer() {
		return viewer;
	}
}