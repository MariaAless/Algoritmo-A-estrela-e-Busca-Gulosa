package org.alessandra;

import java.util.*;


public class AStar {

    public List<Node> findPath(Grid grid, Node start, Node end){
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingDouble(n -> n.fCost));
        Set<Node> closedList = new HashSet<>();

        openList.add(start);

        while(!openList.isEmpty()){
            Node current = openList.poll();

            if(current == end){
                return reconstructPath(current);
            }

            closedList.add(current);

            for(Node neighbor: getNeighbors(grid, current)){
                if(!neighbor.walkable || closedList.contains(neighbor)){
                    continue;
                }

                double tentativeG = current.gCost + distance(current, neighbor);

                if(tentativeG < neighbor.gCost || !openList.contains(neighbor)){
                    neighbor.gCost=tentativeG;
                    neighbor.hCost=distance(neighbor,end);
                    neighbor.fCost= neighbor.gCost + neighbor.hCost;
                    neighbor.parent=current;

                    if(!openList.contains(neighbor)){
                        openList.add(neighbor);
                    }
                }
            }

        }

        return null; //sem caminho possível

    }


    private List<Node> getNeighbors(Grid grid, Node node){
        List<Node> neighbors = new ArrayList<>();

        for(int dx=-1; dx<=1; dx++){
            for(int dy=-1;dy <=1; dy++){
                if(dx ==0 && dy==0){
                    continue; //pula a própria célula
                }
                int newX = node.x + dx;
                int newY = node.y + dy;

                if(grid.isInsideGrid(newX,newY)){
                    neighbors.add(grid.getNode(newX,newY));
                }

            }
        }
        return neighbors;
    }

    private double distance(Node a, Node b){
        //heurística octile
        int dx = Math.abs(a.x - b.x);
        int dy = Math.abs(a.y - b.y);

        return  (dx + dy) + (Math.sqrt(2)-2) * Math.min(dx,dy);
    }

    private List<Node> reconstructPath(Node end){
        // voltando do fim ao incio pelos parent
        List<Node> path = new ArrayList<>();
        Node current = end;

        while (current!= null){
            path.add(current);
            current=current.parent;
        }
        Collections.reverse(path);
        return path;
    }


}
