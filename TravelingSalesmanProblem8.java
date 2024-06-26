package assignment2;

import java.util.Arrays;

public class TravelingSalesmanProblem8 {

    // Number of houses
    static int V = 8;

    // Define infinity as a large enough value
    static int INF = Integer.MAX_VALUE;

    // Counter to keep track of the number of possible routes considered
    static int routesConsidered = 0;

    // Solves the Traveling Salesman Problem (TSP) using the Dynamic Programming-based Held-Karp algorithm
    public static int[][] tspHeldKarp(int[][] graph) {
        // Create a memoization table for subproblem solutions
        int[][] memo = new int[V][(1 << V)];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Initialize base case: distance from starting house to itself is 0
        memo[0][1] = 0;

        // Record the start time in milliseconds
        long startTime = System.nanoTime();

        // Solve subproblems and compute optimal tour length
        int optimalTourLength = heldKarp(0, (1 << V) - 1, graph, memo);

        // Reconstruct the optimal tour
        int[] tour = new int[V + 1];
        int currentHouse = 0;
        int state = (1 << V) - 1;

        int index = 1; // Starting from index 1 to insert house A (0)
        tour[0] = 0; // House A (0) at the beginning of the tour

        while (state != 0) {
            int nextHouse = -1;
            for (int house = 0; house < V; house++) {
                if ((state & (1 << house)) != 0 && (nextHouse == -1 || memo[currentHouse][state] - graph[currentHouse][house] == memo[house][state ^ (1 << house)])) {
                    nextHouse = house;
                }
            }
            tour[index++] = nextHouse;
            state ^= (1 << nextHouse);
            currentHouse = nextHouse;
        }
        
        // Record the end time in milliseconds
        long endTime = System.nanoTime();

        // Print the total number of possible routes considered
        System.out.println("Total number of possible routes considered: " + routesConsidered);

        // Calculate the execution time in milliseconds with three decimal places
        double executionTime = (double) (endTime - startTime) / 1_000_000.0;

        // Print the execution time in milliseconds
        System.out.println("Execution time: " + String.format("%.3f", executionTime) + " milliseconds");

        // Return both optimal tour length and tour sequence
        return new int[][]{{optimalTourLength}, tour};
    }

    // Recursive function for solving subproblems in the Held-Karp algorithm
    public static int heldKarp(int currentHouse, int state, int[][] graph, int[][] memo) {
        // Increment the counter for each subproblem solved
        routesConsidered++;

        // If the subproblem has already been solved, return the memoized value
        if (memo[currentHouse][state] != -1) {
            return memo[currentHouse][state];
        }

        // Base case: all houses have been visited
        if (state == 0) {
            return graph[currentHouse][0];
        }

        int minDistance = INF;

        // Iterate over all possible next houses
        for (int nextHouse = 0; nextHouse < V; nextHouse++) {
            // If the next house has not been visited
            if ((state & (1 << nextHouse)) != 0) {
                int distance = graph[currentHouse][nextHouse] + heldKarp(nextHouse, state ^ (1 << nextHouse), graph, memo);
                minDistance = Math.min(minDistance, distance);
            }
        }

        // Memoize the result
        memo[currentHouse][state] = minDistance;
        return minDistance;
    }

    // Prints the most efficient route to visit all houses
    public static void printHouseRoute(int[][] routeAndTime, int[][] graph) {
        int optimalTourLength = routeAndTime[0][0];
        int[] route = Arrays.copyOfRange(routeAndTime[1], 0, V + 1);

        System.out.println("The most efficient route to visit all houses:");
        for (int i = 0; i < V; i++) {
            System.out.print(getLocationLabel(route[i]) + " ");
        }
        System.out.println(getLocationLabel(route[V]) + " ");
        System.out.println("The Value Of Each house Travel Time");
        for (int i = 0; i < V; i++) {
            System.out.println(getLocationLabel(route[i]) + "-" + graph[route[i]][route[i + 1]]);
        }
        System.out.println("Total time traveled: " + optimalTourLength);
    }

    // Returns the location label corresponding to the house index
    public static char getLocationLabel(int houseIndex) {
        return (char) ('A' + houseIndex);
    }

    // Returns the value of the location intersection
    public static int getLocationIntersection(int house1, int house2, int[][] graph) {
        return graph[house1][house2];
    }

    // Driver's code
    public static void main(String[] args) {
        // House distances (example distances)
        int[][] houseTravelTime = {
            {0, 30, 15, 22, 12, 20, 16, 11},
            {30, 0, 25, 24, 10, 13, 17, 25},
            {15, 25, 0, 28, 13, 18, 23, 29},
            {22, 24, 28, 0, 8, 21, 19, 23},
            {12, 10, 13, 8, 0, 6, 24, 15},
            {20, 13, 18, 21, 6, 0, 23, 14},
            {16, 17, 23, 19, 24, 23, 0, 18},
            {11, 25, 29, 23, 15, 14, 18, 0}
        };

        // Find the most efficient route and calculate total time traveled
        int[][] routeAndTime = tspHeldKarp(houseTravelTime);

        // Print the most efficient route and total time traveled
        printHouseRoute(routeAndTime, houseTravelTime);
    }
}
