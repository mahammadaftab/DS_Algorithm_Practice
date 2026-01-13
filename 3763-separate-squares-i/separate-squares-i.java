class Solution {
    public double separateSquares(int[][] squares) {
        double totalArea = 0;
        double minBound = Double.MAX_VALUE;
        double maxBound = Double.MIN_VALUE;
        
        // 1. Calculate total area and search range
        for (int[] s : squares) {
            // s[0] = x, s[1] = y, s[2] = l
            double y = s[1];
            double l = s[2];
            totalArea += l * l;
            minBound = Math.min(minBound, y);
            maxBound = Math.max(maxBound, y + l);
        }
        
        double target = totalArea / 2.0;
        double low = minBound;
        double high = maxBound;
        
        // 2. Binary Search for the Y coordinate
        // 100 iterations is enough to get precision far beyond 10^-5
        for (int i = 0; i < 100; i++) {
            double mid = low + (high - low) / 2;
            
            if (getAreaBelow(squares, mid) < target) {
                low = mid;
            } else {
                high = mid;
            }
        }
        
        return high;
    }
    
    // Helper to calculate total area of squares below line y=h
    private double getAreaBelow(int[][] squares, double h) {
        double currentArea = 0;
        for (int[] s : squares) {
            double y = s[1];
            double l = s[2];
            
            // Intersection of square vertical range [y, y+l] with [-inf, h]
            // The height of the square part below 'h' is:
            double heightBelow = Math.max(0, Math.min(y + l, h) - y);
            
            currentArea += heightBelow * l; // Width is l
        }
        return currentArea;
    }
}



