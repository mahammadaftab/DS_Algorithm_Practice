class Solution {
    public boolean hasAlternatingBits(int n) {
        // Step 1: XOR n with itself shifted right by 1.
        // If alternating, this creates a number like 111...1
        int x = n ^ (n >> 1);
        
        // Step 2: Check if x consists of all 1s.
        // We do this by checking if x & (x + 1) is 0.
        // (x + 1) will carry the 1 all the way to the top, making it a power of 2 (100...0)
        return (x & (x + 1)) == 0;
    }
}
