package org.alessandra;

public class Grid {
    private final int width = 30;
    private final int height = 30;
    private final Node[][] nodes;


    public Grid(){
        nodes = new Node[width][height];
        for(int x= 0; x<width; x++){
            for(int y=0; y<height; y++){
                nodes[x][y] = new Node(x,y,true); // por padrão, tudo andável
            }
        }
    }

    public Node getNode(int x, int y){
        return nodes[x][y];
    }

    public void setObstacle(int x, int y){
        nodes[x][y].walkable = false;
    }

    public boolean isInsideGrid(int x, int y){
        return x>=0 && x <width && y>=0 && y < height;
    }
}
