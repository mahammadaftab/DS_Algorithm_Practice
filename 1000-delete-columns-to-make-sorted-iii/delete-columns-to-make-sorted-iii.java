import java.util.Arrays;

class Solution {
    public int minDeletionSize(String[] strs) {
        int rows = strs.length;
        int cols = strs[0].length();
        
        // dp[i] stores the max length of an increasing subsequence ending at column i
        int[] dp = new int[cols];
        
        // Initialize DP: each column is at least a subsequence of length 1 (itself)
        Arrays.fill(dp, 1);
        
        int maxKept = 1;
        
        // Iterate through every column 'i'
        for (int i = 0; i < cols; i++) {
            // Check all previous columns 'j'
            for (int j = 0; j < i; j++) {
                
                // Verify if column 'j' can validly come before column 'i'
                boolean canExtend = true;
                for (int r = 0; r < rows; r++) {
                    if (strs[r].charAt(j) > strs[r].charAt(i)) {
                        canExtend = false;
                        break;
                    }
                }
                
                // If valid, update dp[i]
                if (canExtend) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // Track the global maximum sequence length found so far
            maxKept = Math.max(maxKept, dp[i]);
        }
        
        // The answer is total columns minus the maximum we can keep
        return cols - maxKept;
    }
}