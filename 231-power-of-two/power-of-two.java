class Solution {
    /**
     * Given an integer n, return true if it is a power of two. Otherwise, return false.
     *
     */
    public boolean isPowerOfTwo(int n) {
        // Negative numbers and zero are not powers of two
        if (n <= 0) {
            return false;
        }

        // A number is a power of two if it has only one bit set to 1 in its binary representation.
        // We can check this by using the bitwise AND operator.  If n is a power of two, then
        // n & (n - 1) will be 0.
        return (n & (n - 1)) == 0;
    }
}
