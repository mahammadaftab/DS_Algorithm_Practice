class Solution {
    public int countOdds(int low, int high) {
        int count = (high - low) / 2;
        // missing exactly one odd number. We add it back.
        if (low % 2 != 0 || high % 2 != 0) {
            count++;
        }
        
        return count;
    }
}