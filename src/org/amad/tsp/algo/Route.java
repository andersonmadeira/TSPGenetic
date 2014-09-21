package org.amad.tsp.algo;

import java.util.ArrayList;
import java.util.Collections;

public class Route {
	// Cities of this route
    private ArrayList<City> route = new ArrayList<City>();
    private double fitness = 0;
    private int distance = 0;
    
    /**
     * Default constructor
     */
    public Route() {
        for (int i = 0; i < CityManager.getTotal(); i++)
            route.add(null);
    }
    
    // Creates a random route
    public void buildRandomRoute() {
        // add all cities to this route
        for (int i = 0; i < CityManager.getTotal(); i++) {
          setCity(i, CityManager.get(i));
        }
        // mix
        Collections.shuffle(route);
    }
    
    public void setCity(int i, City c) {
    	route.set(i, c);
    	fitness = 0;
    	distance = 0;
	}
    
    public City getCity(int index) {
        return (City) route.get(index);
    }
    
    // gets the 'fitness' of solution (route)
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/(double)getTotalDistance();
        }
        return fitness;
    }
    
    /**
     * Check if c is in this route.
     * @param c
     * @return
     */
    public boolean containsCity(City c) {
    	return route.contains(c);
	}
    
    /** Gets the distance of this route, first element is the origin, and last is the destination city
     * 
     * @return distance between first and last city.
     */
    public int getTotalDistance() {
    	// only calculates the distance if we dont have it yet.
        if (distance == 0) {
            int routeDistance = 0;

            for (int index = 0; index < this.size(); index++) {
                // The origin and destination
                City c1 = getCity(index), c2;
                // if this is the last city, lets get the distance back to the first city
                if(index+1 < this.size())
                    c2 = getCity(index+1);
                else
                    c2 = getCity(0);
                routeDistance += c1.distanceTo(c2);
            }
            
            distance = routeDistance;
        }
        
        return distance;
    }
    
    public int size() {
    	return route.size();
	}
    
    @Override
    public String toString() {
        String gene = "|";
        int totalCities = this.size(), i = 0;
        
        while (i < totalCities) {
            gene += getCity(i); i++;
            if (i < totalCities) gene += "|";
        }
        
        return gene;
    }
}
