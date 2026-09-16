package org.alessandra;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid();

        // obstáculos de teste  formando uma "parede"
        for (int y = 5; y < 25; y++) {
            grid.setObstacle(15, y);
        }

        Node start = grid.getNode(0, 0);
        Node end = grid.getNode(29, 29);

        AStar aStar = new AStar();
        List<Node> path = aStar.findPath(grid, start, end);

        if (path == null) {
            System.out.println("Nenhum caminho encontrado.");
        } else {
            System.out.println("Caminho encontrado com " + path.size() + " passos:");
            MazePrinter.print(grid, path, start, end);
        }

    }
}