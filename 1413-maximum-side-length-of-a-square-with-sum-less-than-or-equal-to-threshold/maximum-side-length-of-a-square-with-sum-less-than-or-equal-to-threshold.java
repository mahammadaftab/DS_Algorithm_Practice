class Solution {
    int[][] prefix;
    int m, n;

    public int maxSideLength(int[][] mat, int threshold) {
        m = mat.length;
        n = mat[0].length;
        
        // 1. Build 1-based 2D Prefix Sum Array
        prefix = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefix[i + 1][j + 1] = prefix[i][j + 1] + prefix[i + 1][j] 
                                     - prefix[i][j] + mat[i][j];
            }
        }
        
        // 2. Binary Search for the side length
        int low = 0, high = Math.min(m, n);
        int ans = 0;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid == 0) { // square of size 0 is always valid
                low = mid + 1;
                continue;
            }
            
            if (isValid(mid, threshold)) {
                ans = mid;      // Found a valid square, try larger
                low = mid + 1;
            } else {
                high = mid - 1; // Sum too big, try smaller
            }
        }
        
        return ans;
    }
    
    // Helper to check if ANY square of size k has sum <= threshold
    private boolean isValid(int k, int threshold) {
        // We start i and j at k because a square of size k needs at least index k-1
        for (int i = k; i <= m; i++) {
            for (int j = k; j <= n; j++) {
                int currentSum = prefix[i][j] 
                               - prefix[i - k][j] 
                               - prefix[i][j - k] 
                               + prefix[i - k][j - k];
                
                if (currentSum <= threshold) {
                    return true;
                }
            }
        }
        return false;
    }
}


