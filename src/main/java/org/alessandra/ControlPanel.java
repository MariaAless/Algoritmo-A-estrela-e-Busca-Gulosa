package org.alessandra;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class ControlPanel {

    private final VBox root;
    private final RadioButton radioAstar, radioGuloso;
    private final RadioButton ferramentaObstaculo, ferramentaInicio, ferramentaFim;
    private final Button botaoIniciar, botaoReiniciar;
    private final Label labelMetricas;

    public ControlPanel() {
        String textoClaro = "-fx-text-fill: white;";

        Label labelAlgoritmo = new Label("Algoritmo:");
        Label labelFerramenta = new Label("Ferramenta:");
        labelMetricas = new Label("Nós explorados: -\nTempo: -\nCusto do caminho: -");
        labelAlgoritmo.setStyle(textoClaro);
        labelFerramenta.setStyle(textoClaro);
        labelMetricas.setStyle(textoClaro);

        ToggleGroup grupoAlgoritmo = new ToggleGroup();
        radioGuloso = new RadioButton("Busca Gulosa");
        radioAstar = new RadioButton("A*");
        radioAstar.setSelected(true);
        radioGuloso.setToggleGroup(grupoAlgoritmo);
        radioAstar.setToggleGroup(grupoAlgoritmo);
        radioAstar.setStyle(textoClaro);
        radioGuloso.setStyle(textoClaro);

        ToggleGroup grupoFerramenta = new ToggleGroup();
        ferramentaObstaculo = new RadioButton("Obstáculo");
        ferramentaInicio = new RadioButton("Definir início");
        ferramentaFim = new RadioButton("Definir fim");
        ferramentaObstaculo.setSelected(true);
        ferramentaObstaculo.setToggleGroup(grupoFerramenta);
        ferramentaInicio.setToggleGroup(grupoFerramenta);
        ferramentaFim.setToggleGroup(grupoFerramenta);
        ferramentaObstaculo.setStyle(textoClaro);
        ferramentaInicio.setStyle(textoClaro);
        ferramentaFim.setStyle(textoClaro);

        botaoIniciar = new Button("Iniciar");
        botaoReiniciar = new Button("Reiniciar");

        root = new VBox(10,
                labelAlgoritmo, radioAstar, radioGuloso,
                new Separator(),
                labelFerramenta, ferramentaObstaculo, ferramentaInicio, ferramentaFim,
                new Separator(),
                botaoIniciar, botaoReiniciar,
                labelMetricas
        );
        root.setStyle("-fx-padding: 20; -fx-background-color: #2b2b2b;");
        root.setPrefWidth(220);
    }

    public VBox getRoot() {
        return root;
    }

    public boolean isAstarSelecionado() {
        return radioAstar.isSelected();
    }

    public boolean isFerramentaObstaculo() {
        return ferramentaObstaculo.isSelected();
    }

    public boolean isFerramentaInicio() {
        return ferramentaInicio.isSelected();
    }

    public boolean isFerramentaFim() {
        return ferramentaFim.isSelected();
    }

    public void setOnIniciar(Runnable acao) {
        botaoIniciar.setOnAction(e -> acao.run());
    }

    public void setOnReiniciar(Runnable acao) {
        botaoReiniciar.setOnAction(e -> acao.run());
    }

    public void setMetricas(String texto) {
        labelMetricas.setText(texto);
    }
}