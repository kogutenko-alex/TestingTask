package my.tasks.kogutenko.task2.data;

import my.tasks.kogutenko.task2.finder.Edge;
import my.tasks.kogutenko.task2.finder.Graph;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reader is used for read the input data from keyboard.
 */

public class Reader {
    private Scanner scanner = new Scanner(System.in);
    private List<Paths> paths;
    private List<Edge> edges;
    private Map<String, String> runPaths;
    private Graph graph;

    public Map<String, String> getRunPaths() {
        return runPaths;
    }

    public Graph getGraph() {
        return graph;
    }

    public Reader() {

        paths = new ArrayList<Paths>();
        edges = new ArrayList<Edge>();
        runPaths = new LinkedHashMap<>();
        Pattern pattern;
        CharSequence input;
        Matcher matcher;
        try {
            String str_numberOfTests, str_numberOfCities, str_numberOfNeighbors, str_idCity, str_cost, str_numberOfPaths;
            int numberOfTests, numberOfCities, numberOfNeighbors, idCity, cost, numberOfPaths;
            while(true) {
                System.out.print("Enter number of tests not more 10: ");
                pattern = Pattern.compile("[1-9]");
                str_numberOfTests = scanner.nextLine();
                matcher = pattern.matcher(str_numberOfTests);
                if (matcher.matches()) {
                    numberOfTests = Integer.parseInt(str_numberOfTests);
                    break;
                } else {
                    System.err.println("\nEnter correctly!!!");
                }
            }

            for (int i = 0; i < numberOfTests; i++) {
                while (true) {
                    System.out.print("Enter number of cities not more 10000: ");
                    pattern = pattern.compile("[1-9]{1}[0-9]{0,3}");
                    str_numberOfCities = scanner.nextLine();
                    matcher = pattern.matcher(str_numberOfCities);
                    if (matcher.matches()) {
                        numberOfCities = Integer.parseInt(str_numberOfCities);
                        break;
                    } else {
                        System.out.println("\nEnter correctly!!!");
                    }
                }
                for (int j = 0; j < numberOfCities; j++) {
                    // read the name of city and the numbers of neighbors of this city
                    System.out.print("Enter city name: ");
                    String cityName = scanner.nextLine();
                    while(true) {
                        System.out.print("Enter number of city - neighbors: ");
                        pattern = pattern.compile("[1-9]{1}[0-9]{0,3}");
                        str_numberOfNeighbors = scanner.nextLine();
                        matcher = pattern.matcher(str_numberOfNeighbors);
                        if (matcher.matches()) {
                            numberOfNeighbors = Integer.parseInt(str_numberOfNeighbors);
                            break;
                        } else {
                            System.err.println("\nEnter correctly!!!");
                        }
                    }
                    int cityNumber = j + 1;
                    for (int k = 0; k < numberOfNeighbors; k++) {
                        while(true){
                            System.out.print("\tEnter id neighbor city: ");
                            pattern = pattern.compile("[1-9]{1}[0-9]{0,3}");
                            str_idCity = scanner.nextLine();
                            matcher = pattern.matcher(str_idCity);
                            if (matcher.matches()) {
                                idCity = Integer.parseInt(str_idCity);
                                break;
                            } else {
                                System.err.println("\n[" + i + "] enter correctly!!!");
                            }
                        }
                        while(true){
                            System.out.print("\t[" + i + "] enter cost: ");
                            pattern = pattern.compile("[1-9]{1,4}|1[1-9]{1,4}"); //!!!!
                            str_cost = scanner.nextLine();
                            matcher = pattern.matcher(str_cost);
                            if (matcher.matches()) {
                                cost = Integer.parseInt(str_cost);
                                break;
                            } else {
                                System.err.println("\nEnter correctly!!!");
                            }
                        }
                        paths.add(new Paths(cityNumber, cityName, idCity, cost));
                    }
                }
                createEdge(paths);
            }

            graph = new Graph(edges);
            String sourceCity = null;
            String destinationCity = null;

            while(true) {
                System.out.print("Enter number of paths to find not more 100: ");
                pattern = pattern.compile("[1-9]{1,2}");
                str_numberOfPaths = scanner.nextLine();
                matcher = pattern.matcher(str_numberOfPaths);
                if (matcher.matches()) {
                    numberOfPaths = Integer.parseInt(str_numberOfPaths);
                    break;
                } else {
                    System.err.println("\nEnter correctly!!!");
                }
            }
            for (int i = 0; i < numberOfPaths; i++) {
                System.out.print("Enter source city: ");
                sourceCity = scanner.next();
                System.out.print("Enter destination city: ");
                destinationCity = scanner.next();
                runPaths.put(sourceCity, destinationCity);
            }
        } catch (NumberFormatException e) {
            System.err.println(e);
        }
    }


    private void createEdge(List<Paths> paths) {
        for (Paths path : paths) {
            String sourceCity = path.getCity();
            int id = path.getIdNeighbour();
            String destinationCity = null;
            for (Paths auxiliaryPath : paths) {
                if (auxiliaryPath.getIdCity() == id) {
                    destinationCity = auxiliaryPath.getCity();
                }
            }
            edges.add(new Edge(sourceCity, destinationCity, path.getCost()));
        }
    }
}
