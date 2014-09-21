package org.amad.tsp.algo;

/**
 * This class holds the Genetic Algorithm itself. Here we have three static methods
 * for the four main 'steps' of a GA: Evolve, Selection, Crossover, Mutation 
 * @author Anderson Madeira
 *
 */
public class Algorithm {

    /* Main Parameters */
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;

    /**
     * Evolves a population to the next generation 
     * @param pop
     * @return
     */
    public static Population evolve(Population pop) {
        Population newPopulation = new Population(pop.size());
 
        for (int i = 0; i < newPopulation.size(); i++) {
        	// SELECTION through 'Tournament Selection'
            Route parent1 = tournamentSelection(pop);
            Route parent2 = tournamentSelection(pop);
            // CROSSOVER
            Route child = crossover(parent1, parent2);
            newPopulation.storeRoute(i, child);
        }

        // MUTATE
        for (int i = 0; i < newPopulation.size(); i++) {
            mutate(newPopulation.getRoute(i));
        }

        return newPopulation;
    }

    /**
     * Crossover. Create the offspring.
     * @param parent1
     * @param parent2
     * @return
     */
    public static Route crossover(Route parent1, Route parent2) {
        // Create new child tour
        Route child = new Route();

        // Get start and end sub tour positions for parent1's route
        int start = (int) (Math.random() * parent1.size()),
        	end   = (int) (Math.random() * parent1.size());

        // Loop and add the sub tour from parent1 to our child
        for (int index = 0; index < child.size(); index++) {
            // If our start position is less than the end position
            if (start < end && index > start && index < end) {
                child.setCity(index, parent1.getCity(index));
            } // If our start position is larger
            else if (start > end) {
                if (!(index < start && index > end)) {
                    child.setCity(index, parent1.getCity(index));
                }
            }
        }

        // Loop through parent2's city tour
        for (int index = 0; index < parent2.size(); index++) {
            // If child doesn't have the city add it
            if (!child.containsCity(parent2.getCity(index))) {
                // Loop to find a spare position in the child's tour
                for (int ii = 0; ii < child.size(); ii++) {
                    // Spare position found, add city
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, parent2.getCity(index));
                        break;
                    }
                }
            }
        }
        return child;
    }

    /**
     * MUTATION - Through Swap Mutation
     * @param route
     */
    private static void mutate(Route route) {
        // Loop through tour cities
        for(int index = 0; index < route.size(); index++){
            // Apply mutation rate
            if(Math.random() < mutationRate){
                // Get a second random position in the route
                int index2 = (int) (route.size() * Math.random());

                // Get the cities at target position in tour
                City city1 = route.getCity(index);
                City city2 = route.getCity(index2);

                // Swap them around
                route.setCity(index2, city1);
                route.setCity(index, city2);
            }
        }
    }

    /**
     * Selects the best solutions (Fitness value) for crossover
     * @param pop
     * @return
     */
    private static Route tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize);
        // For each place in the tournament get a random candidate tour and
        // add it
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.storeRoute(i, pop.getRoute(randomId));
        }
        // Get the fittest tour
        Route fittest = tournament.getFittest();
        return fittest;
    }
}
