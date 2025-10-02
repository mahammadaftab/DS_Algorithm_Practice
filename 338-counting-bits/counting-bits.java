import java.util.Arrays;

public class Solution {

    /**
     * LeetCode Problem 338: Counting Bits
     * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.
     *
     * Example 1:
     * Input: n = 2
     * Output: [0,1,1]
     * Explanation:
     * 0 --> 0
     * 1 --> 1
     * 2 --> 10
     *
     * Example 2:
     * Input: n = 5
     * Output: [0,1,1,2,1,2]
     * Explanation:
     * 0 --> 0
     * 1 --> 1
     * 2 --> 10
     * 3 --> 11
     * 4 --> 100
     * 5 --> 101
     *
     * Constraints:
     * 0 <= n <= 10^5
     *
     * @param n The integer up to which to count the number of set bits.
     * @return An array where each element at index i contains the number of set bits in i.
     */
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];

        // Base case: number of 1s in 0 is 0
        ans[0] = 0;

        // Iterate from 1 to n
        for (int i = 1; i <= n; i++) {
            // If i is even, then the number of 1s in i is the same as the number of 1s in i/2.
            // If i is odd, then the number of 1s in i is the number of 1s in i/2 plus 1.
            ans[i] = ans[i >> 1] + (i & 1); // i >> 1 is equivalent to i/2, i & 1 is equivalent to i%2
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        int n1 = 2;
        int[] result1 = solution.countBits(n1);
        System.out.println("Counting Bits for n = " + n1 + ": " + Arrays.toString(result1)); // Output: [0, 1, 1]

        int n2 = 5;
        int[] result2 = solution.countBits(n2);
        System.out.println("Counting Bits for n = " + n2 + ": " + Arrays.toString(result2)); // Output: [0, 1, 1, 2, 1, 2]
    }
}
