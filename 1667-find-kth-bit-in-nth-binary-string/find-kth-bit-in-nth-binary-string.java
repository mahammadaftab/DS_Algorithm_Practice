class Solution {
    public char findKthBit(int n, int k) {
        // Base case: S1 is "0"
        if (n == 1) {
            return '0';
        }
        
        // Calculate the length of the current string Sn: 2^n - 1
        // (1 << n) is bitwise for 2^n
        int length = (1 << n) - 1;
        int mid = length / 2 + 1;
        
        // Case 1: k is the middle element
        if (k == mid) {
            return '1';
        }
        
        // Case 2: k is in the first half
        // It's the same as finding the kth bit in S(n-1)
        if (k < mid) {
            return findKthBit(n - 1, k);
        }
        
        // Case 3: k is in the second half
        // We find the symmetric position in the first half, find that bit, and invert it.
        // The symmetric index is: length - k + 1
        char bit = findKthBit(n - 1, length - k + 1);
        return (bit == '0') ? '1' : '0';
    }
}
