from collections import defaultdict, deque

class Solution:
    def assignEdgeWeights(self, edges: list[list[int]]) -> int:
        # 1. Build the adjacency list representation of the tree
        graph = defaultdict(list)
        for u, v in edges:
            graph[u].append(v)
            graph[v].append(u)
            
        # 2. Use BFS to find the maximum depth (number of edges from root 1)
        # Queue stores tuples of (current_node, current_depth)
        queue = deque([(1, 0)])
        visited = {1}
        max_depth = 0
        
        while queue:
            node, depth = queue.popleft()
            max_depth = max(max_depth, depth)
            
            for neighbor in graph[node]:
                if neighbor not in visited:
                    visited.add(neighbor)
                    queue.append((neighbor, depth + 1))
                    
        # 3. If k is the maximum depth, the answer is 2^(k - 1) % (10^9 + 7)
        MOD = 10**9 + 7
        if max_depth == 0:
            return 0
            
        return pow(2, max_depth - 1, MOD)