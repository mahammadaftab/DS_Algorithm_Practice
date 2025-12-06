import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        int MOD = 1_000_000_007;
        
        // dp[i] = Number of ways to partition the prefix nums[0...i-1]
        int[] dp = new int[n + 1];
        
        // prefixDp[i] = Sum of dp[0]...dp[i-1]
        // This allows us to calculate the sum of a range of dp values in O(1)
        int[] prefixDp = new int[n + 2];
        
        // Base case: Empty prefix has 1 way to be partitioned
        dp[0] = 1;
        prefixDp[1] = 1; // prefixDp[1] stores sum(dp[0])
        
        // Deques to maintain indices of max and min elements in the current window
        Deque<Integer> maxD = new ArrayDeque<>();
        Deque<Integer> minD = new ArrayDeque<>();
        
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            while (!maxD.isEmpty() && nums[maxD.peekLast()] <= nums[right]) {
                maxD.pollLast();
            }
            maxD.offerLast(right);
            
            while (!minD.isEmpty() && nums[minD.peekLast()] >= nums[right]) {
                minD.pollLast();
            }
            minD.offerLast(right);
            
            // 3. Shrink window from the left if condition is violated
            while (nums[maxD.peekFirst()] - nums[minD.peekFirst()] > k) {
                left++;
                // Remove indices that are now out of the window [left, right]
                if (maxD.peekFirst() < left) maxD.pollFirst();
                if (minD.peekFirst() < left) minD.pollFirst();
            }
                        
            long rangeSum = (prefixDp[right + 1] - prefixDp[left] + MOD) % MOD;
            dp[right + 1] = (int) rangeSum;
            
            // 5. Update prefix sum for the next iteration
            prefixDp[right + 2] = (prefixDp[right + 1] + dp[right + 1]) % MOD;
        }
        
        return dp[n];
    }
}