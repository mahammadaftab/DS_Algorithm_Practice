class Solution:
    def getMinDistance(self, nums: list[int], target: int, start: int) -> int:
        min_dist = float('inf')
        
        for i, num in enumerate(nums):
            if num == target:
                # Calculate the absolute distance and update the minimum
                min_dist = min(min_dist, abs(i - start))
                
        return min_dist