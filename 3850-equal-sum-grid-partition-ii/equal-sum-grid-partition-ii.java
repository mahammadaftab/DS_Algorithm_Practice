class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        long total = 0;
        for (int[] row : grid) {
            for (int val : row) {
                total += val;
            }
        }
        
        // 1. Horizontal Cut: Discount from Top
        if (check(grid, total)) return true;
        
        // 2. Horizontal Cut: Discount from Bottom
        if (check(reverseRows(grid), total)) return true;
        
        int[][] transposed = transpose(grid);
        
        // 3. Vertical Cut: Discount from Left
        if (check(transposed, total)) return true;
        
        // 4. Vertical Cut: Discount from Right
        if (check(reverseRows(transposed), total)) return true;
        
        return false;
    }
    
    private boolean check(int[][] g, long total) {
        int m = g.length;
        int n = g[0].length;
        long topSum = 0;
        boolean[] seen = new boolean[100001];
        
        // Iterate through possible horizontal cuts (leaving at least 1 row for bottom)
        for (int i = 0; i < m - 1; i++) {
            // Add the current row to our "Top" section
            for (int j = 0; j < n; j++) {
                topSum += g[i][j];
                seen[g[i][j]] = true;
            }
            
            long botSum = total - topSum;
            long diff = topSum - botSum;
            
            // If perfectly equal, no discount needed
            if (diff == 0) return true;
            
            // We only care if we need to remove a positive value from the Top section
            if (diff > 0 && diff <= 100000) {
                int d = (int) diff;
                
                if (i == 0) {
                    // 1D Row Vector: Only left/right endpoints are valid
                    if (g[0][0] == d || g[0][n - 1] == d) return true;
                } else if (n == 1) {
                    // 1D Column Vector: Only top/bottom endpoints are valid
                    if (g[0][0] == d || g[i][0] == d) return true;
                } else {
                    // 2D Matrix (R > 1, C > 1): Any cell is valid
                    if (seen[d]) return true;
                }
            }
        }
        return false;
    }
    
    private int[][] reverseRows(int[][] g) {
        int m = g.length, n = g[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            res[i] = g[m - 1 - i].clone();
        }
        return res;
    }
    
    private int[][] transpose(int[][] g) {
        int m = g.length, n = g[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[j][i] = g[i][j];
            }
        }
        return res;
    }
}
