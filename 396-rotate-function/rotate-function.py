class Solution:
    def maxRotateFunction(self, nums: list[int]) -> int:
        n = len(nums)
        S = sum(nums)
        
        # Calculate F(0)
        curr_f = sum(i * num for i, num in enumerate(nums))
        max_f = curr_f
        
        # Calculate F(1) through F(n-1) using the O(1) transition formula
        for k in range(1, n):
            # nums[n - k] gives us the element that just rotated from the back to the front
            curr_f = curr_f + S - (n * nums[n - k])
            max_f = max(max_f, curr_f)
            
        return max_f
