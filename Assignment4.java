import java.util.*;

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
        System.out.println("Node\tDiscovery\tFinishing");
        for (int i = 0; i < numNodes; i++) {
            System.out.println(i + "\t" + discovery[i] + "\t\t" + finishing[i]);
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

        // Display discovery and finishing times
        graph.displayTimes();

        // Display running time
        System.out.println("DFS running time: " + (endTime - startTime) / 1e6 + " ms");

        scanner.close();
    }
}
