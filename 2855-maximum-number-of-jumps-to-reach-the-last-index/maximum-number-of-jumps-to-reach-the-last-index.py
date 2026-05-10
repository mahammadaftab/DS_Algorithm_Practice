class Solution:
    def maximumJumps(self, nums: list[int], target: int) -> int:
        n = len(nums)
        # Initialize DP array with -1 (unreachable)
        dp = [-1] * n
        # Base case: We start at index 0 with 0 jumps
        dp[0] = 0
        
        # Outer loop: the index we are jumping FROM
        for i in range(n):
            if dp[i] == -1:
                continue  # Skip if we can't even reach this starting pad
                
            # Inner loop: the index we are jumping TO
            for j in range(i + 1, n):
                if abs(nums[j] - nums[i]) <= target:
                    # Update destination with the maximum jumps found so far
                    dp[j] = max(dp[j], dp[i] + 1)
                    
        return dp[n - 1]
