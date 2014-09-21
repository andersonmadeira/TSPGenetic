package org.amad.tsp;

import java.util.ArrayList;

import org.amad.tsp.algo.Algorithm;
import org.amad.tsp.algo.City;
import org.amad.tsp.algo.Population;
import org.amad.tsp.algo.Route;
import org.amad.tsp.algo.CityManager;
import org.amad.tsp.ui.LoggerPanel;
import org.amad.tsp.ui.TSPViewer;

/**
 * Holds all relevant information and state of the current simulation
 * @author Anderson Madeira
 */
public class Simulation {
	private Population pop = null;
	private LoggerPanel logger = null;
	private TSPViewer viewer = null;
	private int lastGen = 0;
	
	public Simulation() {
		reset();
	}
	
	public void reset() {
		// 1. Creates the cities, just the data
    	CityManager.createCities(20);
    	// 2. Create the first population with random solutions 
    	pop = new Population(50);
    	pop.generateRandom();
    	// 3. Set lastGen to 0, this is now the first, new individuals were born
    	lastGen = 0;
    	// 4. Redraw stuff
    	if (viewer != null) {
    		viewer.setBestSolution(null);
    		viewer.repaint();
    	}
    	
    	if (logger != null) {
    		logger.clear();
    	}
	}
	
	public void run(int generations) {
		Route best = null;
		
		// if we still have the first generation (random population)
		if (lastGen == 0) {
			logger.pushText("+First population created.\n");
    		logger.pushText("+Now logging best Solutions:\n");
		}
		
		for (int i = 1; i <= generations; i++) {
			lastGen += 1;
			step();
			best = getBestSolution();
			logger.pushText(" G["+(lastGen)+"]: D["+best.getTotalDistance()+
							"] F["+best.getFitness()+"] P["+best+"]\n");
			
		}
		
		viewer.setBestSolution(best);
		viewer.repaint();
	}
	
	/**
	 * Step simulation - i.e. evolve population
	 */
	private void step() {
		pop = Algorithm.evolve(pop);
	}
	
	/**
	 * 
	 * @return The best solution from the current population
	 */
	public Route getBestSolution() {
		return pop.getFittest();
	}

    
    /**
     * Log cities to logger for debug
     */
    @SuppressWarnings("unused")
	private void logCities() {
    	if (logger == null) return;
        
        for(int i = 0; i < CityManager.getTotal(); i++)
        	logger.pushText("City "+CityManager.get(i).getId()+": "+
        					CityManager.get(i).toString()+"\n");
	}
    
    /**
     * Log distances between cities for debug
     */
    public void logDistances() {
    	if (logger == null) return;
		ArrayList<City> copyList = (ArrayList<City>) CityManager.getArrayOfCities();
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
