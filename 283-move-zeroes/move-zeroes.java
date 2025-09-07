import java.util.Arrays;

public class Solution {

    /**
     * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     *
     * Note that you must do this in-place without making a copy of the array.
     *
     * Example 1:
     * Input: nums = [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     *
     * Example 2:
     * Input: nums = [0]
     * Output: [0]
     *
     * Constraints:
     * 1 <= nums.length <= 104
     * -104 <= nums[i] <= 104
     *
     * @param nums The integer array to modify.
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int insertPos = 0; // Index to insert the next non-zero element

        // Iterate through the array
        for (int num : nums) {
            // If the current element is not zero
            if (num != 0) {
                // Move it to the insert position
                nums[insertPos++] = num;
            }
        }

        // Fill the remaining positions with zeroes
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        int[] nums1 = {0, 1, 0, 3, 12};
        solution.moveZeroes(nums1);
        System.out.println("nums1: " + Arrays.toString(nums1)); // Expected: [1, 3, 12, 0, 0]

        int[] nums2 = {0};
        solution.moveZeroes(nums2);
        System.out.println("nums2: " + Arrays.toString(nums2)); // Expected: [0]

        int[] nums3 = {1, 0};
        solution.moveZeroes(nums3);
        System.out.println("nums3: " + Arrays.toString(nums3)); // Expected: [1, 0]

        int[] nums4 = {0, 0, 1};
        solution.moveZeroes(nums4);
        System.out.println("nums4: " + Arrays.toString(nums4)); // Expected: [1, 0, 0]
    }
}
