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
     * @param s       the id of the node to start the DFS on
     * @return array with predecessors. I.e. pi[5] = 2 means, that the node with ID 2 is
     * predecessor of the node with ID 5. For nodes x that don't have a
     * predecessor (i.e., the start node and nodes which aren't reachable), the
     * returned array contains the value -1 (i.e., pi[x] = -1).
     */
    public static int[] dfs(ArrayList<ArrayList<Integer>> weights, int s) throws IllegalArgumentException {
        //number of nodes
        int n = weights.size();
        int[] pi = new int[n];
        boolean[] visited = new boolean[n];

        // initialize the pi-Array with -1
        for (int i = 0; i < n; i++) {
            pi[i] = -1;
        }

        // this method validates that the given adjacency matrix is rectangular
        for (ArrayList<Integer> row : weights) {
            if (row.size() != n) {
                throw new IllegalArgumentException("Adjazenzmatrix ist nicht rechteckig");
            }
        }

        // validates that the start node s is within the valid range
        if (s < 0 || s >= n) {
            throw new IllegalArgumentException("Startknoten liegt außerhalb des gültigen Bereichs");
        }

        // starts the dfs
        dfsRekursiv(weights, s, pi, visited);

        return pi;
    }

    private static void dfsRekursiv(ArrayList<ArrayList<Integer>> weights, int u, int[] pi, boolean[] visited) {
        visited[u] = true;

        // iterates through neighbors of u with the smallest ID
        for (int v = 0; v < weights.size(); v++) {
            if (weights.get(u).get(v) > 0 && !visited[v]) { // checks if there is an edge from u to v and v is not visited
                pi[v] = u; // u gets to v
                dfsRekursiv(weights, v, pi, visited);
            }
        }
    }

}

