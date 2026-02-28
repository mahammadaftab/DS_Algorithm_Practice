class Solution {
    public int concatenatedBinary(int n) {
        long MOD = 1_000_000_007;
        long ans = 0;
        int length = 0; // Tracks the binary length of the current number 'i'
        
        for (int i = 1; i <= n; i++) {
            // If 'i' is a power of 2, its binary representation needs one more bit
            if ((i & (i - 1)) == 0) {
                length++;
            }
            
            // Shift the accumulated answer left by 'length' to make room,
            // then use bitwise OR (or addition) to append 'i'.
            ans = ((ans << length) | i) % MOD;
        }
        
        return (int) ans;
    }
}
