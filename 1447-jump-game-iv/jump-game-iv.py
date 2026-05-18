from collections import defaultdict, deque

class Solution:
    def minJumps(self, arr: list[int]) -> int:
        n = len(arr)
        if n <= 1:
            return 0
            
        # 1. Build the teleportation network
        graph = defaultdict(list)
        for i, val in enumerate(arr):
            graph[val].append(i)
            
        # 2. Initialize BFS
        queue = deque([0])
        visited = [False] * n
        visited[0] = True
        
        steps = 0
        
        # 3. Traverse
        while queue:
            # Process level by level to track the exact number of steps
            for _ in range(len(queue)):
                curr = queue.popleft()
                
                # Did we reach the end?
                if curr == n - 1:
                    return steps
                    
                # Evaluate Left and Right Steps
                for nxt in (curr - 1, curr + 1):
                    if 0 <= nxt < n and not visited[nxt]:
                        visited[nxt] = True
                        queue.append(nxt)
                        
                # Evaluate Teleportation Steps
                val = arr[curr]
                if val in graph:
                    for nxt in graph[val]:
                        if not visited[nxt]:
                            visited[nxt] = True
                            queue.append(nxt)
                    
                    # CRITICAL OPTIMIZATION: 
                    # Destroy the teleportation network for this value to prevent TLE
                    del graph[val]
                    
            steps += 1
            
        return -1

