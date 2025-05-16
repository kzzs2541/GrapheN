import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Graph {
    private Map<Integer, Vertices> graph;

    public Graph(){
        this.graph = new HashMap<>();
    }

    public void addVertex(int id, Vertices vertex) {
        if (!graph.containsKey(id)) {
            graph.put(id, vertex);
        }
    }

    public void addEdge(int id1, int id2) {
        Vertices w1 = graph.get(id1);
        Vertices w2 = graph.get(id2);
        if (w1 != null && w2 != null) {
            w1.addNeighbor(w2);

        }
    }

    public Map<Integer, Vertices> getWierzcholki() {
        return graph;
    }
}
