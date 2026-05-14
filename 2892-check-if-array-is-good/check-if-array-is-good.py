class Solution:
    def isGood(self, nums: list[int]) -> bool:
        # Sort the array in ascending order
        nums.sort()
        
        n = len(nums) - 1
        
        # Check the sequence from 1 to n-1
        for i in range(n):
            if nums[i] != i + 1:
                return False
                
        # Check the final duplicate maximum element
        return nums[-1] == n
