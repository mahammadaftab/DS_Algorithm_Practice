import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        // Queue to store the coordinates of rotten oranges
        Queue<int[]> rottenOranges = new LinkedList<>();

        // Count of fresh oranges
        int freshOranges = 0;

        // Iterate through the grid to find rotten and fresh oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    // Add rotten oranges to the queue
                    rottenOranges.offer(new int[] { i, j });
                } else if (grid[i][j] == 1) {
                    // Increment the count of fresh oranges
                    freshOranges++;
                }
            }
        }

        // If there are no fresh oranges initially, return 0
        if (freshOranges == 0) {
            return 0;
        }

        // Directions to move (up, down, left, right)
        int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        // Minutes elapsed
        int minutes = 0;

        // Breadth-First Search (BFS)
        while (!rottenOranges.isEmpty()) {
            int size = rottenOranges.size();

            // Process all oranges at the current level (minute)
            for (int i = 0; i < size; i++) {
                int[] current = rottenOranges.poll();
                int row = current[0];
                int col = current[1];

                // Explore adjacent cells
                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    // Check if the new cell is within bounds and contains a fresh orange
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == 1) {
                        // Rot the fresh orange
                        grid[newRow][newCol] = 2;

                        // Add the rotten orange to the queue
                        rottenOranges.offer(new int[] { newRow, newCol });

                        // Decrement the count of fresh oranges
                        freshOranges--;
                    }
                }
            }

            // Increment the minutes elapsed if any oranges were rotten
            if (!rottenOranges.isEmpty()) {
                minutes++;
            }
        }

        // If there are still fresh oranges left, it's impossible to rot them all
        if (freshOranges > 0) {
            return -1;
        } else {
            return minutes;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid1 = {{2,1,1},{1,1,0},{0,1,1}};
        int result1 = sol.orangesRotting(grid1);  // Expected: 4
        System.out.println("Result 1: " + result1);

        int[][] grid2 = {{2,1,1},{0,1,1},{1,0,1}};
        int result2 = sol.orangesRotting(grid2);  // Expected: -1
        System.out.println("Result 2: " + result2);

        int[][] grid3 = {{0,2}};
        int result3 = sol.orangesRotting(grid3); // Expected: 0
        System.out.println("Result 3: " + result3);
    }
}
