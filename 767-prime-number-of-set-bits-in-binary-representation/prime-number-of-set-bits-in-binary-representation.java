class Solution {
    public int countPrimeSetBits(int left, int right) {
        int count = 0;
        
        // Bitmask representing primes up to 20: 2, 3, 5, 7, 11, 13, 17, 19
        // Binary: 10100010100010101100 -> Decimal: 665772
        int primeMask = 665772;
        
        for (int i = left; i <= right; i++) {
            // Count the number of set bits (1s)
            int bits = Integer.bitCount(i);
            
            // Check if the bit count is prime using the mask
            if (((primeMask >> bits) & 1) == 1) {
                count++;
            }
        }
        
        return count;
    }
}