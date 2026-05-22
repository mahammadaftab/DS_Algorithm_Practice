class Solution:
    def search(self, nums: list[int], target: int) -> int:
        left, right = 0, len(nums) - 1
        
        while left <= right:
            mid = left + (right - left) // 2
            
            # Match found
            if nums[mid] == target:
                return mid
                
            # Case 1: Left half is perfectly sorted
            if nums[left] <= nums[mid]:
                # Check if target falls strictly within the sorted left bounds
                if nums[left] <= target < nums[mid]:
                    right = mid - 1
                else:
                    left = mid + 1
                    
            # Case 2: Right half is perfectly sorted
            else:
                # Check if target falls strictly within the sorted right bounds
                if nums[mid] < target <= nums[right]:
                    left = mid + 1
                else:
                    right = mid - 1
                    
        return -1