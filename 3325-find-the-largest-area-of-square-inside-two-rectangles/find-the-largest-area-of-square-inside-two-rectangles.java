class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long maxSide = 0;
        
        // Iterate through every unique pair of rectangles
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                
                // Calculate intersection boundaries
                int left = Math.max(bottomLeft[i][0], bottomLeft[j][0]);
                int bottom = Math.max(bottomLeft[i][1], bottomLeft[j][1]);
                int right = Math.min(topRight[i][0], topRight[j][0]);
                int top = Math.min(topRight[i][1], topRight[j][1]);
                
                // Calculate width and height of the intersection
                int width = right - left;
                int height = top - bottom;
                
                // If intersection is valid (positive area)
                if (width > 0 && height > 0) {
                    // The largest square is limited by the smaller dimension
                    long currentSide = Math.min(width, height);
                    maxSide = Math.max(maxSide, currentSide);
                }
            }
        }
        
        return maxSide * maxSide;
    }
}


