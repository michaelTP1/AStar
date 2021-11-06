package com.github.michaeltp1.astar;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStar {

	Node[][] grid;
	Node start, goal;

	/**
	 * Default constructor, create a grid of nodes of random size between 2x2 and
	 * 20x20 and set start and goal randomly
	 */
	public AStar() {
		int x = (int) (Math.random() * 18) + 2;
		int y = (int) (Math.random() * 18) + 2;
		setGrid(new Node[x][y]);

		// initialize nodes of the grid, set traversable randomly
		for (int i = 0; i < getGrid().length; i++) {
			for (int j = 0; j < getGrid()[0].length; j++) {
				getGrid()[i][j] = new Node(i, j, Math.random() < 0.8);
			}
		}
		do {
			setStart(getGrid()[(int) (Math.random() * getGrid().length)][(int) (Math.random() * getGrid()[0].length)]);
			setGoal(getGrid()[(int) (Math.random() * getGrid().length)][(int) (Math.random() * getGrid()[0].length)]);
		} while (!getStart().isTraversable() || !getGoal().isTraversable() || getStart().equals(getGoal()));

	}

	// A* finds a path from start to goal.
	// h is the heuristic function. h(n) estimates the cost to reach goal from node
	// n.
	// The implementation uses a custom A* search algorithm.
	void AStarAlgorithm() {
		// create a open and closed list
		PriorityQueue<Node> openSet = new PriorityQueue<Node>();
		List<Node> closedSet = new ArrayList<Node>();

		// initialize the start node
		start.setG(0);
		start.setH(heuristic(start, goal));
		start.setF(start.getG() + start.getH());
		openSet.add(start);
		start.setVisited(true);
		start.setPath(true);
		goal.setPath(true);
		Node current;
		printGrid();
		// while the open set is not empty
		while (!openSet.isEmpty()) {
			// get the node with the lowest f value
			current = openSet.poll();
			current.setVisited(true);
			// and add it to the closed set
			closedSet.add(current);
			if (current.equals(goal)) {
				reconstructPath(current);
				return;

			}

			// for each of the current node's neighbors
			for (Node neighbor : getNeighbors(current, grid)) {
				if (neighbor.isTraversable()) {
					if (!closedSet.contains(neighbor)) {
						// if the neighbor is not in the open set
						if (!openSet.contains(neighbor) || neighbor.getG() < current.getG()) {
							// set the neighbor's parent to the current node
							neighbor.setParent(current);
							// calculate the neighbor's g and h values
							neighbor.setG(current.getG() + 1);
							neighbor.setH(heuristic(neighbor, goal));
							neighbor.setF(neighbor.getG() + neighbor.getH());
							// add the neighbor to the open set
							openSet.add(neighbor);
							neighbor.setVisited(true);
						
						}
					} else {
						// if the neighbor is in the open set, but the current path is shorter
						if (current.getG() + 1 < neighbor.getG()) {
							// set the neighbor's parent to the current node
							neighbor.setParent(current);
							// and recalculate the g and f values
							neighbor.setG(current.getG() + 1);
							neighbor.setF(neighbor.getG() + neighbor.getH());
						}

					}
				
				}
			}

		}

		System.out.println("No path found");

	}

	private void reconstructPath(Node current) {
		while (current.getParent() != null) {
			current.setPath(true);
			current = current.getParent();
		}
		printGrid();
	}

	private void printGrid() {
		// print @ if traversable and path
		// print . if traversable and visited
		// print + if traversable and not visited
		// print # if not traversable
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].isTraversable()) {
					if (grid[i][j].isPath()) {
						System.out.print("@");
					} else if (grid[i][j].isVisited()) {
						System.out.print(".");
					} else {
						System.out.print("+");
					}
				} else {
					System.out.print("#");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	List<Node> getNeighbors(Node current, Node[][] grid) {
		List<Node> neighbors = new ArrayList<Node>();
		int x = current.getX();
		int y = current.getY();
		if (x > 0) {
			neighbors.add(grid[x - 1][y]);
		}
		if (x < grid.length - 1) {
			neighbors.add(grid[x + 1][y]);
		}
		if (y > 0) {
			neighbors.add(grid[x][y - 1]);
		}
		if (y < grid[0].length - 1) {
			neighbors.add(grid[x][y + 1]);
		}
		return neighbors;
	}

	public Node[][] getGrid() {
		return grid;
	}

	public void setGrid(Node[][] grid) {
		this.grid = grid;
	}

	public Node getStart() {
		return start;
	}

	public void setStart(Node start) {
		this.start = start;
	}

	public Node getGoal() {
		return goal;
	}

	public void setGoal(Node goal) {
		this.goal = goal;
	}

	// heuristic function
	public int heuristic(Node n, Node goal) {
		return Math.abs(n.getX() - goal.getX()) + Math.abs(n.getY() - goal.getY());
	}

}
