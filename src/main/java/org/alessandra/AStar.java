package org.alessandra;

import java.util.*;

public class AStar implements PathFinder {

    private int nodesExplored = 0;

    public List<Node> findPath(Grid grid, Node start, Node end) {
        nodesExplored = 0; // zera a cada nova execução

        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingDouble(n -> n.fCost));
        Set<Node> closedList = new HashSet<>();

        openList.add(start);

        while (!openList.isEmpty()) {
            Node current = openList.poll();

            if (current == end) {
                return reconstructPath(current);
            }

            closedList.add(current);
            nodesExplored++;

            for (Node neighbor : getNeighbors(grid, current)) {
                if (!neighbor.walkable || closedList.contains(neighbor)) {
                    continue;
                }

                double tentativeG = current.gCost + distance(current, neighbor);

                if (tentativeG < neighbor.gCost || !openList.contains(neighbor)) {
                    neighbor.gCost = tentativeG;
                    neighbor.hCost = distance(neighbor, end);
                    neighbor.fCost = neighbor.gCost + neighbor.hCost;
                    neighbor.parent = current;

                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public int getNodesExplored() {
        return nodesExplored;
    }

    private List<Node> getNeighbors(Grid grid, Node node) {
        List<Node> neighbors = new ArrayList<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                int newX = node.x + dx;
                int newY = node.y + dy;
                if (grid.isInsideGrid(newX, newY)) {
                    neighbors.add(grid.getNode(newX, newY));
                }
            }
        }
        return neighbors;
    }

    private double distance(Node a, Node b) {
        int dx = Math.abs(a.x - b.x);
        int dy = Math.abs(a.y - b.y);
        return (dx + dy) + (Math.sqrt(2) - 2) * Math.min(dx, dy);
    }

    private List<Node> reconstructPath(Node end) {
        List<Node> path = new ArrayList<>();
        Node current = end;
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }
}