package com.github.michaeltp1.astar;

public class Main {


    public static void main(String[] args) {
    	//Execute the algorithm 10 times
        for (int i = 0; i < 10; i++) {
        	System.out.printf("------------------try %d------------------------------\n", i+1);
            new AStar().AStarAlgorithm();
            

        }

    }

}
