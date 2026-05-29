class Solution:
    def minElement(self, nums: list[int]) -> int:
        min_element = float('inf')
        
        for num in nums:
            digit_sum = 0
            temp = num
            
            # Extract digits mathematically
            while temp > 0:
                digit_sum += temp % 10
                temp //= 10
                
            # Track the absolute minimum digit sum found
            if digit_sum < min_element:
                min_element = digit_sum
                
        return min_element