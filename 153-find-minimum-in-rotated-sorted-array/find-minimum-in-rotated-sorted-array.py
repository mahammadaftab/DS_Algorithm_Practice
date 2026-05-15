class Solution:
    def findMin(self, nums: list[int]) -> int:
        left, right = 0, len(nums) - 1
        
        while left < right:
            mid = left + (right - left) // 2
            
            # Compare middle element to the rightmost element
            if nums[mid] > nums[right]:
                # The minimum must be to the right of mid
                left = mid + 1
            else:
                # The minimum is at mid or to the left of mid
                right = mid
                
        # left and right converge on the minimum element
        return nums[left]

