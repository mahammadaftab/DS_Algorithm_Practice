class Solution:
    def minimumDistance(self, nums: list[int]) -> int:
        first = {}
        second = {}
        min_dist = float('inf')
        
        for i, val in enumerate(nums):
            if val in second:
                # We hit a 3rd occurrence! Calculate the distance formula 2 * (k - i)
                min_dist = min(min_dist, 2 * (i - first[val]))
                
                # Slide the tracking window forward
                first[val] = second[val]
                second[val] = i
                
            elif val in first:
                # 2nd occurrence
                second[val] = i
            else:
                # 1st occurrence
                first[val] = i
                
        return min_dist if min_dist != float('inf') else -1
