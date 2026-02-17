import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> validTimes = new ArrayList<>();
        
        // Iterate through all possible hours (0-11)
        for (int h = 0; h < 12; h++) {
            // Iterate through all possible minutes (0-59)
            for (int m = 0; m < 60; m++) {
                
                // Integer.bitCount returns the number of 1-bits in the integer
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    
                    // Format the time as H:MM
                    // %d for hour (no leading zero)
                    // %02d for minute (pad with leading zero if needed)
                    validTimes.add(String.format("%d:%02d", h, m));
                }
            }
        }
        
        return validTimes;
    }
}