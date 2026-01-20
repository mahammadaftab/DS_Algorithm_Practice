import java.util.List;

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int val = nums.get(i);
            
            if (val % 2 == 0) {
                // If val is even, no x | (x+1) can produce it
                ans[i] = -1;
            } else {
                // Find the lowest set bit of (val + 1)
                // We use long to handle potential Integer.MAX_VALUE overflow
                long next = (long) val + 1;
                long lowBit = next & -next;
                
                // We subtract half of that lowBit to flip the correct bit in val
                ans[i] = val - (int)(lowBit >> 1);
            }
        }
        
        return ans;
    }
}
