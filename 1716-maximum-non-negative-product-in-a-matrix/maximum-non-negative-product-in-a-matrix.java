class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Use long arrays to prevent overflow before the final modulo
        long[][] maxDP = new long[m][n];
        long[][] minDP = new long[m][n];
        
        // Base case: starting point
        maxDP[0][0] = grid[0][0];
        minDP[0][0] = grid[0][0];
        
        // Initialize the first column (can only come from above)
        for (int i = 1; i < m; i++) {
            maxDP[i][0] = maxDP[i-1][0] * grid[i][0];
            minDP[i][0] = minDP[i-1][0] * grid[i][0];
        }
        
        // Initialize the first row (can only come from the left)
        for (int j = 1; j < n; j++) {
            maxDP[0][j] = maxDP[0][j-1] * grid[0][j];
            minDP[0][j] = minDP[0][j-1] * grid[0][j];
        }
        
        // Fill out the rest of the grid
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int curr = grid[i][j];
                
                // Calculate all 4 possible products to reach this cell
                long p1 = maxDP[i-1][j] * curr; // Max from above
                long p2 = minDP[i-1][j] * curr; // Min from above
                long p3 = maxDP[i][j-1] * curr; // Max from left
                long p4 = minDP[i][j-1] * curr; // Min from left
                
                // Find the new max and min
                maxDP[i][j] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
                minDP[i][j] = Math.min(Math.min(p1, p2), Math.min(p3, p4));
            }
        }
        
        long ans = maxDP[m-1][n-1];
        
        // If the max product is negative, return -1 as per instructions
        if (ans < 0) {
            return -1;
        }
        
        // Apply modulo only at the very end
        return (int) (ans % 1_000_000_007);
    }
}

