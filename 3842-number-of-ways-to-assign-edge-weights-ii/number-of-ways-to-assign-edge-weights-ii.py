import math
from collections import defaultdict

class Solution:
    def assignEdgeWeights(self, edges: list[list[int]], queries: list[list[int]]) -> list[int]:
        n = len(edges) + 1
        graph = defaultdict(list)
        for u, v in edges:
            graph[u].append(v)
            graph[v].append(u)
            
        # Binary lifting constants
        LOG_N = int(math.log2(n)) + 1
        up = [[-1] * LOG_N for _ in range(n + 1)]
        depth = [0] * (n + 1)
        
        # 1. Standard DFS to establish depths and immediate parents
        stack = [(1, -1, 0)]  # (node, parent, current_depth)
        visited = {1}
        
        # We can use an iterative traversal to avoid Python recursion limits
        while stack:
            curr, p, d = stack.pop()
            depth[curr] = d
            up[curr][0] = p
            
            for neighbor in graph[curr]:
                if neighbor not in visited:
                    visited.add(neighbor)
                    stack.append((neighbor, curr, d + 1))
                    
        # 2. Populate binary lifting ancestors table
        for j in range(1, LOG_N):
            for i in range(1, n + 1):
                if up[i][j-1] != -1:
                    up[i][j] = up[up[i][j-1]][j-1]
                    
        # 3. Helper function to find the Lowest Common Ancestor
        def get_lca(u, v):
            if depth[u] < depth[v]:
                u, v = v, u
                
            # Bring both nodes to the same depth level
            diff = depth[u] - depth[v]
            for j in range(LOG_N):
                if (diff >> j) & 1:
                    u = up[u][j]
                    
            if u == v:
                return u
                
            # Lift both nodes concurrently right beneath their common ancestor
            for j in range(LOG_N - 1, -1, -1):
                if up[u][j] != up[v][j]:
                    u = up[u][j]
                    v = up[v][j]
                    
            return up[u][0]

        # 4. Process all query paths
        MOD = 10**9 + 7
        ans = []
        
        for u, v in queries:
            if u == v:
                ans.append(0)
            else:
                lca = get_lca(u, v)
                # Number of edges on the simple path between u and v
                k = depth[u] + depth[v] - 2 * depth[lca]
                ans.append(pow(2, k - 1, MOD))
                
        return ans