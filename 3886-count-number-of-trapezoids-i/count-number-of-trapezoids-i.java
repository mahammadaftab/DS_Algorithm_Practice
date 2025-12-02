import java.util.HashMap;
import java.util.Map;

class Solution {
    public int countTrapezoids(int[][] points) {
        long MOD = 1_000_000_007L;
        
        // Step 1: Count frequency of points at each Y-coordinate
        // Key: Y-coordinate, Value: Count of points
        Map<Integer, Integer> yCounts = new HashMap<>();
        for (int[] p : points) {
            // p[1] is the y-coordinate
            yCounts.put(p[1], yCounts.getOrDefault(p[1], 0) + 1);
        }
        
        long totalTrapezoids = 0;
        // This tracks the cumulative sum of segments from all previously processed Y-levels.
        long segmentsSoFar = 0;
        
        // Step 2 & 3: Calculate segments and accumulate trapezoids
        for (int count : yCounts.values()) {
            // We need at least 2 points to form a horizontal side (segment)
            if (count < 2) continue;
            
            // Calculate number of segments possible at this level (nC2)
            // nC2 = n * (n - 1) / 2
            long currentSegments = (long) count * (count - 1) / 2;
            
            // Multiply current segments by all segments found in previous levels
            // to form unique trapezoids. Perform modulo operation to prevent overflow.
            // New Trapezoids = (Segments at Y_current) * (Total Segments at Y_previous)
            totalTrapezoids = (totalTrapezoids + (currentSegments * segmentsSoFar)) % MOD;
            
            // Update the running sum for the next iteration
            segmentsSoFar = (segmentsSoFar + currentSegments) % MOD;
        }
        
        return (int) totalTrapezoids;
    }
}