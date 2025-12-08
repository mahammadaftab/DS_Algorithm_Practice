class Solution {
    public int countTriples(int n) {
        int count = 0;

        // Iterate through all possible values for a and b
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                
                int squareSum = a * a + b * b;
                
                // Calculate the integer square root
                int c = (int) Math.sqrt(squareSum);
                
                // Check if it forms a valid triple:
                // 1. Must be a perfect square (c * c == squareSum)
                // 2. The hypotenuse c must be within the range [1, n]
                if (c <= n && c * c == squareSum) {
                    count++;
                }
            }
        }
        
        return count;
    }
}



