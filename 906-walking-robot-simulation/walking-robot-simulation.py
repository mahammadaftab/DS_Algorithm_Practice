class Solution:
    def robotSim(self, commands: list[int], obstacles: list[list[int]]) -> int:
        # Directions: North, East, South, West
        directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
        dir_idx = 0 # Starts facing North (index 0)
        
        # Convert obstacles to a set of tuples for O(1) constant time lookup
        obs_set = set(map(tuple, obstacles))
        
        x, y = 0, 0
        max_dist_sq = 0
        
        for cmd in commands:
            if cmd == -2:
                # Turn left
                dir_idx = (dir_idx - 1) % 4
            elif cmd == -1:
                # Turn right
                dir_idx = (dir_idx + 1) % 4
            else:
                # Move forward step-by-step
                dx, dy = directions[dir_idx]
                for _ in range(cmd):
                    # Check if the next step is an obstacle
                    if (x + dx, y + dy) in obs_set:
                        break # Stop moving in this direction
                        
                    x += dx
                    y += dy
                    # Update the maximum distance squared seen so far
                    max_dist_sq = max(max_dist_sq, x*x + y*y)
                    
        return max_dist_sq

