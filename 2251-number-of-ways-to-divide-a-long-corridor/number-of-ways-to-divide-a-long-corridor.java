import java.util.ArrayList;
import java.util.List;

class Solution {
    public int numberOfWays(String corridor) {
        long MOD = 1_000_000_007L;
        
        // Step 1: Store the indices of all seats
        List<Integer> seatIndices = new ArrayList<>();
        for (int i = 0; i < corridor.length(); i++) {
            if (corridor.charAt(i) == 'S') {
                seatIndices.add(i);
            }
        }
        
        // Step 2: Check for invalid cases
        // We need at least 2 seats, and the total count must be even
        if (seatIndices.size() < 2 || seatIndices.size() % 2 != 0) {
            return 0;
        }
        
        long ways = 1;
        
        // Step 3: Calculate ways based on gaps between pairs
        // We look at the gap between the end of one pair and the start of the next.
        // Pairs are: (0,1), (2,3), (4,5)...
        // We measure gaps between index 1 and 2, index 3 and 4, etc.
        for (int i = 2; i < seatIndices.size(); i += 2) {
            int prevSeatIndex = seatIndices.get(i - 1); // End of previous pair
            int currSeatIndex = seatIndices.get(i);     // Start of current pair
            
            // The number of ways to divide here is the distance between them
            long gap = currSeatIndex - prevSeatIndex;
            
            ways = (ways * gap) % MOD;
        }
        
        return (int) ways;
    }
}