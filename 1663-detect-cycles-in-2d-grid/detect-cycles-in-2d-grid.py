class Solution:
    def containsCycle(self, grid: list[list[str]]) -> bool:
        rows, cols = len(grid), len(grid[0])
        visited = set()
        
        def dfs(r: int, c: int, pr: int, pc: int, char: str) -> bool:
            # Mark current cell as visited
            visited.add((r, c))
            
            # Explore all 4 adjacent directions
            for dr, dc in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
                nr, nc = r + dr, c + dc
                
                # Check if the neighbor is valid and matches our character region
                if 0 <= nr < rows and 0 <= nc < cols and grid[nr][nc] == char:
                    # The golden rule: Don't go immediately back to the parent
                    if (nr, nc) != (pr, pc):
                        # If we hit an already visited cell, a cycle exists!
                        if (nr, nc) in visited:
                            return True
                        # Otherwise, continue the DFS
                        if dfs(nr, nc, r, c, char):
                            return True
                            
            return False

        # Try starting a DFS from every unvisited cell
        for r in range(rows):
            for c in range(cols):
                if (r, c) not in visited:
                    # -1, -1 signifies the starting node has no parent
                    if dfs(r, c, -1, -1, grid[r][c]):
                        return True
                        
        return False