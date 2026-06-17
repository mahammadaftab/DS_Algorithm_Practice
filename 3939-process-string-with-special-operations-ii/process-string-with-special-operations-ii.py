class Solution:
    def processStr(self, s: str, k: int) -> str:
        # 1. Forward Pass: Precompute string lengths step-by-step
        lengths = []
        curr_len = 0
        
        for char in s:
            if char.isalpha():
                curr_len += 1
            elif char == '*':
                if curr_len > 0:
                    curr_len -= 1
            elif char == '#':
                curr_len *= 2
            elif char == '%':
                pass # Reversal does not alter total string length
            
            lengths.append(curr_len)
            
        # Out of bounds escape hatch
        if k >= curr_len or k < 0:
            return '.'
            
        # 2. Backward Pass: Reconstruct target index k via reverse simulation
        for i in range(len(s) - 1, -1, -1):
            char = s[i]
            
            # Fetch previous length before this current operation was applied
            prev_len = lengths[i-1] if i > 0 else 0
            
            if char.isalpha():
                # If k lands precisely on the newly appended character
                if k == curr_len - 1:
                    return char
                curr_len = prev_len
                
            elif char == '*':
                curr_len = prev_len
                
            elif char == '#':
                half_len = prev_len
                # If k is inside the duplicated half, mirror it back to the first half
                if k >= half_len:
                    k %= half_len
                curr_len = half_len
                
            elif char == '%':
                # Map k to its mirrored position from the opposite side
                curr_len = prev_len
                if curr_len > 0:
                    k = curr_len - 1 - k
                    
        return '.'
        