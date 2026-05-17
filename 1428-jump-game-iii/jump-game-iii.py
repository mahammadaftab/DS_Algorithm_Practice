from collections import deque

class Solution:
    def canReach(self, arr: list[int], start: int) -> bool:
        # Queue to hold the indices we need to explore
        queue = deque([start])
        
        while queue:
            curr = queue.popleft()
            
            # 1. Did we find the target?
            if arr[curr] == 0:
                return True
                
            # 2. Is this a valid, unvisited index?
            if arr[curr] > 0:
                jump = arr[curr]
                
                # Check right jump bounds
                if curr + jump < len(arr):
                    queue.append(curr + jump)
                    
                # Check left jump bounds
                if curr - jump >= 0:
                    queue.append(curr - jump)
                    
                # 3. Mark the current index as visited by making it negative
                arr[curr] = -arr[curr]
                
        # If the queue empties and we haven't found 0, it's unreachable
        return False
