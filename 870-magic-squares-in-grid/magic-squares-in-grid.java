class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        
        // Iterate through all possible top-left corners of 3x3 subgrids
        for (int r = 0; r < rows - 2; r++) {
            for (int c = 0; c < cols - 2; c++) {
                // Optimization: The center of a 3x3 magic square (1-9) MUST be 5.
                if (grid[r + 1][c + 1] != 5) {
                    continue;
                }
                
                if (isMagic(grid, r, c)) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    private boolean isMagic(int[][] grid, int r, int c) {
        // 1. Check if all numbers are distinct and between 1-9
        // We use a boolean array to track visited numbers.
        boolean[] seen = new boolean[10]; // indices 1-9 used
        
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                int val = grid[i][j];
                // Must be between 1 and 9
                if (val < 1 || val > 9) return false;
                // Must be distinct
                if (seen[val]) return false;
                seen[val] = true;
            }
        }
        
        // 2. Check Sums (Target is always 15 for 1-9 magic square)
        
        // Rows
        if (grid[r][c] + grid[r][c+1] + grid[r][c+2] != 15) return false;
        if (grid[r+1][c] + grid[r+1][c+1] + grid[r+1][c+2] != 15) return false;
        if (grid[r+2][c] + grid[r+2][c+1] + grid[r+2][c+2] != 15) return false;
        
        // Columns
        if (grid[r][c] + grid[r+1][c] + grid[r+2][c] != 15) return false;
        if (grid[r][c+1] + grid[r+1][c+1] + grid[r+2][c+1] != 15) return false;
        if (grid[r][c+2] + grid[r+1][c+2] + grid[r+2][c+2] != 15) return false;
        
        // Diagonals
        if (grid[r][c] + grid[r+1][c+1] + grid[r+2][c+2] != 15) return false;
        if (grid[r][c+2] + grid[r+1][c+1] + grid[r+2][c] != 15) return false;
        
        return true;
    }
}