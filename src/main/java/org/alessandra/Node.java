package org.alessandra;

public class Node {
    int x, y;
    boolean walkable; // se dá para passsar por essa célula(obstáculo ou não)

    double gCost;  //Custo já percorrido do inicio até aqui
    double hCost; //Estimativa(heuristica) daqui até o fim
    double fCost; //gCost + hCost -. o que o A* usa para decidir prioridade

    Node parent; //de qual célula eu vim(para reconstruir o caminho no final)

    public Node(int x, int y, boolean walkable){
        this.x = x;
        this.y= y;
        this.walkable = walkable;
    }
}
