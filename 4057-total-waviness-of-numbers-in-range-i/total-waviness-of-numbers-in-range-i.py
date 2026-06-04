class Solution:
    def totalWaviness(self, num1: int, num2: int) -> int:
        total_waviness = 0
        
        # 1. Iterate through every single integer in the range
        for num in range(num1, num2 + 1):
            s = str(num)
            n = len(s)
            
            # Constraints specify numbers with < 3 digits have 0 waviness
            if n < 3:
                continue
                
            # 2. Check each internal digit for peak or valley conditions
            for i in range(1, n - 1):
                # Peak Check
                if s[i] > s[i-1] and s[i] > s[i+1]:
                    total_waviness += 1
                # Valley Check
                elif s[i] < s[i-1] and s[i] < s[i+1]:
                    total_waviness += 1
                    
        return total_waviness
