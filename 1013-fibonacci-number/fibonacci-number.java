/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1.
 */
class Solution {
    /**
     * Calculates the nth Fibonacci number using recursion.
     *
     * @param n The index of the Fibonacci number to calculate.
     * @return The nth Fibonacci number.
     */
    public int fib(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    // Alternative solution using dynamic programming (memoization) for better performance
    public int fibMemo(int n) {
        if (n <= 1) {
            return n;
        }

        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = 1;

        return fibMemoHelper(n, memo);
    }

    private int fibMemoHelper(int n, int[] memo) {
        if (memo[n] != 0) {
            return memo[n];
        }

        memo[n] = fibMemoHelper(n - 1, memo) + fibMemoHelper(n - 2, memo);
        return memo[n];
    }

    // Iterative solution using dynamic programming (bottom-up approach)
    public int fibIterative(int n) {
        if (n <= 1) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}