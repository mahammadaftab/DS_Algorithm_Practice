from collections import deque

class Solution:
    def hasValidPath(self, grid: list[list[int]]) -> bool:
        m, n = len(grid), len(grid[0])
        
        # Maps a direction to: (row_delta, col_delta, required_opposite_direction)
        directions = {
            'U': (-1, 0, 'D'),
            'D': (1, 0, 'U'),
            'L': (0, -1, 'R'),
            'R': (0, 1, 'L')
        }
        
        # Maps the street type (1-6) to its allowed outgoing directions
        streets = {
            1: ['L', 'R'],
            2: ['U', 'D'],
            3: ['L', 'D'],
            4: ['R', 'D'],
            5: ['L', 'U'],
            6: ['R', 'U']
        }
        
        # Start BFS
        queue = deque([(0, 0)])
        visited = set([(0, 0)])
        
        while queue:
            r, c = queue.popleft()
            
            # Reached the destination
            if r == m - 1 and c == n - 1:
                return True
                
            current_street = grid[r][c]
            
            for d in streets[current_street]:
                dr, dc, opposite_dir = directions[d]
                nr, nc = r + dr, c + dc
                
                # 1. Check if neighbor is within grid bounds
                # 2. Check if neighbor hasn't been visited
                if 0 <= nr < m and 0 <= nc < n and (nr, nc) not in visited:
                    next_street = grid[nr][nc]
                    
                    # 3. The Handshake: Does the neighbor have a pipe pointing back?
                    if opposite_dir in streets[next_street]:
                        visited.add((nr, nc))
                        queue.append((nr, nc))
                        
        return False
