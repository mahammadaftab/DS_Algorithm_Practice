import java.util.Arrays;

class Solution {
    public int minimumCost(int[] nums) {
        // The first element is always part of the cost (head of the 1st subarray)
        int cost = nums[0];
        
        // We need to find the two smallest elements in the rest of the array (nums[1] to end)
        // Since the array size is small (n <= 50), sorting is perfectly fine.
        // For O(N) efficiency, we could just scan for min1 and min2, but Arrays.sort is concise.
        
        // Create a copy of the rest of the array to sort
        int[] rest = Arrays.copyOfRange(nums, 1, nums.length);
        Arrays.sort(rest);
        
        // Add the two smallest available numbers
        cost += rest[0] + rest[1];
        
        return cost;
    }
}
