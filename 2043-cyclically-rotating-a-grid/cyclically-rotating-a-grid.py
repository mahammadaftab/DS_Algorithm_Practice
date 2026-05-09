class Solution:
    def rotateGrid(self, grid: list[list[int]], k: int) -> list[list[int]]:
        m, n = len(grid), len(grid[0])
        # The number of concentric layers
        num_layers = min(m, n) // 2
        
        for layer in range(num_layers):
            # Define the boundaries for the current layer
            top, bottom = layer, m - 1 - layer
            left, right = layer, n - 1 - layer
            
            # 1. Unroll the layer clockwise into a 1D array
            elements = []
            
            # Top row
            for j in range(left, right + 1):
                elements.append(grid[top][j])
            # Right column
            for i in range(top + 1, bottom):
                elements.append(grid[i][right])
            # Bottom row
            for j in range(right, left - 1, -1):
                elements.append(grid[bottom][j])
            # Left column
            for i in range(bottom - 1, top, -1):
                elements.append(grid[i][left])
                
            # 2. Modulo optimization and 1D Shift
            effective_k = k % len(elements)
            # Counter-clockwise rotation = Left shift of the array
            shifted = elements[effective_k:] + elements[:effective_k]
            
            # 3. Insert the shifted elements back into the grid
            idx = 0
            
            # Top row
            for j in range(left, right + 1):
                grid[top][j] = shifted[idx]
                idx += 1
            # Right column
            for i in range(top + 1, bottom):
                grid[i][right] = shifted[idx]
                idx += 1
            # Bottom row
            for j in range(right, left - 1, -1):
                grid[bottom][j] = shifted[idx]
                idx += 1
            # Left column
            for i in range(bottom - 1, top, -1):
                grid[i][left] = shifted[idx]
                idx += 1
                
        return grid
