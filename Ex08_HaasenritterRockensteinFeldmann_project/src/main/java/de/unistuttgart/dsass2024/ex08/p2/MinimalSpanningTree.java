package de.unistuttgart.dsass2024.ex08.p2;

import java.util.*;

public class MinimalSpanningTree {
    /**
     * This method calculates the minimal spanning tree (MST) using the kruskal
     * algorithm.
     *
     * @param graph the graph object to calculate the MST for
     * @return a set of edges, which belong to the MST of the given graph
     */
    public static Set<IEdge> kruskal(IWeightedGraph graph) {
        Set<IEdge> mst = new HashSet<>();
        List<IEdge> edges = new ArrayList<>();
        graph.edgeIterator().forEachRemaining(edges::add);

        // Sort edges by weight
        Collections.sort(edges, Comparator.comparingDouble(IEdge::getWeight));

        // Initialize Union-Find structure
        UnionFind uf = new UnionFind(graph.numberOfNodes());

        for (IEdge edge : edges) {
            long src = edge.getSource();
            long dest = edge.getDestination();

            // If src and dest are not connected, add the edge to the MST
            if (uf.find(src) != uf.find(dest)) {
                uf.union(src, dest);
                mst.add(edge);
            }
        }

        return mst;
    }

    static class UnionFind {
        private final Map<Long, Long> parent;
        private final Map<Long, Integer> rank;

        public UnionFind(int size) {
            parent = new HashMap<>(size);
            rank = new HashMap<>(size);
        }

        public long find(long node) {
            if (parent.get(node) == null) {
                parent.put(node, node);
                rank.put(node, 0);
            }
            if (parent.get(node) != node) {
                parent.put(node, find(parent.get(node))); // Path compression
            }
            return parent.get(node);
        }

        public void union(long node1, long node2) {
            long root1 = find(node1);
            long root2 = find(node2);

            if (root1 != root2) {
                // Union by rank
                if (rank.get(root1) > rank.get(root2)) {
                    parent.put(root2, root1);
                } else if (rank.get(root1) < rank.get(root2)) {
                    parent.put(root1, root2);
                } else {
                    parent.put(root2, root1);
                    rank.put(root1, rank.get(root1) + 1);
                }
            }
        }
    }
}


