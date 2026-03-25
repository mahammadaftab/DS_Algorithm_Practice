class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        long totalSum = 0;
        long[] rowSums = new long[m];
        long[] colSums = new long[n];
        
        // 1. Calculate row sums, column sums, and the total sum in one pass
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowSums[i] += grid[i][j];
                colSums[j] += grid[i][j];
                totalSum += grid[i][j];
            }
        }
        
        // If the total sum is odd, it cannot be equally divided
        if (totalSum % 2 != 0) {
            return false;
        }
        
        long target = totalSum / 2;
        
        // 2. Check for a valid horizontal cut
        long currentSum = 0;
        // We loop up to m - 1 to ensure the bottom section is non-empty
        for (int i = 0; i < m - 1; i++) {
            currentSum += rowSums[i];
            if (currentSum == target) {
                return true;
            }
        }
        
        // 3. Check for a valid vertical cut
        currentSum = 0;
        // We loop up to n - 1 to ensure the right section is non-empty
        for (int j = 0; j < n - 1; j++) {
            currentSum += colSums[j];
            if (currentSum == target) {
                return true;
            }
        }
        
        return false;
    }
}
