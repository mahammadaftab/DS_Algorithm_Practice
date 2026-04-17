class Solution:
    def minMirrorPairDistance(self, nums: list[int]) -> int:
        pos = {}
        min_dist = float('inf')
        
        for j, num in enumerate(nums):
            # 1. Check if there is an earlier 'i' where reverse(nums[i]) == nums[j]
            if num in pos:
                min_dist = min(min_dist, j - pos[num])
                
            # 2. Store the reversed value of the current number for future 'j's to match against
            # str(num)[::-1] converts to string, reverses it, then int() drops leading zeros
            rev_num = int(str(num)[::-1])
            pos[rev_num] = j
            
        return min_dist if min_dist != float('inf') else -1

