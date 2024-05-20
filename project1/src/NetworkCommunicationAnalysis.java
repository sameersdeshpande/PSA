import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//main function for reading csv file and then calling functions for MST and Visualize
public class NetworkCommunicationAnalysis {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = readCommunicationLinksFromCSV("/Users/sameersdeshpande/PSA_Project/sample_100.csv");
        int[][] adjacencyMatrix = createAdjacencyMatrix(graph);
        int[][] mst = primsAlgorithm(adjacencyMatrix);
        int diameter = calculateDiameter(mst);
        //int sourceVertex = 0;
       // int[] shortestPaths = dijkstra(adjacencyMatrix, 0);
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            dijkstra(adjacencyMatrix, i);
        }
       // printShortestPaths(shortestPaths);
        printMST(mst);
        visualizeMST(mst);
        System.out.println("Diameter of the Minimum Spanning Tree: " + diameter);
    }

    //Visualizing MST using mxGraph
    private static void visualizeMST(int[][] mst) {
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();
        try {
            Map<Integer, Object> vertexMap = new HashMap<>();
            for (int i = 0; i < mst.length; i++) {
                vertexMap.put(i, graph.insertVertex(parent, null, String.valueOf(i), 20, 20, 30, 30));
            }

            for (int i = 0; i < mst.length; i++) {
                for (int j = i + 1; j < mst.length; j++) {
                    if (mst[i][j] != 0) {
                        graph.insertEdge(parent, null, String.valueOf(mst[i][j]), vertexMap.get(i), vertexMap.get(j));
                    }
                }
            }
            mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
            layout.execute(parent);
        } finally {
            graph.getModel().endUpdate();
        }
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        JFrame frame = new JFrame("Minimum Spanning Tree Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(graphComponent);
        frame.pack();
        frame.setVisible(true);
    }

//Printing the MST in console
    private static void printMST(int[][] mst) {
        System.out.println("---------------------------------------");
        System.out.println("Minimum Spanning Tree:");
        for (int i = 0; i < mst.length; i++) {
            for (int j = i + 1; j < mst.length; j++) {
                if (mst[i][j] != 0) {
                    System.out.println(i + " - " + j + ": " + mst[i][j]);
                }
            }
        }
    }

    // Function declaration on reading the CSV file
    private static Map<Integer, List<Integer>> readCommunicationLinksFromCSV(String filePath) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean skipHeader = true;
            while ((line = br.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                String[] parts = line.split(",");
                int source = Integer.parseInt(parts[0]);
                int destination = Integer.parseInt(parts[1]);
                graph.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
                graph.computeIfAbsent(destination, k -> new ArrayList<>()).add(source);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }
// Created adjacency matrix to pass through Prims and Visualization
    private static int[][] createAdjacencyMatrix(Map<Integer, List<Integer>> graph) {
        int maxVertex = graph.keySet().stream()
                .flatMapToInt(key -> graph.get(key).stream().mapToInt(Integer::intValue))
                .max()
                .orElse(0);
        int[][] adjacencyMatrix = new int[maxVertex + 1][maxVertex + 1];
        for (int i = 0; i <= maxVertex; i++) {
            Arrays.fill(adjacencyMatrix[i], 0); // Initialize all values to 0
        }
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            int source = entry.getKey();
            for (int destination : entry.getValue()) {
                adjacencyMatrix[source][destination] = 1; // Assuming unweighted graph
                adjacencyMatrix[destination][source] = 1; // Assuming undirected graph
            }
        }
        return adjacencyMatrix;
    }
    //Used Prims algorithm for Minimum Tree
    private static int[][] primsAlgorithm(int[][] graph) {
        int V = graph.length;
        int[][] mst = new int[V][V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet);
            if (u == -1) {
                break; // All vertices have been added to MST
            }
            mstSet[u] = true;
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    mst[v][u] = graph[u][v];
                    mst[u][v] = graph[u][v];
                    key[v] = graph[u][v];
                }
            }
        }
        return mst;
    }
    //The minKey function is used in Prim's algorithm to find the vertex with the minimum key value that has not yet been included in the Minimum Spanning Tree (MST). The key values are used to determine the next vertex to be added to the MST. The mstSet array keeps track of which vertices have already been added to the MST.
    private static int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int v = 0; v < key.length; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Dijkstra algorithm to calculate the shortest path
    private static void dijkstra(int[][] graph, int src) {
        int V = graph.length;
        int[] dist = new int[V];
        boolean[] sptSet = new boolean[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);
            if (u == -1) {
                break; // All remaining vertices are unreachable
            }
            sptSet[u] = true;
            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
        // Print shortest paths for reachable vertices
        for (int i = 0; i < V; i++) {
            if (dist[i] != Integer.MAX_VALUE) {
                System.out.println("Vertex " + src + " to Vertex " + i + ": " + dist[i]);
            }
        }
    }
    private static int minDistance(int[] dist, boolean[] sptSet) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int v = 0; v < dist.length; v++) {
            if (!sptSet[v] && dist[v] < min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
    private static void printShortestPaths(int[] shortestPaths) {
        System.out.println("Shortest Paths from Source:");
        for (int i = 0; i < shortestPaths.length; i++) {
            System.out.println("Vertex 0 to Vertex " + i + ": " + shortestPaths[i]);
        }
    }
    // For calculating the diameter, BFS is used
    private static int calculateDiameter(int[][] mst) {
        // You can calculate the diameter of the MST using the graph as needed
        //  by performing a BFS on the MST
        int V = mst.length;
        int diameter = 0;
        // Perform BFS from each vertex to find the furthest vertex
        for (int i = 0; i < V; i++) {
            int[] distances = new int[V];
            Arrays.fill(distances, -1);
            distances[i] = 0;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int v = 0; v < V; v++) {
                    if (mst[u][v] != 0 && distances[v] == -1) {
                        distances[v] = distances[u] + 1;
                        queue.add(v);
                    }
                }
            }
            // Find the maximum distance from the current BFS traversal
            for (int distance : distances) {
                if (distance > diameter) {
                    diameter = distance;
                }
            }
        }
        return diameter;
    }
}
