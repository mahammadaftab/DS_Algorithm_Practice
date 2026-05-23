class Solution:
    def check(self, nums: list[int]) -> bool:
        drops = 0
        n = len(nums)
        
        for i in range(n):
            # Compare current element with the NEXT element circularly
            if nums[i] > nums[(i + 1) % n]:
                drops += 1
                
            # Optimization: The moment we see more than 1 drop, it's invalid
            if drops > 1:
                return False
                
        return True
