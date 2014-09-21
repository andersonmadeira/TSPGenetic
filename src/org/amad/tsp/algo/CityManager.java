package org.amad.tsp.algo;

import java.util.ArrayList;

import org.amad.tsp.ui.TSPViewer;

/**
 * Manages the cities, holds cities info
 * @author anderson
 *
 */
public class CityManager {
    // cities
    private static ArrayList<City> cities = null;
    
    public static void createCities(int cityCount) {
    	cities = new ArrayList<City>();
    	City.resetCityCount();
    	
    	for(int i = 0; i < cityCount; i++) {
        	add(new City((int) (Math.random() * (TSPViewer.DEFAULT_WIDTH - 100)), 
					(int) (Math.random() * (TSPViewer.DEFAULT_HEIGHT - 100)),
					String.valueOf((char) (i+65))));
        }
    }
    
    public static City get(int i) {
    	return cities.get(i);
	}
    
    public static void add(City c) {
    	cities.add(c);
    }
    
    public static int getTotal() {
    	return cities.size();
    }
    
    /**
     * @return clone of the underlying ArrayList of cities
     */
	public static ArrayList<City> getArrayOfCities() {
    	return (ArrayList<City>) cities.clone();
    }
}