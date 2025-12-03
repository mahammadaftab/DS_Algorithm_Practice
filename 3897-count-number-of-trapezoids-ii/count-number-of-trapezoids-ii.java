import java.util.*;

class Solution {
    public int countTrapezoids(int[][] points) {
        int n = points.length;
        long MOD = 1_000_000_007L;

        // --- STEP 1: Count Total Parallel Pairs (Trapezoids + 2 * Parallelograms) ---
        
        // Map<SlopeKey, Map<Intercept, Count>>
        Map<String, Map<Long, Integer>> slopeMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long dx = points[i][0] - points[j][0];
                long dy = points[i][1] - points[j][1];

                // Normalize Slope
                long g = gcd(Math.abs(dx), Math.abs(dy));
                dx /= g;
                dy /= g;
                
                if (dx < 0 || (dx == 0 && dy < 0)) {
                    dx = -dx;
                    dy = -dy;
                }
                String slopeKey = dx + "_" + dy;

                // Unique Line Identifier: Cross product intercept
                // For line ax + by + c = 0 -> c determines the line for fixed slope
                // derived from y = mx + c => y - (dy/dx)x = c => dx*y - dy*x = C_val
                long intercept = dx * points[i][1] - dy * points[i][0];

                slopeMap.computeIfAbsent(slopeKey, k -> new HashMap<>())
                        .merge(intercept, 1, Integer::sum);
            }
        }

        long totalCounts = 0;

        for (Map<Long, Integer> lines : slopeMap.values()) {
            if (lines.size() < 2) continue;
            
            long runningSum = 0;
            for (int count : lines.values()) {
                totalCounts = (totalCounts + runningSum * count) % MOD;
                runningSum = (runningSum + count) % MOD;
            }
        }

        // --- STEP 2: Subtract Parallelograms (Counted Twice) ---
        
        // Two segments form a parallelogram if they share the same Midpoint 
        // AND have different slopes (to avoid collinear degenerate cases).
        // Midpoint Map: "2*MidX_2*MidY" -> Map<Slope, Count>
        Map<String, Map<String, Integer>> midpointMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Midpoint * 2 to avoid division (x1+x2, y1+y2)
                long midX = (long)points[i][0] + points[j][0];
                long midY = (long)points[i][1] + points[j][1];
                String midKey = midX + "_" + midY;

                long dx = points[i][0] - points[j][0];
                long dy = points[i][1] - points[j][1];
                long g = gcd(Math.abs(dx), Math.abs(dy));
                dx /= g; 
                dy /= g;
                if (dx < 0 || (dx == 0 && dy < 0)) { dx = -dx; dy = -dy; }
                String slopeKey = dx + "_" + dy;

                midpointMap.computeIfAbsent(midKey, k -> new HashMap<>())
                           .merge(slopeKey, 1, Integer::sum);
            }
        }

        long parallelogramCount = 0;
        
        for (Map<String, Integer> slopesAtMidpoint : midpointMap.values()) {
            long totalPairsAtMid = 0;
            long degeneratePairs = 0; // Pairs from collinear points (same slope)

            for (int count : slopesAtMidpoint.values()) {
                totalPairsAtMid += count;
                // nC2 pairs with SAME slope are collinear, not parallelograms
                degeneratePairs += (long)count * (count - 1) / 2;
            }

            long totalCombinations = totalPairsAtMid * (totalPairsAtMid - 1) / 2;
            long validParallelograms = totalCombinations - degeneratePairs;
            
            parallelogramCount = (parallelogramCount + validParallelograms) % MOD;
        }

        // --- STEP 3: Result ---
        return (int) ((totalCounts - parallelogramCount + MOD) % MOD);
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}






