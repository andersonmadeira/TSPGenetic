package org.amad.tsp.algo;

import java.util.ArrayList;

import org.amad.tsp.ui.TSPViewer;

public class RoutesInfo {
    // cities
    private static ArrayList<City> cities = null;
    
    public static void createCities(int cityCount) {
    	cities = new ArrayList<City>();
    	City.resetCityCount();
    	
    	for(int i = 0; i < cityCount; i++) {
        	addCity(new City((int) (Math.random() * (TSPViewer.DEFAULT_WIDTH - 100)), 
					(int) (Math.random() * (TSPViewer.DEFAULT_HEIGHT - 100)),
					String.valueOf((char) (i+65))));
        }
    }
    
    public static City getCity(int i) {
		// TODO Auto-generated method stub
    	return cities.get(i);
	}
    
    public static void addCity(City c) {
    	cities.add(c);
    }
    
    public static int cityCount() {
    	return cities.size();
    }
    
    /**
     * @return clone of the underlying ArrayList of cities
     */
    public static ArrayList<City> getArrayOfCities() {
    	return (ArrayList<City>) cities.clone();
    }
}