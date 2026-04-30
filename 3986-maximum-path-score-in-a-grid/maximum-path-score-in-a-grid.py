class Solution:
    def maxPathScore(self, grid: list[list[int]], k: int) -> int:
        m, n = len(grid), len(grid[0])
        
        # Optimization: The maximum path length sets a hard ceiling on cost
        k = min(k, m + n)
        
        # dp[j][c] stores the max score for reaching column j with exactly cost c
        dp = [[-1] * (k + 1) for _ in range(n)]
        dp[0][0] = 0
        
        for i in range(m):
            for j in range(n):
                if i == 0 and j == 0:
                    continue
                
                cost = 1 if grid[i][j] > 0 else 0
                score = grid[i][j]
                
                new_dp = [-1] * (k + 1)
                
                # Grab references to the previous states (Top and Left)
                top_dp = dp[j] if i > 0 else None
                left_dp = dp[j - 1] if j > 0 else None
                
                # Check all possible valid costs
                for c in range(cost, k + 1):
                    prev_c = c - cost
                    val_top = top_dp[prev_c] if top_dp else -1
                    val_left = left_dp[prev_c] if left_dp else -1
                    
                    best_prev = val_top if val_top > val_left else val_left
                    
                    # If the previous state is reachable, update our new state
                    if best_prev != -1:
                        new_dp[c] = best_prev + score
                        
                # Overwrite the DP array for the next row iteration
                dp[j] = new_dp
                
        # The answer is the maximum score at the destination cell across any valid cost
        ans = max(dp[n - 1])
        return ans if ans != -1 else -1
