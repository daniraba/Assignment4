import java.util.*;

/*
 * Daniella R. & Daisy M.
 * 
 *              |E| = |V| -1    |E| = [(|V| -1) 3/2]  |E| = (|V| -1)^2
 * |V| = 10     0.072958 ms     0.098541 ms            0.10025 ms
 * |V| = 100    0.107875 ms     0.2705 ms              0.782667 ms
 * |V| = 1000   0.501041 ms     2.183917 ms            38.634458 ms
 *
 * Approximate Formula: T(V, E) = 0.00044 * V + 0.000038 * E (in ms)
 * 
 * We used got the constant by solving system of equations using the largest
 * and middle values of V and E. For example, for |V| = 1000 and |E| = 998001, 
 * which is T = 0.00044*1000 + 0.000038*998001 = 38.7ms which matches.
 * This confirms the formula O(V + E) complexity.
 */ 

public class Assignment4 {
    private int numNodes;
    private LinkedList<Integer>[] adjList;
    private int time;
    private int[] discovery;
    private int[] finishing;
    private String[] color;

    // Constructor to initialize the graph
    public Assignment4(int numNodes) {
        this.numNodes = numNodes;
        adjList = new LinkedList[numNodes];
        for (int i = 0; i < numNodes; i++) {
            adjList[i] = new LinkedList<>();
        }
        discovery = new int[numNodes];
        finishing = new int[numNodes];
        color = new String[numNodes];
    }

    // Add an edge to the graph
    public void addEdge(int u, int v) {
        adjList[u].add(v);
    }

    // Generate random edges for the graph
    public void generateRandomEdges(int numEdges) {
        Random random = new Random();
        for (int i = 0; i < numEdges; i++) {
            int u = random.nextInt(numNodes);
            int v = random.nextInt(numNodes);
            addEdge(u, v);
        }
    }

    // Depth First Search (G)
    public void dfs() {
        Arrays.fill(color, "WHITE");
        time = 0;

        for (int u = 0; u < numNodes; u++) {
            if (color[u].equals("WHITE")) {
                dfsVisit(u); // If vertex u is white, visit
            }
        }
    }

    // DFS visit
    private void dfsVisit(int u) {
        time++; // White vertex u has been discovered
        discovery[u] = time;
        color[u] = "GRAY";

        for (int v : adjList[u]) { // Explore each edge (u, v)
            if (color[v].equals("WHITE")) {
                dfsVisit(v);
            }
        }
        time++;
        finishing[u] = time;
        color[u] = "BLACK";
    }

    // Discovery and finishing times
    public void displayTimes() {
        System.out.println("Node\tDiscovery\tFinishing"); // Three columns to display node, discovery, and finishing
        for (int i = 0; i < numNodes; i++) { // Iterate through the nodes
            System.out.println(i + "\t" + discovery[i] + "\t\t" + finishing[i]); // Output the values
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of nodes:");
        int numNodes = scanner.nextInt();
        System.out.println("Enter the number of edges:");
        int numEdges = scanner.nextInt();

        // Create graph and generate random edges
        Assignment4 graph = new Assignment4(numNodes);
        graph.generateRandomEdges(numEdges);

        // Perform DFS and measure the running time
        long startTime = System.nanoTime();
        graph.dfs();
        long endTime = System.nanoTime();

        // Discovery and finishing times
        graph.displayTimes();

        // Display running time
        System.out.println("DFS running time: " + (endTime - startTime) / 1e6 + " ms");

        scanner.close();
    }
}
