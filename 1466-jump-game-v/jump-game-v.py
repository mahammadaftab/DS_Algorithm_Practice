class Solution:
    def maxJumps(self, arr: list[int], d: int) -> int:
        n = len(arr)
        dp = [1] * n
        
        # 1. Pair each index with its height and sort by height ascending
        sorted_indices = sorted(range(n), key=lambda i: arr[i])
        
        # 2. Process from shortest to tallest
        for i in sorted_indices:
            # Check valid jumps to the RIGHT
            for j in range(i + 1, min(n, i + d + 1)):
                if arr[j] >= arr[i]:
                    break  # Blocked! Cannot jump to or past this building
                dp[i] = max(dp[i], 1 + dp[j])
                
            # Check valid jumps to the LEFT
            for j in range(i - 1, max(-1, i - d - 1), -1):
                if arr[j] >= arr[i]:
                    break  # Blocked!
                dp[i] = max(dp[i], 1 + dp[j])
                
        return max(dp)
