import java.util.Arrays;

class Solution {
    /**
     * Given an array nums containing n distinct numbers in the range [0, n],
     * return the only number in the range that is missing from the array.
     *
     * Example 1:
     * Input: nums = [3,0,1]
     */
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;

        for (int num : nums) {
            actualSum += num;
        }

        return expectedSum - actualSum;
    }

    public int missingNumberBitManipulation(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        int[] nums1 = {3, 0, 1};
        System.out.println("Missing number in " + Arrays.toString(nums1) + ": " + solution.missingNumber(nums1));
        System.out.println("Missing number in " + Arrays.toString(nums1) + " (Bit Manipulation): " + solution.missingNumberBitManipulation(nums1));

        int[] nums2 = {0, 1};
        System.out.println("Missing number in " + Arrays.toString(nums2) + ": " + solution.missingNumber(nums2));
        System.out.println("Missing number in " + Arrays.toString(nums2) + " (Bit Manipulation): " + solution.missingNumberBitManipulation(nums2));

        int[] nums3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println("Missing number in " + Arrays.toString(nums3) + ": " + solution.missingNumber(nums3));
        System.out.println("Missing number in " + Arrays.toString(nums3) + " (Bit Manipulation): " + solution.missingNumberBitManipulation(nums3));
    }
}