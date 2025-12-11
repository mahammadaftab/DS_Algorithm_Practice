import java.util.Arrays;

class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        // minX_at_Y[c] stores the minimum Row index (x) found in Column 'c'
        // maxX_at_Y[c] stores the maximum Row index (x) found in Column 'c'
        int[] minX_at_Y = new int[n + 1];
        int[] maxX_at_Y = new int[n + 1];
        
        // minY_at_X[r] stores the minimum Col index (y) found in Row 'r'
        // maxY_at_X[r] stores the maximum Col index (y) found in Row 'r'
        int[] minY_at_X = new int[n + 1];
        int[] maxY_at_X = new int[n + 1];
        
        // Initialize arrays with extreme values
        Arrays.fill(minX_at_Y, Integer.MAX_VALUE);
        Arrays.fill(maxX_at_Y, Integer.MIN_VALUE);
        Arrays.fill(minY_at_X, Integer.MAX_VALUE);
        Arrays.fill(maxY_at_X, Integer.MIN_VALUE);
        
        // Step 1: Populate the boundaries
        for (int[] b : buildings) {
            int x = b[0];
            int y = b[1];
            
            // Update vertical boundaries (for column y)
            if (x < minX_at_Y[y]) minX_at_Y[y] = x;
            if (x > maxX_at_Y[y]) maxX_at_Y[y] = x;
            
            // Update horizontal boundaries (for row x)
            if (y < minY_at_X[x]) minY_at_X[x] = y;
            if (y > maxY_at_X[x]) maxY_at_X[x] = y;
        }
        
        int count = 0;
        
        // Step 2: Check if each building is strictly inside the boundaries
        for (int[] b : buildings) {
            int x = b[0];
            int y = b[1];
            
            // Check Vertical: Is current x strictly between the min and max x of this column?
            // This ensures there is a neighbor above (minX) and below (maxX).
            boolean coveredVertically = (x > minX_at_Y[y]) && (x < maxX_at_Y[y]);
            
            // Check Horizontal: Is current y strictly between the min and max y of this row?
            // This ensures there is a neighbor left (minY) and right (maxY).
            boolean coveredHorizontally = (y > minY_at_X[x]) && (y < maxY_at_X[x]);
            
            if (coveredVertically && coveredHorizontally) {
                count++;
            }
        }
        
        return count;
    }
}




