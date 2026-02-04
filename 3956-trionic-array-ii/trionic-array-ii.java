import java.util.Arrays;

class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        if (n < 4) return 0; // Impossible to form len >= 4

        long[] P = new long[n];
        P[0] = nums[0];
        for (int i = 1; i < n; i++) P[i] = P[i - 1] + nums[i];

        long INF = Long.MIN_VALUE / 2; // Safety buffer

        // 1. Calculate bestLeft[p]: Max sum of strict increasing ending at p (len >= 2)
        long[] bestLeft = new long[n];
        Arrays.fill(bestLeft, INF);
        
        int start = 0;
        long minPrefix = (start == 0) ? 0 : P[start - 1]; // Effectively P[-1] = 0
        
        for (int i = 1; i < n; i++) {
            if (nums[i] <= nums[i - 1]) {
                // Sequence broken, new start at i
                start = i;
                minPrefix = P[i - 1]; // P[start-1]
            } else {
                // Strictly increasing extends to i.
                // We need sum from k to i where k <= i-1 (len >= 2)
                // Sum = P[i] - P[k-1]. Maximize sum <=> Minimize P[k-1].
                // Valid k-1 range: [start-1, i-2].
                // Update minPrefix with P[i-2]
                if (i - 2 >= -1) {
                    long val = (i - 2 == -1) ? 0 : P[i - 2];
                    minPrefix = Math.min(minPrefix, val);
                }
                bestLeft[i] = P[i] - minPrefix;
            }
        }

        // 2. Calculate bestRight[q]: Max sum of strict increasing starting at q (len >= 2)
        long[] bestRight = new long[n];
        Arrays.fill(bestRight, INF);
        
        int end = n - 1;
        // Logic: Moving backwards. 
        // We want max sum nums[q...k] where k >= q+1.
        // Sum = P[k] - P[q-1]. Maximize P[k].
        // Valid k range: [q+1, end].
        long maxSuffixP = P[n - 1]; // Tracks max P[k]
        
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] >= nums[i + 1]) {
                // Sequence broken
                end = i;
                maxSuffixP = P[i]; // Effectively looking at P[end]
            } else {
                // Strictly increasing starting at i
                // Valid k is i+1. Update maxSuffixP with P[i+1].
                maxSuffixP = Math.max(maxSuffixP, P[i + 1]);
                
                // Sum = P[k] - P[i-1]
                long prevP = (i == 0) ? 0 : P[i - 1];
                bestRight[i] = maxSuffixP - prevP;
            }
        }

        // 3. Find Max over Decreasing Bridges
        long globalMax = INF;
        long maxDiff = INF; // Tracks max(bestLeft[p] - P[p])

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                // We are in a decreasing sequence. 
                // i-1 is a potential p. i is a potential q.
                
                // Update tracker with p = i-1
                if (bestLeft[i - 1] != INF) {
                    maxDiff = Math.max(maxDiff, bestLeft[i - 1] - P[i - 1]);
                }
                
                // Calculate potential total with q = i
                if (maxDiff != INF && bestRight[i] != INF) {
                    // Total = (bestLeft[p] - P[p]) + P[q-1] + bestRight[q]
                    long currentTotal = maxDiff + P[i - 1] + bestRight[i];
                    globalMax = Math.max(globalMax, currentTotal);
                }
            } else {
                // Decreasing sequence broken
                maxDiff = INF;
            }
        }

        return globalMax == INF ? 0 : globalMax; // Or appropriate error code
    }
}
