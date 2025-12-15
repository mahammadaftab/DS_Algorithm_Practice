class Solution {
    public long getDescentPeriods(int[] prices) {
        // 'total' tracks the sum of all valid periods.
        // Use long because the answer can exceed Integer.MAX_VALUE (for N=10^5, sum ~ 5*10^9).
        long total = 1; 
        
        // 'currentLength' tracks the length of the smooth descent ending at the current index.
        // We start at index 0 with a sequence of length 1 (the element itself).
        long currentLength = 1;
        
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] == prices[i - 1] - 1) {
                // If strictly decreasing by 1, extend the sequence
                currentLength++;
            } else {
                // Otherwise, reset sequence length to 1 (the element itself)
                currentLength = 1;
            }
            
            // Add the number of valid subarrays ending at this specific position
            total += currentLength;
        }
        
        return total;
    }
}




