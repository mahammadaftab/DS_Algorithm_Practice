import java.util.Arrays;

class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        // Step 1: Sort the happiness values.
        // Java sorts primitives in ascending order, so the largest values are at the end.
        Arrays.sort(happiness);
        
        long totalHappiness = 0;
        int n = happiness.length;
        
        // Step 2: Iterate k times to pick the top k children
        for (int i = 0; i < k; i++) {
            // Get the largest available value (starting from the end of the array)
            int originalValue = happiness[n - 1 - i];
            
            // Calculate effective happiness: Value - Number of turns passed
            // It cannot be negative.
            long effectiveValue = Math.max(0, originalValue - i);
            
            totalHappiness += effectiveValue;
            
            // Optimization: If the largest remaining value is already <= current penalty,
            // then ALL subsequent values will also result in 0. We can stop early.
            if (effectiveValue == 0) {
                break; 
            }
        }
        
        return totalHappiness;
    }
}



