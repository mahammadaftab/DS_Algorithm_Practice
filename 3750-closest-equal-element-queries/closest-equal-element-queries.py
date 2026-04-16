from collections import defaultdict

class Solution:
    def solveQueries(self, nums: list[int], queries: list[int]) -> list[int]:
        n = len(nums)
        
        # 1. Group indices by value
        pos_map = defaultdict(list)
        for i, num in enumerate(nums):
            pos_map[num].append(i)
            
        # 2. Precalculate the nearest distance for every index
        # We default to -1 so unique elements are automatically handled
        min_dist = [-1] * n
        
        for indices in pos_map.values():
            size = len(indices)
            if size == 1:
                continue
                
            for i in range(size):
                idx = indices[i]
                
                # Get the immediate left and right neighbor indices in the list
                left_idx = indices[(i - 1) % size]
                right_idx = indices[(i + 1) % size]
                
                # Calculate circular distances 
                dist_left = (idx - left_idx) % n
                dist_right = (right_idx - idx) % n
                
                # The minimum of the two is the closest equal element
                min_dist[idx] = min(dist_left, dist_right)
                
        # 3. Answer queries instantly in O(1) time
        return [min_dist[q] for q in queries]