class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] p = new int[n][m];
        
        long runningProduct = 1;
        
        // 1. Forward Pass: Calculate product of all elements BEFORE grid[i][j]
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                p[i][j] = (int) runningProduct;
                // Update running product for the next cell, keeping it modulo 12345
                runningProduct = (runningProduct * grid[i][j]) % 12345;
            }
        }
        
        // Reset running product for the backward pass
        runningProduct = 1;
        
        // 2. Backward Pass: Multiply by product of all elements AFTER grid[i][j]
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                // Multiply the prefix (already in p[i][j]) by the suffix (runningProduct)
                p[i][j] = (int) ((p[i][j] * runningProduct) % 12345);
                
                // Update running product for the next cell moving backwards
                runningProduct = (runningProduct * grid[i][j]) % 12345;
            }
        }
        
        return p;
    }
}

