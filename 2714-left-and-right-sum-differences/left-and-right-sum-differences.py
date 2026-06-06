class Solution:
    def leftRightDifference(self, nums: list[int]) -> list[int]:
        total_sum = sum(nums)
        left_sum = 0
        answer = []
        
        for num in nums:
            # Calculate right sum using our mathematical balance formula
            right_sum = total_sum - left_sum - num
            
            # Calculate the absolute difference and store it
            answer.append(abs(left_sum - right_sum))
            
            # Update the running left sum for the next index position
            left_sum += num
            
        return answer

