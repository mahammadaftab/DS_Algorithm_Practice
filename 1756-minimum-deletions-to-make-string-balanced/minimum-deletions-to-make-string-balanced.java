class Solution {
    public int minimumDeletions(String s) {
        int countB = 0;
        int minDeletions = 0;
        
        for (char c : s.toCharArray()) {
            if (c == 'b') {
                // Just count the 'b', it doesn't cause a conflict yet
                countB++;
            } else {
                // c == 'a'
                // Conflict arises! We have two options:
                // 1. Delete this 'a' (cost: current minDeletions + 1)
                // 2. Keep this 'a' (implies deleting all previous 'b's: cost countB)
                minDeletions = Math.min(minDeletions + 1, countB);
            }
        }
        
        return minDeletions;
    }
}

