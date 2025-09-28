class Solution {
    public int hammingDistance(int x, int y) {
        // XOR the two numbers to find the bits that are different.
        int xor = x ^ y;

        // Count the number of set bits in the XOR result.
        int distance = 0;
        while (xor != 0) {
            // Increment the distance if the least significant bit is set.
            distance += (xor & 1);

            // Right shift the XOR result to check the next bit.
            xor >>>= 1; // Use unsigned right shift to avoid sign extension.
        }

        return distance;
    }

    //Alternative solution using Integer.bitCount()
}