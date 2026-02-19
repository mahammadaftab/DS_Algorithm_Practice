class Solution {
    public int countBinarySubstrings(String s) {
        int totalSubstrings = 0;
        int prevCount = 0;
        int currCount = 1;
        
        for (int i = 1; i < s.length(); i++) {
            // If the current character is the same as the previous, grow the group
            if (s.charAt(i) == s.charAt(i - 1)) {
                currCount++;
            } else {
                // The character changed, meaning a group just ended.
                // Add the min of the previous and current groups to the total.
                totalSubstrings += Math.min(prevCount, currCount);
                
                // The current group now becomes the previous group
                prevCount = currCount;
                
                // Reset current group count for the new character
                currCount = 1;
            }
        }
        
        // Don't forget to add the calculation for the very last pair of groups!
        totalSubstrings += Math.min(prevCount, currCount);
        
        return totalSubstrings;
    }
}
