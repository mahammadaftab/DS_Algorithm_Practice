class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Precompute prefix sums for rows and columns
        // rowPrefix[i][j] = sum of grid[i][0]...grid[i][j-1]
        int[][] rowPrefix = new int[m][n + 1];
        // colPrefix[i][j] = sum of grid[0][j]...grid[i-1][j]
        int[][] colPrefix = new int[m + 1][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowPrefix[i][j + 1] = rowPrefix[i][j] + grid[i][j];
                colPrefix[i + 1][j] = colPrefix[i][j] + grid[i][j];
            }
        }
        
        // Start checking from the largest possible size k down to 2
        for (int k = Math.min(m, n); k > 1; k--) {
            // Slide the k x k window across the grid
            for (int i = 0; i <= m - k; i++) {
                for (int j = 0; j <= n - k; j++) {
                    if (isMagic(grid, rowPrefix, colPrefix, i, j, k)) {
                        return k;
                    }
                }
            }
        }
        
        return 1; // A 1x1 square is always a magic square
    }
    
    private boolean isMagic(int[][] grid, int[][] rowPrefix, int[][] colPrefix, int r, int c, int k) {
        // 1. Calculate Diagonal Sums
        long d1 = 0, d2 = 0;
        for (int x = 0; x < k; x++) {
            d1 += grid[r + x][c + x];           // Top-left to Bottom-right
            d2 += grid[r + x][c + k - 1 - x];   // Top-right to Bottom-left
        }
        
        if (d1 != d2) return false;
        
        // 2. Check Rows
        for (int x = 0; x < k; x++) {
            long rowSum = rowPrefix[r + x][c + k] - rowPrefix[r + x][c];
            if (rowSum != d1) return false;
        }
        
        // 3. Check Columns
        for (int y = 0; y < k; y++) {
            long colSum = colPrefix[r + k][c + y] - colPrefix[r][c + y];
            if (colSum != d1) return false;
        }
        
        return true;
    }
}



