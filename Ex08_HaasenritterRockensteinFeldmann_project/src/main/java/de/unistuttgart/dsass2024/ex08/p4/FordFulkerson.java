package de.unistuttgart.dsass2024.ex08.p4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class FordFulkerson implements IFordFulkerson{

    private int numVertices;
    private ArrayList<ArrayList<Integer>> residualGraph;
    private ArrayList<ArrayList<Integer>> maxFlowGraph;

    @Override
    public int calculate(ArrayList<ArrayList<Integer>> graph, int s, int t) {
        numVertices = graph.size();
        residualGraph = new ArrayList<>();

        // Initialize residual graph
        for (ArrayList<Integer> row : graph) {
            residualGraph.add(new ArrayList<>(row));
        }

        maxFlowGraph = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            maxFlowGraph.add(new ArrayList<>(graph.get(i)));
        }

        int maxFlow = 0;

        // Augment the flow while there is an augmenting path from s to t
        while (hasAugmentingPath(s, t)) {
            // Find the maximum flow through the path found
            int pathFlow = Integer.MAX_VALUE;
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph.get(u).get(v));
            }

            // Update residual capacities of the edges and reverse edges along the path
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                residualGraph.get(u).set(v, residualGraph.get(u).get(v) - pathFlow);
                residualGraph.get(v).set(u, residualGraph.get(v).get(u) + pathFlow);

                // Update the flow graph (forward edges only)
                maxFlowGraph.get(u).set(v, maxFlowGraph.get(u).get(v) + pathFlow);
            }

            // Add path flow to overall flow
            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    private boolean[] visited;
    private int[] parent;

    private boolean hasAugmentingPath(int s, int t) {
        visited = new boolean[numVertices];
        parent = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
            parent[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;

        // BFS
        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < numVertices; v++) {
                if (!visited[v] && residualGraph.get(u).get(v) > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        // Return true if we reached the sink t in BFS starting from source s
        return visited[t];
    }



    @Override
    public ArrayList<ArrayList<Integer>> flow() {
        return maxFlowGraph;
    }

}