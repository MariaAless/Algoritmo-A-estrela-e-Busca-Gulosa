package org.alessandra;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class MazeCanvas {

    private final Canvas canvas;
    private final GraphicsContext gc;

    public MazeCanvas() {
        canvas = new Canvas(Grid.GRID_SIZE * Grid.CELL_SIZE, Grid.GRID_SIZE * Grid.CELL_SIZE);
        gc = canvas.getGraphicsContext2D();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public int toGridX(double pixelX) {
        return (int) (pixelX / Grid.CELL_SIZE);
    }

    public int toGridY(double pixelY) {
        return (int) (pixelY / Grid.CELL_SIZE);
    }

    public void drawBase(Grid grid, Node start, Node end) {
        for (int x = 0; x < Grid.GRID_SIZE; x++) {
            for (int y = 0; y < Grid.GRID_SIZE; y++) {
                var img = grid.getNode(x, y).walkable ? AssetLoader.FLOOR : AssetLoader.WALL;
                gc.drawImage(img, x * Grid.CELL_SIZE, y * Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE);
            }
        }
        drawMarkers(start, end);
    }

    public void drawPath(List<Node> path) {
        gc.setFill(Color.rgb(255, 255, 0, 0.5));
        for (Node n : path) {
            gc.fillRect(n.x * Grid.CELL_SIZE, n.y * Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE);
        }
    }

    public void drawAgent(Node posicao) {
        gc.drawImage(AssetLoader.AGENT, posicao.x * Grid.CELL_SIZE, posicao.y * Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE);
    }

    public void drawStart(Node start) {
        gc.drawImage(AssetLoader.START, start.x * Grid.CELL_SIZE, start.y * Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE);
    }

    public void drawEnd(Node end) {
        gc.drawImage(AssetLoader.END, end.x * Grid.CELL_SIZE, end.y * Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE);
    }

    public void drawMarkers(Node start, Node end) {
        drawStart(start);
        drawEnd(end);
    }

    public void drawGlow(Node cell, double alpha) {
        gc.setFill(Color.rgb(255, 215, 0, alpha));
        gc.fillRect(cell.x * Grid.CELL_SIZE, cell.y * Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE);
    }

    public void drawTransformed(Node posicao) {
        gc.drawImage(AssetLoader.TRANSFORMED, posicao.x * Grid.CELL_SIZE, posicao.y * Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE);
    }
}