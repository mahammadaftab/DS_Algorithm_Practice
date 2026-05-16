class Solution:
    def findMin(self, nums: list[int]) -> int:
        left, right = 0, len(nums) - 1
        
        while left < right:
            mid = left + (right - left) // 2
            
            if nums[mid] > nums[right]:
                # The minimum must be to the right of mid
                left = mid + 1
            elif nums[mid] < nums[right]:
                # The minimum is at mid or to the left of mid
                right = mid
            else:
                # nums[mid] == nums[right]
                # We cannot be sure which half it is in, but we can safely discard the rightmost duplicate
                right -= 1
                
        # left and right converge on the minimum element
        return nums[left]

