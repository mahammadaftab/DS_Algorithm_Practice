class Solution {
    public int minFlips(String s) {
        int n = s.length();
        String doubleS = s + s;
        
        int diff1 = 0; // Flips needed for "0101..."
        int diff2 = 0; // Flips needed for "1010..."
        int minFlips = Integer.MAX_VALUE;
        
        for (int i = 0; i < 2 * n; i++) {
            // Determine what the target characters should be at index i
            char expected1 = (i % 2 == 0) ? '0' : '1';
            char expected2 = (i % 2 == 0) ? '1' : '0';
            
            // Add the new character to our window's diff count
            if (doubleS.charAt(i) != expected1) diff1++;
            if (doubleS.charAt(i) != expected2) diff2++;
            
            // If the window is larger than n, remove the effect of the character that left
            if (i >= n) {
                int leftIndex = i - n;
                char oldExpected1 = (leftIndex % 2 == 0) ? '0' : '1';
                char oldExpected2 = (leftIndex % 2 == 0) ? '1' : '0';
                
                if (doubleS.charAt(leftIndex) != oldExpected1) diff1--;
                if (doubleS.charAt(leftIndex) != oldExpected2) diff2--;
            }
            
            // Once the window has reached size n, start tracking the minimum flips
            if (i >= n - 1) {
                minFlips = Math.min(minFlips, Math.min(diff1, diff2));
            }
        }
        
        return minFlips;
    }
}