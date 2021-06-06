package my.tasks.kogutenko.task2.finder;

/**
 * One edge of the graph
 */
public final class Edge {
    public final String sourceCity, destinationCity;
    public final int cost;

    public Edge(String sourceCity, String destinationCity, int cost) {
        this.sourceCity = sourceCity;
        this.destinationCity = destinationCity;
        this.cost = cost;
    }
}
