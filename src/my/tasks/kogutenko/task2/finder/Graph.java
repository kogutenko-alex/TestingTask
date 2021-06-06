package my.tasks.kogutenko.task2.finder;

import java.util.*;


public class Graph {

    private final Map<String, Vertex> graph; // mapping of vertex names to Vertex objects, built from a set of Edges

    /**
     * Builds a graph from a set of edges
     */
    public Graph(List<Edge> edges) {
        graph = new HashMap<>(edges.size());

        //one pass to find all vertices
        for (Edge edge : edges) {
            if (!graph.containsKey(edge.sourceCity)) graph.put(edge.sourceCity, new Vertex(edge.sourceCity));
            if (!graph.containsKey(edge.destinationCity))
                graph.put(edge.destinationCity, new Vertex(edge.destinationCity));
        }

        //another pass to set neighbouring vertices
        for (Edge edge : edges) {
            Vertex vertex = graph.get(edge.sourceCity);
            vertex.neighbours.put(graph.get(edge.destinationCity), edge.cost);
        }
    }

    /**
     * Runs data using a specified source vertex
     */
    public void shortestPath(String startName) {
        if (!graph.containsKey(startName)) {
            System.err.printf("Graph doesn't contain start vertex \"%s\"\n", startName);
            return;
        }
        final Vertex source = graph.get(startName);
        NavigableSet<Vertex> verticesTree = new TreeSet<>();

        // set-up vertices
        for (Vertex vertices : graph.values()) {
            vertices.previous = vertices == source ? source : null;
            vertices.maxCost = vertices == source ? 0 : 200001;
            verticesTree.add(vertices);
        }

        shortestPath(verticesTree);
    }

    /**
     * Implementation of data's algorithm using a binary heap.
     */
    private void shortestPath(final NavigableSet<Vertex> verticesTree) {
        Vertex shortestDistance, neighbour;
        while (!verticesTree.isEmpty()) {

            shortestDistance = verticesTree.pollFirst(); // vertex with shortest distance (first iteration will return source)
            if (shortestDistance.maxCost == 200001)
                break; // we can ignore shortestDistance (and any other remaining vertices) since they are unreachable

            //look at distances to each neighbour
            for (Map.Entry<Vertex, Integer> a : shortestDistance.neighbours.entrySet()) {
                neighbour = a.getKey(); //the neighbour in this iteration

                final int alternateDist = shortestDistance.maxCost + a.getValue();
                if (alternateDist < neighbour.maxCost) { // shorter path to neighbour found
                    verticesTree.remove(neighbour);
                    neighbour.maxCost = alternateDist;
                    neighbour.previous = shortestDistance;
                    verticesTree.add(neighbour);
                }
            }
        }
    }

    /**
     * Prints a cost from the source to the specified vertex
     *
     * @param endName
     */
    public void printCost(String endName) {
        if (!graph.containsKey(endName)) {
            System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
            return;
        }
        graph.get(endName).printCost();
    }
}
