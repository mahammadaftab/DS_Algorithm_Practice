import java.util.Arrays;

class Solution {
    public int minRemoval(int[] nums, int k) {
        // 1. Sort the array to make valid subsets contiguous
        Arrays.sort(nums);
        
        int n = nums.length;
        int maxKept = 0;
        int left = 0;
        
        // 2. Sliding Window
        for (int right = 0; right < n; right++) {
            // While the current window is invalid (max > min * k), shrink from left
            // Use long to prevent overflow for nums[left] * k
            while ((long) nums[right] > (long) nums[left] * k) {
                left++;
            }
            
            // Update the maximum number of elements we can keep
            maxKept = Math.max(maxKept, right - left + 1);
        }
        
        // 3. Answer is total size minus best window size
        return n - maxKept;
    }
}

