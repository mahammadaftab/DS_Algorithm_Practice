class Solution {
    public int specialTriplets(int[] nums) {
        long MOD = 1_000_000_007L;
        
        // Use arrays for frequency since constraints are typically nums[i] <= 10^5.
        // If the problem constraints allow larger numbers, use HashMap instead.
        int MAX_VAL = 100001; 
        int[] rightFreq = new int[MAX_VAL];
        int[] leftFreq = new int[MAX_VAL];
        
        // Step 1: Populate rightFreq with all numbers initially
        for (int num : nums) {
            if (num < MAX_VAL) {
                rightFreq[num]++;
            }
        }
        
        long totalTriplets = 0;
        
        // Step 2: Iterate through the array treating each element as the 'middle' (nums[j])
        for (int num : nums) {
            // Remove current element from 'right' because it is now the pivot 'j'
            if (num < MAX_VAL) {
                rightFreq[num]--;
            }
            
            int target = num * 2;
            
            if (target < MAX_VAL) {
                long leftCount = leftFreq[target];
                long rightCount = rightFreq[target];
                
                if (leftCount > 0 && rightCount > 0) {
                    totalTriplets = (totalTriplets + (leftCount * rightCount)) % MOD;
                }
            }
            
            if (num < MAX_VAL) {
                leftFreq[num]++;
            }
        }
        return (int) totalTriplets;
    }
}