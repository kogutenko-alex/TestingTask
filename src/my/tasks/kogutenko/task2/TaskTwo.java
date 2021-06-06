package my.tasks.kogutenko.task2;

import my.tasks.kogutenko.task2.data.Reader;

public class TaskTwo {
    public static void main(String[] args) {
        //new TestData(); //uncommented the line if you want use the test input data
        //commented next lines if you want use the test input data
        Reader reader = new Reader();
        for (String key : reader.getRunPaths().keySet()) {
            reader.getGraph().shortestPath(key);
            reader.getGraph().printCost(reader.getRunPaths().get(key));
        }
    }
}
