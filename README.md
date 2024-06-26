# Traveling Salesman Problem (TSP) Solver

This project implements the Traveling Salesman Problem (TSP) using the Dynamic Programming-based Held-Karp algorithm in Java. The TSP is a classic algorithmic problem in the fields of computer science and operations research. The problem asks for the shortest possible route that visits each house exactly once and returns to the origin house.

## Features

- Calculates the optimal tour length for a set of houses.
- Prints the most efficient route to visit all houses.
- Displays the total number of possible routes considered.
- Measures and prints the execution time for finding the optimal route.

## Code Overview

### Main Class: `TravelingSalesmanProblem5`

This class contains the main logic for solving the TSP using the Held-Karp algorithm.

#### Methods

1. **`tspHeldKarp`**: This method initializes the memoization table, starts the timer, and calls the recursive `heldKarp` method to solve the TSP. It also reconstructs the optimal tour, calculates the execution time, and prints the number of possible routes considered.

2. **`heldKarp`**: This is a recursive method that solves subproblems of the TSP. It uses memoization to store already computed results to avoid redundant calculations.

3. **`printHouseRoute`**: This method prints the most efficient route to visit all houses and the total time traveled. It takes the optimal tour and the distance matrix as inputs.

4. **`getLocationLabel`**: This utility method returns the label corresponding to a house index (e.g., 0 -> 'A').

5. **`getLocationIntersection`**: This utility method returns the distance between two houses from the distance matrix.

6. **`main`**: The main method provides an example of house distances, calls the `tspHeldKarp` method to find the optimal route, and prints the result.

## Example Usage

```java
public static void main(String[] args) {
    // House distances (example distances)
    int[][] houseTravelTime = {
        {0, 30, 15, 22, 12},
        {30, 0, 25, 24, 10},
        {15, 25, 0, 28, 13},
        {22, 24, 28, 0, 8},
        {12, 10, 13, 8, 0}
    };

    // Find the most efficient route and calculate total time traveled
    int[][] routeAndTime = tspHeldKarp(houseTravelTime);

    // Print the most efficient route and total time traveled
    printHouseRoute(routeAndTime, houseTravelTime);
}
```

### Output

The program outputs the following information:

1. The most efficient route to visit all houses.
2. The distance between each pair of consecutive houses in the route.
3. The total time traveled.
4. The total number of possible routes considered.
5. The execution time in milliseconds.

## How to Run

1. Ensure you have Java installed on your system.
2. Copy the code into a file named `TravelingSalesmanProblem5.java`.
3. Compile the code using the following command:
   ```
   javac TravelingSalesmanProblem5.java
   ```
4. Run the compiled program using the following command:
   ```
   java TravelingSalesmanProblem5
   ```

## Notes

- The number of houses (V) is currently set to 5. You can modify this number and the corresponding distance matrix as needed.
- The distance matrix should be a symmetric matrix with zeroes on the diagonal.

## License

This project is licensed under the MIT License.
