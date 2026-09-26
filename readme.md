# A* vs Busca Gulosa

Aplicação JavaFX que simula um agente percorrendo um labirinto até um ponto de destino (poção), permitindo alternar entre dois algoritmos de busca de caminho e comparar métricas em tempo real.

Projeto desenvolvido para a disciplina de Estrutura de Dados Não Lineares (IFRN).

## Funcionalidades

- Grid interativo, com edição manual de obstáculos, ponto de início e ponto de fim
- Alternância entre **A\*** e **Busca Gulosa (Greedy Best-First Search)**
- Métricas em tempo real: nós explorados, tempo de execução, custo total do caminho
- Animação do agente percorrendo o caminho encontrado, com efeito de conquista ao chegar no destino
- Botão de reiniciar (limpa obstáculos e métricas)

## Tecnologias

Java 21 · JavaFX · Maven

## Algoritmos

Ambos resolvem o mesmo problema — achar um caminho do início ao fim, com movimento em 8 direções — mas decidem de forma diferente qual célula explorar primeiro.

**A\*** — pontua cada célula por `F = G + H`, onde `G` é o custo já percorrido e `H` é a distância estimada até o destino (heurística octile, que considera a diagonal mais barata que dois movimentos retos). Sempre expande a célula de menor `F`, garantindo o caminho mais curto.

**Busca Gulosa** — usa só `H`, ignorando o custo já percorrido. É mais rápida (explora menos nós), mas não garante o caminho ótimo.

## Estrutura

```
org/alessandra/
├── Grid.java          tabuleiro, células, constantes GRID_SIZE/CELL_SIZE
├── Node.java           célula individual (posição, custos G/H/F)
├── PathFinder.java      interface comum entre os algoritmos
├── AStar.java
├── Greedy.java
├── AssetLoader.java     carrega os sprites uma única vez
├── MazeCanvas.java       desenho do grid, caminho, agente e efeitos
├── ControlPanel.java     painel lateral (botões, métricas)
└── MainApp.java           orquestra tudo
```

## Como rodar

```bash
mvn javafx:run
```

Ou, pelo IntelliJ: aba Maven → `Plugins → javafx → javafx:run`.

## Como usar

1. Escolha a ferramenta (**Obstáculo**, **Definir início** ou **Definir fim**) e clique no grid para montar o labirinto
2. Escolha o algoritmo (**A\*** ou **Busca Gulosa**)
3. Clique em **Iniciar** para rodar e ver a animação
4. Clique em **Reiniciar** para limpar o grid