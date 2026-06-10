import math
from heapq import heappush, heappop

class SparseTableRMQ:
    def __init__(self, data: list[int]):
        self.n = len(data)
        # Calculate the maximum power of 2 needed
        self.max_log = self.n.bit_length() + 1
        
        self.f_max = [[0] * self.max_log for _ in range(self.n)]
        self.f_min = [[0] * self.max_log for _ in range(self.n)]
        
        # Precompute logarithm tables for O(1) queries
        self.lg = [0] * (self.n + 1)
        for i in range(2, self.n + 1):
            self.lg[i] = self.lg[i >> 1] + 1
            
        # Initialize base cases (intervals of length 1)
        for i in range(self.n):
            self.f_max[i][0] = data[i]
            self.f_min[i][0] = data[i]
            
        # Build Sparse Table layers
        for j in range(1, self.max_log):
            for i in range(self.n - (1 << j) + 1):
                self.f_max[i][j] = max(self.f_max[i][j - 1], self.f_max[i + (1 << (j - 1))][j - 1])
                self.f_min[i][j] = min(self.f_min[i][j - 1], self.f_min[i + (1 << (j - 1))][j - 1])
                
    def query_max(self, l: int, r: int) -> int:
        k = self.lg[r - l + 1]
        return max(self.f_max[l][k], self.f_max[r - (1 << k) + 1][k])
        
    def query_min(self, l: int, r: int) -> int:
        k = self.lg[r - l + 1]
        return min(self.f_min[l][k], self.f_min[r - (1 << k) + 1][k])

class Solution:
    def maxTotalValue(self, nums: list[int], k: int) -> int:
        n = len(nums)
        st = SparseTableRMQ(nums)
        pq = []
        
        # 1. Initialize Heap: Seed it with the maximal window [l, n-1] for every left boundary
        for l in range(n):
            val = st.query_max(l, n - 1) - st.query_min(l, n - 1)
            heappush(pq, (-val, l, n - 1))
            
        ans = 0
        
        # 2. Extract the absolute k-largest subarray ranges greedily
        for _ in range(k):
            if not pq:
                break
            val, l, r = heappop(pq)
            ans += -val
            
            # 3. Transition rule: Shave off the right edge to discover the next optimal sub-window
            if r > l:
                next_val = st.query_max(l, r - 1) - st.query_min(l, r - 1)
                heappush(pq, (-next_val, l, r - 1))
                
        return ans