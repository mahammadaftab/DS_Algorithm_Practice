class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int totalTime = 0;
        
        // Iterate through all pairs of consecutive points
        for (int i = 0; i < points.length - 1; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            int x2 = points[i + 1][0], y2 = points[i + 1][1];
            
            // The time between two points is the max of the X and Y differences
            int deltaX = Math.abs(x2 - x1);
            int deltaY = Math.abs(y2 - y1);
            
            totalTime += Math.max(deltaX, deltaY);
        }
        
        return totalTime;
    }
}

