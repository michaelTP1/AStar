package com.github.michaeltp1.astar;

//node class with parent node
class Node implements Comparable<Node> {
    private final int x;
    private final int y;
    private double g;
    private double h;
    private double f;
    private Node parent;
    private boolean traversable;
    private boolean visited;

    private boolean path;

    public Node(int x, int y, boolean traversable) {
        this.x = x;
        this.y = y;
        this.traversable = traversable;
        // initialize g to infinity
        this.g = Double.POSITIVE_INFINITY;
    }

    // getters and setters
    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isTraversable() {
        return traversable;
    }

    public void setTraversable(boolean traversable) {
        this.traversable = traversable;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isPath() {
        return path;
    }

    public void setPath(boolean path) {
        this.path = path;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double cost(Node n) {
        if (n.getX() == x) {
            return 1;
        }
        if (n.getY() == y) {
            return 1;
        }
        return Math.sqrt(2);
    }

    public boolean equals(Object o) {
        if (o instanceof Node) {
            Node n = (Node) o;
            return n.getX() == x && n.getY() == y;
        }
        return false;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Node o) {
        if (null != o)
            return Double.compare(f, o.f);
        else
            return -1;

    }
}