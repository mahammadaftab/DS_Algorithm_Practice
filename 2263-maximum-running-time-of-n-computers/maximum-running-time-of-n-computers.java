import java.util.Arrays;

class Solution {
    /**
     * Calculates the maximum continuous running time for N computers.
     * Uses Binary Search on the answer.
     */
    public long maxRunTime(int n, int[] batteries) {
        
        // Calculate the total available energy (sum of all battery capacities)
        long totalCapacity = 0;
        for (int capacity : batteries) {
            totalCapacity += capacity;
        }

        // 1. Define the search space [low, high]
        long low = 1;
        // The absolute upper bound is the total capacity divided by the number of computers.
        long high = totalCapacity / n; 
        
        long maxTime = 0;

        // 2. Binary Search
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            // Check if it's possible to run N computers for 'mid' minutes
            if (canRun(n, batteries, mid)) {
                // If possible, this 'mid' is a potential answer. Try for a longer time.
                maxTime = mid;
                low = mid + 1;
            } else {
                // If not possible, the required time is too long. Search shorter times.
                high = mid - 1;
            }
        }
        
        return maxTime;
    }

    /**
     * Check function: Determines if N computers can run for T minutes.
     * @param n The number of computers.
     * @param batteries The array of battery capacities.
     * @param T The required running time to check.
     * @return true if running for time T is possible, false otherwise.
     */
    private boolean canRun(int n, int[] batteries, long T) {
        // Required total energy: n * T
        // Total useful energy available: sum of min(battery capacity, T)
        
        long availableTime = 0; // Represents the total useful minutes we can contribute
        
        for (int capacity : batteries) {
            // A single battery C is only useful up to T minutes for the total running time calculation.
            // Any excess capacity (C - T) is wasted relative to the goal of T minutes for N computers.
            availableTime += Math.min((long)capacity, T);
        }

        // The condition for success: total useful energy must be >= total required energy.
        // Equivalent to: availableTime / T >= n
        return availableTime >= (long)n * T;
    }
}