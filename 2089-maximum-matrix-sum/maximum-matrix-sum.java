class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long totalSum = 0;
        int minAbsVal = Integer.MAX_VALUE;
        int negativeCount = 0;
        
        for (int[] row : matrix) {
            for (int val : row) {
                // Add absolute value to total sum
                totalSum += Math.abs(val);
                
                // Track the smallest absolute value found so far
                minAbsVal = Math.min(minAbsVal, Math.abs(val));
                
                // Count negatives
                if (val < 0) {
                    negativeCount++;
                }
            }
        }
        
        // If we have an even number of negatives, we turn them all positive.
        // If odd, we must subtract 2 * smallest absolute value (once to remove it 
        // from the sum, once to actually subtract it).
        if (negativeCount % 2 != 0) {
            totalSum -= 2 * minAbsVal;
        }
        
        return totalSum;
    }
}
