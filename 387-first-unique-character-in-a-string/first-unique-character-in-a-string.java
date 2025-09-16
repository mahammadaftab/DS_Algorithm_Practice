class Solution {
    public int firstUniqChar(String s) {
        // This array will store the *first index* of each character.
        // We need three states:
        // -1 : not seen yet
        // -2 : seen more than once (is a duplicate)
        // >= 0 : the index of its first (and so far, only) occurrence
        int[] charIndex = new int[26];
        java.util.Arrays.fill(charIndex, -1); // Initialize all to "not seen"

        int n = s.length();
        for (int i = 0; i < n; i++) {
            int charVal = s.charAt(i) - 'a';
            
            if (charIndex[charVal] == -1) {
                // First time seeing this char, store its index
                charIndex[charVal] = i;
            } else {
                // We've seen this char before, mark it as a duplicate
                charIndex[charVal] = -2;
            }
        }

        // Now, find the smallest index that is *not* -1 or -2
        int minFirstIndex = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (charIndex[i] >= 0) { // This char was unique
                minFirstIndex = Math.min(minFirstIndex, charIndex[i]);
            }
        }

        // If minFirstIndex is still MAX_VALUE, no unique char was found.
        return (minFirstIndex == Integer.MAX_VALUE) ? -1 : minFirstIndex;
    }
}