from functools import lru_cache

class Solution:
    def minimumDistance(self, word: str) -> int:
        def get_dist(a, b):
            if a == 26: return 0  # Initial state
            return abs(a // 6 - b // 6) + abs(a % 6 - b % 6)
        
        # Convert word to indices for speed
        chars = [ord(c) - ord('A') for c in word]
        
        @lru_cache(None)
        def solve(idx, other):
            if idx == len(chars):
                return 0
            
            curr_pos = chars[idx-1] if idx > 0 else 26
            next_pos = chars[idx]
            
            # Option 1: Move the finger that typed the last character
            opt1 = get_dist(curr_pos, next_pos) + solve(idx + 1, other)
            
            # Option 2: Move the 'other' finger
            opt2 = get_dist(other, next_pos) + solve(idx + 1, curr_pos)
            
            return min(opt1, opt2)
        
        return solve(0, 26)

