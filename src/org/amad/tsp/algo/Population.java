package org.amad.tsp.algo;

public class Population {
    private Route[] routes;

    /**
     * Empty population. Default constructor.
     * @param size
     */
    public Population(int size) {
        routes = new Route[size];
    }
    
    /**
     * Creates a route of random cities
     */
    public void generateRandom() {
        for (int i = 0; i < size(); i++) {
            Route r = new Route();
            r.buildRandomRoute();
            storeRoute(i, r);
        }
	}
    
    /**
     * Store route in this population
     * @param index
     * @param route
     */
    public void storeRoute(int index, Route route) {
        routes[index] = route;
    }
    
    /**
     * Returns a route from the population
     * @param index
     * @return
     */
    public Route getRoute(int index) {
        return routes[index];
    }
    
    /**
     * Gets the 'best' route from population
     * @return
     */
    public Route getFittest() {
        Route fittest = routes[0];
        
        // gets the individual with the highest Fitness value
        for (int i = 1; i < size(); i++)
            if (fittest.getFitness() <= getRoute(i).getFitness())
                fittest = getRoute(i);
        
        return fittest;
    }

    public int size() {
        return routes.length;
    }
}