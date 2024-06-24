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
        int n = weights.size(); // Anzahl der Knoten
        int[] pi = new int[n];
        boolean[] visited = new boolean[n];

        // Initialisiere das pi-Array mit -1 (kein Vorgänger)
        for (int i = 0; i < n; i++) {
            pi[i] = -1;
        }

        // Validiere die Adjazenzmatrix (weights), dass sie rechteckig ist
        for (ArrayList<Integer> row : weights) {
            if (row.size() != n) {
                throw new IllegalArgumentException("Adjazenzmatrix ist nicht rechteckig");
            }
        }

        // Validiere den Startknoten s, dass er innerhalb des gültigen Bereichs liegt
        if (s < 0 || s >= n) {
            throw new IllegalArgumentException("Startknoten liegt außerhalb des gültigen Bereichs");
        }

        // Führe die DFS durch
        dfsRekursiv(weights, s, pi, visited);

        return pi;
    }

    private static void dfsRekursiv(ArrayList<ArrayList<Integer>> weights, int u, int[] pi, boolean[] visited) {
        visited[u] = true;

        // Durchlaufe Nachbarn von u mit der kleinsten ID
        for (int v = 0; v < weights.size(); v++) {
            if (weights.get(u).get(v) > 0 && !visited[v]) { // Es gibt eine Kante von u nach v und v ist nicht besucht
                pi[v] = u; // v wird von u erreicht
                dfsRekursiv(weights, v, pi, visited);
            }
        }
    }

}

