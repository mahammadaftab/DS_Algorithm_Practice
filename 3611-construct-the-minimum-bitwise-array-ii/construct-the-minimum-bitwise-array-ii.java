import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int val = nums.get(i);
            
            if (val == 2) { 
                ans[i] = -1;
            } else if (val % 2 == 0) {
                // Any even number cannot be formed by x | (x+1)
                ans[i] = -1;
            } else {
                // Use long to prevent overflow during 'val + 1' if val is Integer.MAX_VALUE
                long nextVal = (long) val + 1;
                
                // Get the lowest set bit of (val + 1)
                long lowBit = nextVal & -nextVal;
                
                // The bit we want to remove from 'val' is lowBit shifted right by 1
                ans[i] = val - (int)(lowBit >> 1);
            }
        }
        
        return ans;
    }
}

