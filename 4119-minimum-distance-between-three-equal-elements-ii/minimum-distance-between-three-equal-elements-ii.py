class Solution:
    def minimumDistance(self, nums: list[int]) -> int:
        # pos maps value -> [index_of_first_occurrence, index_of_second_occurrence]
        pos = {}
        min_dist = float('inf')
        
        for idx, val in enumerate(nums):
            if val not in pos:
                pos[val] = [idx]
            elif len(pos[val]) == 1:
                pos[val].append(idx)
            else:
                # We have found a third (or fourth, fifth...) occurrence
                # The 'i' is the first element in our list, 'k' is the current 'idx'
                min_dist = min(min_dist, 2 * (idx - pos[val][0]))
                
                # Shift the window: [i, j] becomes [j, k]
                pos[val][0] = pos[val][1]
                pos[val][1] = idx
                
        return min_dist if min_dist != float('inf') else -1