class Solution {
    public int firstUniqChar(String s) {
        // Use a simple array of size 26 for frequency counting.
        // This is much faster than a HashMap (no hashing, no autoboxing).
        int[] freq = new int[26];
        
        // First pass: Count frequencies.
        // s.charAt(i) - 'a' maps 'a'->0, 'b'->1, ... 'z'->25
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        
        // Second pass: Find the first character with a frequency of 1.
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) {
                return i; // Found it!
            }
        }
        
        // No unique character found.
        return -1;
    }
}