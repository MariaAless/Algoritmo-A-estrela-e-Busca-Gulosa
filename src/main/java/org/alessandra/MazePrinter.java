package org.alessandra;

import java.util.List;

public class MazePrinter {

    public static void print(Grid grid, List<Node> path, Node start, Node end) {
        char[][] display = new char[30][30];

        // monta a base: obstáculo ou livre
        for (int y = 0; y < 30; y++) {
            for (int x = 0; x < 30; x++) {
                display[y][x] = grid.getNode(x, y).walkable ? '-' : '\u25A0'; // ■
            }
        }

        // desenha as setas do percurso
        for (int i = 0; i < path.size() - 1; i++) {
            Node current = path.get(i);
            Node next = path.get(i + 1);
            display[current.y][current.x] = getArrow(current, next);
        }

        // marca início e fim por cima de tudo
        display[start.y][start.x] = 'A';
        display[end.y][end.x] = 'B';

        for (int y = 0; y < 30; y++) {
            StringBuilder linha = new StringBuilder();
            for (int x = 0; x < 30; x++) {
                linha.append(display[y][x]).append(' ');
            }
            System.out.println(linha);
        }
    }

    private static char getArrow(Node from, Node to) {
        int dx = to.x - from.x;
        int dy = to.y - from.y;

        if (dx == 1 && dy == 0) return '>';
        if (dx == -1 && dy == 0) return '<';
        if (dx == 0 && dy == 1) return 'v';
        if (dx == 0 && dy == -1) return '^';
        if (dx == 1 && dy == 1) return '\u2198';  // ↘
        if (dx == -1 && dy == -1) return '\u2196'; // ↖
        if (dx == 1 && dy == -1) return '\u2197';  // ↗
        if (dx == -1 && dy == 1) return '\u2199';  // ↙
        return '?';
    }
}