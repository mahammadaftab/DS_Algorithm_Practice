class Solution:
    def minMoves(self, nums: list[int], limit: int) -> int:
        n = len(nums)
        # Array to store the differences in moves. 
        # Size is 2 * limit + 2 to safely handle (B + limit + 1) without out-of-bounds.
        delta = [0] * (2 * limit + 2)
        
        for i in range(n // 2):
            a = nums[i]
            b = nums[n - 1 - i]
            
            # Ensure A is the smaller number
            if a > b:
                a, b = b, a
                
            # Calculate boundary thresholds
            left_1_move = a + 1
            exact_0_moves = a + b
            right_1_move = exact_0_moves + 1
            right_2_moves = b + limit + 1
            
            # Record the step changes
            delta[2] += 2                  # Starts at 2 moves
            delta[left_1_move] -= 1        # Drops to 1 move
            delta[exact_0_moves] -= 1      # Drops to 0 moves
            delta[right_1_move] += 1       # Goes back to 1 move
            delta[right_2_moves] += 1      # Goes back to 2 moves
            
        current_moves = 0
        min_moves = float('inf')
        
        # Sweep through all possible sums to find the minimum required moves
        for target_sum in range(2, 2 * limit + 1):
            current_moves += delta[target_sum]
            if current_moves < min_moves:
                min_moves = current_moves
                
        return min_moves
  