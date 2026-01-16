import java.util.*;

class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        // 1. Add boundaries (1 and m/n) to the fence lists
        int[] h = new int[hFences.length + 2];
        int[] v = new int[vFences.length + 2];
        
        h[0] = 1; h[1] = m;
        System.arraycopy(hFences, 0, h, 2, hFences.length);
        
        v[0] = 1; v[1] = n;
        System.arraycopy(vFences, 0, v, 2, vFences.length);
        
        // Sorting helps, though not strictly necessary for O(N^2) logic,
        // it makes the iteration cleaner (j > i implies positive gap).
        Arrays.sort(h);
        Arrays.sort(v);
        
        // 2. Store all possible horizontal gaps
        Set<Integer> hGaps = new HashSet<>();
        for (int i = 0; i < h.length; i++) {
            for (int j = i + 1; j < h.length; j++) {
                hGaps.add(h[j] - h[i]);
            }
        }
        
        long maxSide = -1;
        
        // 3. Check vertical gaps against horizontal gaps
        for (int i = 0; i < v.length; i++) {
            for (int j = i + 1; j < v.length; j++) {
                int currentGap = v[j] - v[i];
                if (hGaps.contains(currentGap)) {
                    maxSide = Math.max(maxSide, currentGap);
                }
            }
        }
        
        // 4. Return result
        if (maxSide == -1) return -1;
        
        long mod = 1_000_000_007;
        return (int) ((maxSide * maxSide) % mod);
    }
}



