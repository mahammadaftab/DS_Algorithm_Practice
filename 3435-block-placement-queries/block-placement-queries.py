from sortedcontainers import SortedList

class MaxFenwickTree:
    def __init__(self, size):
        self.tree = [0] * (size + 1)
        
    def update(self, i, val):
        while i < len(self.tree):
            self.tree[i] = max(self.tree[i], val)
            i += i & -i
            
    def query(self, i):
        max_val = 0
        while i > 0:
            max_val = max(max_val, self.tree[i])
            i -= i & -i
        return max_val

class Solution:
    def getResults(self, queries: list[list[int]]) -> list[bool]:
        # Maximum possible coordinate space needed based on constraints
        max_coord = min(50000, len(queries) * 3)
        
        # 1. Gather all obstacles with boundary sentinels
        obstacles = SortedList([0, max_coord])
        for q in queries:
            if q[0] == 1:
                obstacles.add(q[1])
                
        # 2. Populate the initial Fenwick Tree with full layout gaps
        bit = MaxFenwickTree(max_coord)
        for i in range(len(obstacles) - 1):
            x1, x2 = obstacles[i], obstacles[i + 1]
            bit.update(x2, x2 - x1)
            
        ans = []
        
        # 3. Process queries backwards
        for q in reversed(queries):
            q_type = q[0]
            x = q[1]
            
            if q_type == 1:
                # Obstacle removal: find neighbors
                idx = obstacles.index(x)
                prev_obs = obstacles[idx - 1]
                next_obs = obstacles[idx + 1]
                
                # Merge the gaps and update the Fenwick Tree
                bit.update(next_obs, next_obs - prev_obs)
                obstacles.remove(x)
            else:
                sz = q[2]
                # Find the largest obstacle position <= x
                idx = obstacles.bisect_right(x)
                prev_obs = obstacles[idx - 1]
                
                # Check maximum gap before prev_obs OR the partial gap up to x
                max_gap_before = bit.query(prev_obs)
                partial_gap = x - prev_obs
                
                ans.append(max_gap_before >= sz or partial_gap >= sz)
                
        return ans[::-1]