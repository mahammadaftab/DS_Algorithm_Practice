class Solution:
    def rotatedDigits(self, n: int) -> int:
        count = 0
        
        for i in range(1, n + 1):
            s = str(i)
            
            # Rule 1: Must NOT contain 3, 4, or 7
            if '3' in s or '4' in s or '7' in s:
                continue
                
            # Rule 2: Must contain at least one of 2, 5, 6, or 9
            if '2' in s or '5' in s or '6' in s or '9' in s:
                count += 1
                
        return count