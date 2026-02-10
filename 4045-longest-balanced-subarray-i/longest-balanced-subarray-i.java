import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int maxLen = 0;
        
        for (int i = 0; i < n; i++) {
            Set<Integer> seen = new HashSet<>();
            int distinctEven = 0;
            int distinctOdd = 0;
            
            for (int j = i; j < n; j++) {
                // Only process if the number is new to this subarray
                if (seen.add(nums[j])) {
                    if (nums[j] % 2 == 0) {
                        distinctEven++;
                    } else {
                        distinctOdd++;
                    }
                }
                
                // Check if distinct counts are equal
                if (distinctEven == distinctOdd) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        
        return maxLen;
    }
}
