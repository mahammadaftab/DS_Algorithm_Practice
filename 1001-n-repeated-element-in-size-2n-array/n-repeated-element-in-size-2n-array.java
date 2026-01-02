class Solution {
    public int repeatedNTimes(int[] nums) {
        // We check every element against its next 2 neighbors.
        // The repeated element is guaranteed to be found within distance 2.
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] == nums[i + 1] || nums[i] == nums[i + 2]) {
                return nums[i];
            }
        }
        
        // If we haven't found it by the end of the loop, 
        // the repeated element must be the very last one.
        // Consider the case [2, 1, 2, 5] -> loop checks index 0, 1. 
        // Returns 2.
        // Consider edge case [1, 2, 3, 1] -> loop checks index 0, 1. 
        // 1==2? No. 1==3? No. Returns nums[3] which is 1.
        return nums[nums.length - 1];
    }
}







