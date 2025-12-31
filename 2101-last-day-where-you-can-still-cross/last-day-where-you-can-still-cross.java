import java.util.*;

class Solution {
    private int[] dr = {0, 0, 1, -1};
    private int[] dc = {1, -1, 0, 0};

    public int latestDayToCross(int row, int col, int[][] cells) {
        int left = 0;
        int right = row * col;
        int ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // If we can cross on day 'mid', try a later day (move right)
            if (canCross(mid, row, col, cells)) {
                ans = mid; // Store valid answer
                left = mid + 1;
            } else {
                // If we can't cross, we must try an earlier day (move left)
                right = mid - 1;
            }
        }
        
        return ans;
    }

    // Returns true if a path exists from top to bottom row after 'day' cells are flooded
    private boolean canCross(int day, int row, int col, int[][] cells) {
        int[][] grid = new int[row][col];
        
        // Mark flooded cells for the current 'day'
        // The cells array is 1-based, so we adjust to 0-based
        for (int i = 0; i < day; i++) {
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            grid[r][c] = 1; // 1 represents Water
        }
        
        // BFS Queue
        Queue<int[]> queue = new LinkedList<>();
        
        // Add all valid starting points (Land cells in the Top Row)
        for (int c = 0; c < col; c++) {
            if (grid[0][c] == 0) { // If land
                queue.offer(new int[]{0, c});
                grid[0][c] = -1; // Mark as visited
            }
        }
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            
            // If we reached the bottom row, success!
            if (r == row - 1) {
                return true;
            }
            
            // Explore neighbors
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                // Check bounds and if it is Land (0)
                if (nr >= 0 && nr < row && nc >= 0 && nc < col && grid[nr][nc] == 0) {
                    grid[nr][nc] = -1; // Mark visited
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        
        return false;
    }
}

