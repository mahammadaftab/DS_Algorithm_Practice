class Solution:
    def maxDistance(self, colors: list[int]) -> int:
        n = len(colors)
        max_dist = 0
        
        # 1. Anchor Left: Find the furthest house from the first house
        for i in range(n - 1, -1, -1):
            if colors[i] != colors[0]:
                max_dist = max(max_dist, i) # i - 0 is just i
                break # We found the furthest, stop scanning
                
        # 2. Anchor Right: Find the furthest house from the last house
        for i in range(n):
            if colors[i] != colors[-1]:
                max_dist = max(max_dist, (n - 1) - i)
                break # We found the furthest, stop scanning
                
        return max_dist
