package org.alessandra;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class MainApp extends Application {

    private final Grid grid = new Grid();
    private Node start;
    private Node end;

    private MazeCanvas mazeCanvas;
    private ControlPanel controlPanel;

    @Override
    public void start(Stage stage) {
        start = grid.getNode(0, 0);
        end = grid.getNode(Grid.GRID_SIZE - 1, Grid.GRID_SIZE - 1);

        mazeCanvas = new MazeCanvas();
        controlPanel = new ControlPanel();

        mazeCanvas.getCanvas().setOnMouseClicked(this::aoClicarNoGrid);
        controlPanel.setOnIniciar(this::executar);
        controlPanel.setOnReiniciar(this::reiniciar);

        BorderPane root = new BorderPane();
        root.setCenter(mazeCanvas.getCanvas());
        root.setLeft(controlPanel.getRoot());
        root.setStyle("-fx-background-color: #2b2b2b;");

        stage.setScene(new Scene(root));
        stage.setTitle("A* vs Busca Gulosa");
        stage.sizeToScene();
        stage.show();

        mazeCanvas.drawBase(grid, start, end);
    }

    private void aoClicarNoGrid(javafx.scene.input.MouseEvent evento) {
        int x = mazeCanvas.toGridX(evento.getX());
        int y = mazeCanvas.toGridY(evento.getY());

        if (!grid.isInsideGrid(x, y)) return;

        Node clicado = grid.getNode(x, y);

        if (controlPanel.isFerramentaObstaculo()) {
            if (clicado == start || clicado == end) return;
            clicado.walkable = !clicado.walkable;
        } else if (controlPanel.isFerramentaInicio()) {
            if (!clicado.walkable) return;
            start = clicado;
        } else if (controlPanel.isFerramentaFim()) {
            if (!clicado.walkable) return;
            end = clicado;
        }

        mazeCanvas.drawBase(grid, start, end);
    }

    private void executar() {
        PathFinder algoritmo = controlPanel.isAstarSelecionado() ? new AStar() : new Greedy();

        grid.resetNodes();

        long inicio = System.currentTimeMillis();
        List<Node> path = algoritmo.findPath(grid, start, end);
        long fim = System.currentTimeMillis();

        mazeCanvas.drawBase(grid, start, end);

        if (path == null) {
            controlPanel.setMetricas("Nenhum caminho encontrado.");
            return;
        }

        double custo = calcularCusto(path);
        controlPanel.setMetricas(String.format(
                "Nós explorados: %d\nTempo: %dms\nCusto do caminho: %.2f",
                algoritmo.getNodesExplored(), (fim - inicio), custo
        ));

        mazeCanvas.drawPath(path);
        mazeCanvas.drawMarkers(start, end);

        animarAgente(path);
    }

    private void animarAgente(List<Node> path) {
        Timeline timeline = new Timeline();

        // percurso célula por célula — poção ainda visível
        for (int i = 0; i < path.size(); i++) {
            Node passo = path.get(i);
            KeyFrame frame = new KeyFrame(Duration.millis(i * 80), e -> {
                mazeCanvas.drawBase(grid, start, end);
                mazeCanvas.drawPath(path);
                mazeCanvas.drawMarkers(start, end);
                mazeCanvas.drawAgent(passo);
            });
            timeline.getKeyFrames().add(frame);
        }

        // celebração: poção some, brilho pisca no lugar dela
        double tempoBase = path.size() * 80;
        double[] alphas = {0.6, 0.0, 0.6, 0.0, 0.6};
        for (int i = 0; i < alphas.length; i++) {
            double alpha = alphas[i];
            KeyFrame frameGlow = new KeyFrame(Duration.millis(tempoBase + i * 150), e -> {
                mazeCanvas.drawBase(grid, start, end);
                mazeCanvas.drawPath(path);
                mazeCanvas.drawStart(start); // só o início, sem a poção
                mazeCanvas.drawGlow(end, alpha);
                mazeCanvas.drawAgent(end);
            });
            timeline.getKeyFrames().add(frameGlow);
        }

        // transformação final: gato vira monstro, poção continua ausente
        KeyFrame frameTransformado = new KeyFrame(Duration.millis(tempoBase + alphas.length * 150 + 100), e -> {
            mazeCanvas.drawBase(grid, start, end);
            mazeCanvas.drawPath(path);
            mazeCanvas.drawStart(start);
            mazeCanvas.drawTransformed(end);
        });
        timeline.getKeyFrames().add(frameTransformado);

        timeline.play();
    }

    private void reiniciar() {
        grid.clearObstacles();
        grid.resetNodes();
        start = grid.getNode(0, 0);
        end = grid.getNode(19, 19);
        controlPanel.setMetricas("Nós explorados: -\nTempo: -\nCusto do caminho: -");
        mazeCanvas.drawBase(grid, start, end);
    }

    private double calcularCusto(List<Node> path) {
        double total = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            Node a = path.get(i);
            Node b = path.get(i + 1);
            int dx = Math.abs(a.x - b.x);
            int dy = Math.abs(a.y - b.y);
            total += (dx + dy) + (Math.sqrt(2) - 2) * Math.min(dx, dy);
        }
        return total;
    }

    public static void main(String[] args) {
        launch(args);
    }
}