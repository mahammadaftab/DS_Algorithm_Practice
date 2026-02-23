class Solution {
    public boolean hasAllCodes(String s, int k) {
        int n = s.length();
        int totalCodes = 1 << k; // 2^k
        
        // Early pruning: Impossible to fit all codes
        if (n < totalCodes + k - 1) return false;
        
        boolean[] seen = new boolean[totalCodes];
        int hash = 0;
        int mask = totalCodes - 1; // Creates a mask of k 1s (e.g., k=3 -> 111)
        int distinctCount = 0;
        
        for (int i = 0; i < n; i++) {
            // Shift left, mask to keep only k bits, and add the new bit
            hash = ((hash << 1) & mask) | (s.charAt(i) - '0');
            
            // Once we have processed at least k characters, we have a valid window
            if (i >= k - 1) {
                if (!seen[hash]) {
                    seen[hash] = true;
                    distinctCount++;
                    
                    // If we've found all possible combinations, we can stop early
                    if (distinctCount == totalCodes) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
}
