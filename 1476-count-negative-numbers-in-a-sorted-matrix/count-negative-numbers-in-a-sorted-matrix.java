class Solution {
    public int countNegatives(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        
        // Start from the bottom-left corner
        int row = m - 1;
        int col = 0;
        
        while (row >= 0 && col < n) {
            if (grid[row][col] < 0) {
                // If the current number is negative, then all numbers 
                // to its right in this row are also negative.
                // Number of negatives in this row = total cols - current col index
                count += (n - col);
                
                // Move up to the previous row to check for more negatives
                row--;
            } else {
                // If the current number is positive (or zero), we need smaller numbers.
                // Move right to find them.
                col++;
            }
        }
        
        return count;
    }
}





