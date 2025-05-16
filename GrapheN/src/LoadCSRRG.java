import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class LoadCSRRG {
    public static Graph loadGraph(String filePath) throws IOException {
        Graph graph = new Graph();
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String maxVertices = br.readLine();

        String[] indexesStr = br.readLine().split(";");
        int[] indexes = Arrays.stream(indexesStr).mapToInt(Integer::parseInt).toArray();
        int numOfVertices = indexes.length;
        String[] rowStartsStr = br.readLine().split(";");
        int[] rowStarts = Arrays.stream(rowStartsStr).mapToInt(Integer::parseInt).toArray();
        int numOfRows = rowStarts.length;
        String[] groupsStr = br.readLine().split(";");
        int[] groups = Arrays.stream(groupsStr).mapToInt(Integer::parseInt).toArray();
        String[] groupStartsStr = br.readLine().split(";");
        int[] groupStarts = Arrays.stream(groupStartsStr).mapToInt(Integer::parseInt).toArray();
        int numOfGroups = groupStarts.length;
        int id = 0;
        for(int i = 0; i < numOfRows; i++){
            int start = rowStarts[i];
            int end = (i + 1 < numOfRows) ? rowStarts[i+1] : numOfVertices;
            for(int j = start; j < end; j++){
                Vertices vertex = new Vertices(id, indexes[id], i);
                graph.addVertex(id, vertex);
                id++;
            }
        }

        for(int i = 0; i < numOfGroups; i++){
            int start = groupStarts[i];
            int end = (i + 1 < numOfGroups) ? groupStarts[i + 1] : groups.length;
            for(int j = start + 1; j < end; j++){
                graph.addEdge(groups[start], groups[j]);
            }
        }
        return graph;
    }
}
