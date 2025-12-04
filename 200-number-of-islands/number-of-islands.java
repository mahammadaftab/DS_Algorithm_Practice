class Solution {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numRows = grid.length;
        int numCols = grid[0].length;
        int numIslands = 0;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (grid[i][j] == '1') {
                    numIslands++;
                    dfs(grid, i, j);
                }
            }
        }

        return numIslands;
    }

    private void dfs(char[][] grid, int row, int col) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        // Check for out-of-bounds or water
        if (row < 0 || row >= numRows || col < 0 || col >= numCols || grid[row][col] == '0') {
            return;
        }

        // Mark the current cell as visited (water)
        grid[row][col] = '0';

        // Explore adjacent cells
        dfs(grid, row + 1, col); // Down
        dfs(grid, row - 1, col); // Up
        dfs(grid, row, col + 1); // Right
        dfs(grid, row, col - 1); // Left
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example usage
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println("Number of islands in grid1: " + solution.numIslands(grid1)); // Expected: 1

        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println("Number of islands in grid2: " + solution.numIslands(grid2)); // Expected: 3
    }
}

