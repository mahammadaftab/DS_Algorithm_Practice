from collections import defaultdict

class Solution:
    def distance(self, nums: list[int]) -> list[int]:
        # 1. Group indices by their values
        pos_map = defaultdict(list)
        for i, num in enumerate(nums):
            pos_map[num].append(i)
            
        n = len(nums)
        ans = [0] * n
        
        # 2. Process each group of identical values
        for indices in pos_map.values():
            total_sum = sum(indices)
            left_sum = 0
            size = len(indices)
            
            for i in range(size):
                curr_idx = indices[i]
                
                # Calculate the sum of elements strictly to the right
                right_sum = total_sum - left_sum - curr_idx
                
                # Number of elements to the left and right
                left_count = i
                right_count = size - 1 - i
                
                # Apply the algebraic grouping formulas
                left_dist = (left_count * curr_idx) - left_sum
                right_dist = right_sum - (right_count * curr_idx)
                
                # Store the total distance 
                ans[curr_idx] = left_dist + right_dist
                
                # Add current index to the left sum for the next iteration
                left_sum += curr_idx
                
        return ans
