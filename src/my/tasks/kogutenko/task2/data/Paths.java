package my.tasks.kogutenko.task2.data;

/**
 * Object to storage input data
 */
public class Paths {

    private int idCity;
    private String city;
    private int idNeighbour;
    private int cost;

    public int getIdCity() {
        return idCity;
    }

    public String getCity() {
        return city;
    }

    public int getIdNeighbour() {
        return idNeighbour;
    }

    public int getCost() {
        return cost;
    }

    public Paths(int idCity, String city, int idNeighbour, int cost) {
        this.idCity = idCity;
        this.city = city;
        this.idNeighbour = idNeighbour;
        this.cost = cost;
    }
}
