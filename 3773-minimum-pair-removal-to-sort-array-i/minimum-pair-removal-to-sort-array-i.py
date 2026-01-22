class Solution:
    def minimumPairRemoval(self, nums: List[int]) -> int:
        # Helper to check if array is sorted
        def is_sorted(arr):
            for i in range(len(arr) - 1):
                if arr[i] > arr[i+1]:
                    return False
            return True
        
        operations = 0
        
        # Simulate until sorted
        while not is_sorted(nums):
            min_sum = float('inf')
            idx = -1
            
            # Find leftmost pair with minimum sum
            for i in range(len(nums) - 1):
                pair_sum = nums[i] + nums[i+1]
                # Strictly less (<) ensures we keep the leftmost index in case of ties
                if pair_sum < min_sum:
                    min_sum = pair_sum
                    idx = i
            
            # Perform the merge operation
            nums[idx] = min_sum
            nums.pop(idx + 1)
            operations += 1
            
        return operations