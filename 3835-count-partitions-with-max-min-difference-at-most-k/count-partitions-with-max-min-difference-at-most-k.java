import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        int MOD = 1_000_000_007;
        
        int[] dp = new int[n + 1];
        
        int[] prefixDp = new int[n + 2];
        
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
            
            while (nums[maxD.peekFirst()] - nums[minD.peekFirst()] > k) {
                left++;
                if (maxD.peekFirst() < left) maxD.pollFirst();
                if (minD.peekFirst() < left) minD.pollFirst();
            }
                        
            long rangeSum = (prefixDp[right + 1] - prefixDp[left] + MOD) % MOD;
            dp[right + 1] = (int) rangeSum;
            
            prefixDp[right + 2] = (prefixDp[right + 1] + dp[right + 1]) % MOD;
        }
        
        return dp[n];
    }
}