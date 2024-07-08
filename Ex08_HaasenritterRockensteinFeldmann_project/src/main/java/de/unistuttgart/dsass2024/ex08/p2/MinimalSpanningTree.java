package de.unistuttgart.dsass2024.ex08.p2;

import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

public class MinimalSpanningTree {
    /**
     * This method calculates the minimal spanning tree (MST) using the kruskal
     * algorithm.
     *
     * @param graph the graph object to calculate the MST for
     * @return a set of edges, which belong to the MST of the given graph
     */
    public static Set<IEdge> kruskal(IWeightedGraph graph) {
        // sort Edges ascending
        ArrayList<IEdge> edgeList = new ArrayList<>();
        Iterator<IEdge> EdgeIterator = graph.edgeIterator();
        // get Edges from Adjacency list and add them to a List in an unsorted manner
        while(EdgeIterator.hasNext())
        {
            edgeList.add(EdgeIterator.next());
        }
        EdgeWeigthComparator comparator = new EdgeWeigthComparator();

        // sort EdgeList in ascending order by Weights
        Collections.sort(edgeList,comparator);
        // debug
    	/*
    	 System.out.println("After sorting:");
         for (IEdge edge : edgeList) {
             System.out.println(edge.getWeight());
         }
         */
        int numNodes = graph.numberOfNodes();
        long[] NodeIDs;
        NodeIDs = new long[numNodes];

        Iterator<Node> nodeIterator = graph.nodeIterator();
        //set of edges, which belong to the MST of the given graph
        Set<IEdge> result = new HashSet<>();
        //for all v element of V do Make-set(v)

        int i = 0;
        while(nodeIterator.hasNext()) {
            NodeIDs[i] = nodeIterator.next().getID();
            i++;
        }

        int size = graph.numberOfNodes();
        UnionFind uf = new UnionFind(size,NodeIDs);

        for(IEdge e : edgeList) {

            //skip this edge to avoid creating a cycle in MST
            if(uf.connected(e.getSource(),e.getDestination())) continue;
            // Include this edge
            uf.union(e.getSource(), e.getDestination());
            //add Node to minimal spanning tree
            result.add(e);

        }

        return result;

    }

}


