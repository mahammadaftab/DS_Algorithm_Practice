class Solution:
    def maxTotalValue(self, nums: list[int], k: int) -> int:
        # Simply find the global max and global min in O(N) time
        global_max = max(nums)
        global_min = min(nums)
        
        # We pick the best possible subarray k times
        return (global_max - global_min) * k