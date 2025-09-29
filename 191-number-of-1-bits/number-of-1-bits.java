public class Solution {
    /**
     */
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1); // Clear the least significant bit (rightmost 1)
            count++;
        }
        return count;
    }

    //Alternative solution using bitwise right shift operator.
    /*
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += (n & 1); // Check if the last bit is 1
            n >>>= 1; // Unsigned right shift to move to the next bit. >>> is important to prevent sign extension for negative numbers.
        }
        return count;
    }
     */

    // Another alternative solution using Integer.bitCount()
    /*
    public int hammingWeight(int n) {
      return Integer.bitCount(n);
    }
    */
}
