package org.alessandra;

public class Grid {

    public static final int GRID_SIZE = 20;
    public static final int CELL_SIZE = 50;

    private final int width = GRID_SIZE;
    private final int height = GRID_SIZE;
    private final Node[][] nodes;

    public Grid() {
        nodes = new Node[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                nodes[x][y] = new Node(x, y, true);
            }
        }
    }

    public Node getNode(int x, int y) {
        return nodes[x][y];
    }

    public void setObstacle(int x, int y) {
        nodes[x][y].walkable = false;
    }

    public boolean isInsideGrid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public void resetNodes() {
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                Node n = nodes[x][y];
                n.gCost = 0;
                n.hCost = 0;
                n.fCost = 0;
                n.parent = null;
            }
        }
    }

    public void clearObstacles() {
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                nodes[x][y].walkable = true;
            }
        }
    }
}