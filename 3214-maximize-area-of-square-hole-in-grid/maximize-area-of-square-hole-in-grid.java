import java.util.Arrays;

class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        // Calculate max contiguous gap for both dimensions
        int maxH = getMaxGap(hBars);
        int maxV = getMaxGap(vBars);
        
        // The largest square side is determined by the smaller dimension
        int side = Math.min(maxH, maxV);
        
        return side * side;
    }
    
    private int getMaxGap(int[] bars) {
        Arrays.sort(bars);
        
        int maxCount = 1;
        int currentCount = 1;
        
        for (int i = 1; i < bars.length; i++) {
            // Check if current bar is consecutive to the previous one
            if (bars[i] == bars[i - 1] + 1) {
                currentCount++;
            } else {
                currentCount = 1; // Reset if sequence breaks
            }
            maxCount = Math.max(maxCount, currentCount);
        }
        
        // The physical gap size is (number of removed bars) + 1
        return maxCount + 1;
    }
}
