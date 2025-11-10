import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    /**
     * LeetCode Problem: 239 - Sliding Window Maximum
     * Difficulty: Hard
     * Topics: Stacks & Queues
     *
     * Given an array of integers nums, there is a sliding window of size k which
     * is moving from the very left of the array to the very right. You can only
     * see the k numbers in the window. Each time the sliding window moves right
     * by one position.
     *
     * Return the max sliding window.
     *
     * Example:
     *
     * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
     * Output: [3,3,5,5,6,7]
     *
     * Explanation:
     * Window position                Max
     * ---------------               ----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *
     * Constraints:
     * 1 <= nums.length <= 10^5
     * -10^4 <= nums[i] <= 10^4
     * 1 <= k <= nums.length
     *
     * @param nums The input array of integers.
     * @param k    The size of the sliding window.
     * @return An array containing the maximum value in each sliding window.
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>(); // Store indices

        for (int i = 0; i < n; i++) {
            // Remove elements out of the window
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // Remove elements smaller than the current element
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }

            // Add the current element's index to the deque
            deque.offerLast(i);

            // Add the maximum element to the result
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example usage
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] result = solution.maxSlidingWindow(nums, k);

        System.out.print("Sliding Window Maximum: ");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println(); // Output: 3 3 5 5 6 7
    }
}




