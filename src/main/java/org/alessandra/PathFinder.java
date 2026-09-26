package org.alessandra;

import java.util.List;

public interface PathFinder {
    List<Node> findPath(Grid grid, Node start, Node end);
    int getNodesExplored();


}
