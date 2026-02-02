import java.util.TreeMap;

class Solution {
    // Two TreeMaps to act as multisets for the sliding window
    TreeMap<Integer, Integer> low = new TreeMap<>();
    TreeMap<Integer, Integer> high = new TreeMap<>();
    long lowSum = 0;
    int lowSize = 0;
    int kTarget; // We need k-1 elements in the 'low' set

    public long minimumCost(int[] nums, int k, int dist) {
        kTarget = k - 1;
        long minCost = Long.MAX_VALUE;
        
        // 1. Initialize window with the first 'dist + 1' elements (indices 1 to dist + 1)
        // Note: The loop bound handles cases where dist >= n-1
        for (int i = 1; i <= Math.min(dist + 1, nums.length - 1); i++) {
            add(nums[i]);
        }
        
        minCost = nums[0] + lowSum;
        
        // 2. Slide the window
        // Remove index 'i' (outgoing) and add index 'i + dist + 1' (incoming)
        for (int i = 1; i + dist + 1 < nums.length; i++) {
            int out = nums[i];
            int in = nums[i + dist + 1];
            
            remove(out);
            add(in);
            
            minCost = Math.min(minCost, nums[0] + lowSum);
        }
        
        return minCost;
    }

    // Helper: Add value to appropriate set and rebalance
    private void add(int val) {
        // Decide initially where to put it
        if (lowSize < kTarget) {
            addToMap(low, val);
            lowSum += val;
            lowSize++;
        } else {
            addToMap(high, val);
        }
        balance();
    }

    // Helper: Remove value from appropriate set and rebalance
    private void remove(int val) {
        if (high.containsKey(val)) {
            removeFromMap(high, val);
        } else {
            // Must be in low
            removeFromMap(low, val);
            lowSum -= val;
            lowSize--;
        }
        balance();
    }

    // Helper: Ensure low has exactly kTarget elements and adheres to min/max property
    private void balance() {
        // If low is too small, take smallest from high
        while (lowSize < kTarget && !high.isEmpty()) {
            int val = high.firstKey();
            removeFromMap(high, val);
            addToMap(low, val);
            lowSum += val;
            lowSize++;
        }
        // If low is too big (shouldn't happen with this logic, but good safety),
        // or if high has a smaller element than low's biggest (swap needed)
        while (!low.isEmpty() && !high.isEmpty() && low.lastKey() > high.firstKey()) {
            int lowMax = low.lastKey();
            int highMin = high.firstKey();
            
            removeFromMap(low, lowMax);
            lowSum -= lowMax;
            
            removeFromMap(high, highMin);
            
            addToMap(high, lowMax);
            addToMap(low, highMin);
            lowSum += highMin;
        }
    }

    // Map Utility: Add with count increment
    private void addToMap(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    // Map Utility: Remove with count decrement
    private void removeFromMap(TreeMap<Integer, Integer> map, int val) {
        if (map.get(val) == 1) {
            map.remove(val);
        } else {
            map.put(val, map.get(val) - 1);
        }
    }
}

