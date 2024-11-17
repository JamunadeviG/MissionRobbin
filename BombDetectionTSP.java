import java.util.*;

public class BombDetectionTSP {

    // Function to calculate the minimum cost using brute-force TSP
    public static int tsp(int[][] distances, int currentPosition, boolean[] visited, int count, int cost, int start) {
        int V = distances.length;

        // If all locations are visited, return to the start
        if (count == V && distances[currentPosition][start] > 0) {
            return cost + distances[currentPosition][start];
        }

        int minCost = Integer.MAX_VALUE;

        // Try visiting all unvisited locations
        for (int i = 0; i < V; i++) {
            if (!visited[i] && distances[currentPosition][i] > 0) {
                // Mark the city as visited
                visited[i] = true;

                // Recur to calculate the path cost
                int tempCost = tsp(distances, i, visited, count + 1, cost + distances[currentPosition][i], start);

                // Update the minimum cost
                minCost = Math.min(minCost, tempCost);

                // Backtrack
                visited[i] = false;
            }
        }
        return minCost;
    }

    public static void main(String[] args) {
        // Example adjacency matrix representing distances
        // 0 is the distance from a node to itself
        int[][] distances = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        // Starting point (Jayasurya's location)
        int start = 0;

        // Number of locations (including Jayasurya's place and bomb sites)
        int V = distances.length;

        // Array to track visited locations
        boolean[] visited = new boolean[V];
        visited[start] = true;

        // Calculate the minimum cost path
        int minCost = tsp(distances, start, visited, 1, 0, start);

        // Output the result
        System.out.println("Minimum cost for the dog to detect bombs and return: " + minCost);
    }
}
