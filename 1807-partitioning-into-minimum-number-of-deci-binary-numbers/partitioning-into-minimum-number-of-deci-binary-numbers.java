class Solution {
    public int minPartitions(String n) {
        int maxDigit = 0;
        
        for (int i = 0; i < n.length(); i++) {
            // Convert the character to its integer value
            int currentDigit = n.charAt(i) - '0';
            
            // Update the maximum digit found so far
            maxDigit = Math.max(maxDigit, currentDigit);
            
            // Optimization: If we hit 9, we can't go any higher, so stop early
            if (maxDigit == 9) {
                break;
            }
        }
        
        return maxDigit;
    }
}
