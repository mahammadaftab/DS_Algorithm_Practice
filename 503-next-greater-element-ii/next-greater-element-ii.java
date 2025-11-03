import java.util.Arrays;
import java.util.Stack;

class Solution {
    /**
     * LeetCode Problem 503: Next Greater Element II
     *
     * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
     * return the next greater number for every element in nums.
     *
     * The next greater number of a number x is the first greater number to its traversing-order next in the array,
     * which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
     *
     * Example 1:
     * Input: nums = [1,2,1]
     * Output: [2,-1,2]
     * Explanation: The first 1's next greater number is 2;
     * The number 2 can't find next greater number.
     * The second 1's next greater number needs to search circularly, which is also 2.
     *
     * Example 2:
     * Input: nums = [1,2,3,4,3]
     * Output: [2,3,4,-1,4]
     *
     * Constraints:
     * 1 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     *
     * @param nums The input circular array.
     * @return An array containing the next greater element for each element in the input array.
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1); // Initialize result array with -1

        Stack<Integer> stack = new Stack<>(); // Stack to store indices

        // Iterate through the array twice to simulate circular array
        for (int i = 0; i < 2 * n; i++) {
            int num = nums[i % n]; // Get the current element (handle circular indexing)

            // While the stack is not empty and the current element is greater than the element at the top of the stack
            while (!stack.isEmpty() && num > nums[stack.peek()]) {
                int index = stack.pop(); // Pop the index from the stack
                result[index] = num;    // Update the result with the next greater element
            }

            // Push the current index onto the stack if it's within the first half of the iteration (0 <= i < n)
            if (i < n) {
                stack.push(i); // Push the index onto the stack
            }
        }

        return result; // Return the result array
    }

    // Main method for testing (optional)
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1
        int[] nums1 = {1, 2, 1};
        int[] result1 = solution.nextGreaterElements(nums1);
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Output: " + Arrays.toString(result1)); // Expected: [2, -1, 2]

        // Test case 2
        int[] nums2 = {1, 2, 3, 4, 3};
        int[] result2 = solution.nextGreaterElements(nums2);
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("Output: " + Arrays.toString(result2)); // Expected: [2, 3, 4, -1, 4]
    }
}



