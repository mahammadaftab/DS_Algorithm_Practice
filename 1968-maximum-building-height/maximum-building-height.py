class Solution:
    def maxBuilding(self, n: int, restrictions: list[list[int]]) -> int:
        # 1. Add baseline boundaries and sort restrictions by building ID
        # Building 1 must start at height 0
        reqs = restrictions + [[1, 0]]
        reqs.sort()
        
        # If the last building isn't explicitly restricted, add a loose boundary
        if reqs[-1][0] != n:
            reqs.append([n, n - 1])
            
        m = len(reqs)
        
        # 2. Left-to-Right Pass: Propagate limits forward
        for i in range(1, m):
            dist = reqs[i][0] - reqs[i-1][0]
            reqs[i][1] = min(reqs[i][1], reqs[i-1][1] + dist)
            
        # 3. Right-to-Left Pass: Propagate limits backward
        for i in range(m - 2, -1, -1):
            dist = reqs[i+1][0] - reqs[i][0]
            reqs[i][1] = min(reqs[i][1], reqs[i+1][1] + dist)
            
        # 4. Find the absolute peak among all intervals
        max_overall_height = 0
        for i in range(m - 1):
            id1, h1 = reqs[i]
            id2, h2 = reqs[i+1]
            dist = id2 - id1
            
            # Mathematical peak calculation between two constrained nodes
            peak = (h1 + h2 + dist) // 2
            max_overall_height = max(max_overall_height, peak)
            
        return max_overall_height