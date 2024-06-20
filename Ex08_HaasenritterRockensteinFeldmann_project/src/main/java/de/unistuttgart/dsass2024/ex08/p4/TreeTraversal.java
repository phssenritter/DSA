package de.unistuttgart.dsass2024.ex08.p4;

import java.util.ArrayList;

public class TreeTraversal {

    /**
     * This method traverses the tree using depth first search (DFS). To eliminate any
     * ambiguity choosing the next node, the node with the smallest ID is visited next.
     *
     * @param weights adjacency matrix defining the graph. Since only you are
     *                using this method (when performing the ford-fulkerson algorithm),
     *                you can expect weights to be a rectangular array.
     * @param s the id of the node to start the DFS on
     * @return array with predecessors. I.e. pi[5] = 2 means, that the node with ID 2 is
     *         predecessor of the node with ID 5. For nodes x that don't have a
     *         predecessor (i.e., the start node and nodes which aren't reachable), the
     *         returned array contains the value -1 (i.e., pi[x] = -1).
     */
    public static int[] dfs(ArrayList<ArrayList<Integer>> weights, int s) throws IllegalArgumentException {

    }

}