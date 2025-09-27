public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            // Get the rightmost bit of n
            int bit = (n >> i) & 1; 

            // Shift the bit to its reversed position and add to the result
            result |= (bit << (31 - i));
        }
        return result;
    }
}
