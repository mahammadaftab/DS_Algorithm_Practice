class Solution:
    def minimumTotalDistance(self, robot: list[int], factory: list[list[int]]) -> int:
        # Sort robots and factories to ensure optimal non-crossing paths
        robot.sort()
        factory.sort()
        
        # Flatten factories: A factory at 'pos' with 'limit' becomes 'limit' individual slots
        factories = []
        for pos, limit in factory:
            factories.extend([pos] * limit)
            
        n = len(robot)
        m = len(factories)
        
        # dp[i] represents the minimum distance to assign the first 'i' robots
        dp = [float('inf')] * (n + 1)
        dp[0] = 0  # 0 cost to assign 0 robots
        
        # Process each factory slot one by one
        for j in range(m):
            factory_pos = factories[j]
            
            # Traverse robots backwards to use 1D DP (prevents reusing the same slot)
            for i in range(n, 0, -1):
                # Option 1: Skip this factory slot (dp[i] remains the same)
                # Option 2: Assign robot i-1 to this factory slot
                dp[i] = min(dp[i], dp[i - 1] + abs(robot[i - 1] - factory_pos))
                
        return dp[n]