class Solution:
    def maximumScore(self, grid: list[list[int]]) -> int:
        N = len(grid)
        
        # 1. Precompute prefix sums for O(1) range queries
        # P[c][x] = sum of grid[0...x-1][c]
        P = [[0] * (N + 1) for _ in range(N)]
        for c in range(N):
            for r in range(N):
                P[c][r + 1] = P[c][r] + grid[r][c]
                
        # dp[curr_height][prev_height]
        dp = [[-float('inf')] * (N + 1) for _ in range(N + 1)]
        
        # Base case: column 0. We pretend a hypothetical column -1 had height 0.
        for h0 in range(N + 1):
            dp[h0][0] = 0
            
        for c in range(1, N):
            new_dp = [[-float('inf')] * (N + 1) for _ in range(N + 1)]
            
            for prev in range(N + 1):
                # Maximum DP if we don't apply any right bonus
                max_dp_prev = max(dp[prev])
                
                # Arrays to separate the prev2 dependencies
                V1 = [-float('inf')] * (N + 1)
                V2 = [-float('inf')] * (N + 1)
                
                for prev2 in range(N + 1):
                    if dp[prev][prev2] != -float('inf'):
                        V1[prev2] = dp[prev][prev2]
                        # Subtract the overlapping prefix to avoid double-counting
                        V2[prev2] = dp[prev][prev2] - P[c-1][max(prev2, prev)]
                
                # Suffix max for cases where prev2 >= curr
                suffix_V1 = [-float('inf')] * (N + 2)
                for i in range(N, -1, -1):
                    suffix_V1[i] = max(suffix_V1[i+1], V1[i])
                    
                # Prefix max for cases where prev2 < curr
                prefix_V2 = [-float('inf')] * (N + 2)
                for i in range(N + 1):
                    prefix_V2[i+1] = max(prefix_V2[i], V2[i])
                    
                # Calculate new states in O(1) using our precomputed arrays
                for curr in range(N + 1):
                    # Left bonus: Col c gets bonus from taller Col c-1
                    left_bonus = P[c][prev] - P[c][curr] if prev > curr else 0
                    
                    # Right bonus: Col c-1 gets bonus from taller Col c
                    if curr <= prev:
                        right_bonus_applied = max_dp_prev
                    else:
                        right_bonus_applied = max(
                            suffix_V1[curr], 
                            prefix_V2[curr] + P[c-1][curr]
                        )
                        
                    new_dp[curr][prev] = left_bonus + right_bonus_applied
                    
            dp = new_dp
            
        # The answer is the maximum configuration at the final column
        return max(max(row) for row in dp)