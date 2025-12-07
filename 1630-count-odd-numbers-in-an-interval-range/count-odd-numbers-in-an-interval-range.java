class Solution {
    public int countOdds(int low, int high) {
        // Base count of odd numbers is half the difference
        int count = (high - low) / 2;
        
        // If either low or high is odd, the division rounds down, 
        // missing exactly one odd number. We add it back.
        if (low % 2 != 0 || high % 2 != 0) {
            count++;
        }
        
        return count;
    }
}