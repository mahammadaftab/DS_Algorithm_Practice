import java.util.Arrays;

public class Solution {

    /**
     * LeetCode Problem 698: Partition to K Equal Sum Subsets
     *
     * Given an integer array nums and an integer k, determine whether it is possible to divide this array into k non-empty subsets whose sums are all equal.
     *
     * Example 1:
     * Input: nums = [4,3,2,3,5,2,1], k = 4
     * Output: true
     * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
     *
     * Example 2:
     * Input: nums = [1,2,3,4], k = 3
     * Output: false
     */

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false; // Sum not divisible by k, impossible to partition
        }

        int target = sum / k; // Each subset should sum up to target
        int n = nums.length;
        boolean[] used = new boolean[n]; // To keep track of used numbers

        Arrays.sort(nums); // Sorting can improve performance in some cases. Can be removed if needed
        //optimization, if largest number is greater than target, can't partition
        if(nums[n-1] > target){
            return false;
        }
        return canPartition(nums, k, 0, 0, target, used);
    }

    private boolean canPartition(int[] nums, int k, int currentSum, int start, int target, boolean[] used) {
        if (k == 1) {
            return true; // If only one subset left, it must be possible
        }

        if (currentSum == target) {
            // Found a subset with the target sum, try to find the next subset
            return canPartition(nums, k - 1, 0, 0, target, used);
        }

        for (int i = start; i < nums.length; i++) {
            if (used[i] || currentSum + nums[i] > target) {
                continue; // Skip used numbers and numbers that exceed the target
            }

            used[i] = true; // Mark the number as used
            if (canPartition(nums, k, currentSum + nums[i], i + 1, target, used)) {
                return true; // Found a valid partition
            }
            used[i] = false; // Backtrack: unmark the number and try a different path
        }

        return false; // No valid partition found
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {4, 3, 2, 3, 5, 2, 1};
        int k1 = 4;
        System.out.println("Partition to K Equal Sum Subsets: " + solution.canPartitionKSubsets(nums1, k1)); // Output: true

        int[] nums2 = {1, 2, 3, 4};
        int k2 = 3;
        System.out.println("Partition to K Equal Sum Subsets: " + solution.canPartitionKSubsets(nums2, k2)); // Output: false

        int[] nums3 = {2,2,2,2,3,4,5};
        int k3 = 4;
        System.out.println("Partition to K Equal Sum Subsets: " + solution.canPartitionKSubsets(nums3, k3)); // Output: false

        int[] nums4 = {10,10,10,7,7,7,7,7,6,6,6};
        int k4 = 3;
         System.out.println("Partition to K Equal Sum Subsets: " + solution.canPartitionKSubsets(nums4, k4)); // Output: true
    }
}