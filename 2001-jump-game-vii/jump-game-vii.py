class Solution:
    def canReach(self, s: str, minJump: int, maxJump: int) -> bool:
        n = len(s)
        # If the last character is a wall, it's impossible from the start
        if s[-1] == '1':
            return False
            
        dp = [False] * n
        dp[0] = True  # Base case: we start at index 0
        
        reachable_count = 0
        
        for i in range(1, n):
            # 1. Add new available jumping source to the right side of the window
            if i >= minJump:
                if dp[i - minJump]:
                    reachable_count += 1
                    
            # 2. Remove the outdated jumping source from the left side of the window
            if i > maxJump:
                if dp[i - maxJump - 1]:
                    reachable_count -= 1
                    
            # 3. If the window contains at least one valid source and current spot is '0'
            if s[i] == '0' and reachable_count > 0:
                dp[i] = True
                
        return dp[n - 1]

