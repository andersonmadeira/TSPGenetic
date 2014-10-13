# TSPGenetic

#### Introduction:

This application finds solution paths to The [*Travelling Salesman Problem*](http://en.wikipedia.org/wiki/Travelling_salesman_problem) using [*Genetic Algorithm*](http://en.wikipedia.org/wiki/Genetic_algorithm). 

#### Overview:

A brief overview of this implementation:

* Java2D and Swing for drawing, controls, general UI.
* A Genetic Algorithm to evolve populations of solutions untill we find a near-optimal solution.
* Three main compoments: A Viewer (draws the graph), Logger (debug info), ControlPanel (Buttons for controlling our simulation)

#### Controls/Interaction:

Main buttons:

- Evolve: evolve the current population to 10 generations ahead.
- Next Generation: evolve to next generation.
- New Simulation: create a blank simulation(graph) with a random new population of solutions.
- About: self-explanatory

#### Logging:

Watch for the logger down in the window.

#### References:

This project is strongly based (main GA idea; selection, mutation and crossover operators) on this great tutorial by [*The Project Spot*](http://www.theprojectspot.com/tutorial-post/applying-a-genetic-algorithm-to-the-travelling-salesman-problem/5). Please refer to his tutorial for a brief explanation.

**Pull requests / ideas / suggestions are always welcome :D**
