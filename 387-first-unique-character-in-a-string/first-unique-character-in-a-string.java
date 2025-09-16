import java.util.HashMap;
import java.util.Map;

class Solution {
    /**
     * Given a string s, find the first non-repeating character in it and return its index.
     * If it does not exist, return -1.
     */
    public int firstUniqChar(String s) {
        // Use a HashMap to store the frequency of each character in the string.
        Map<Character, Integer> charFrequencies = new HashMap<>();

        // Iterate over the string and update the frequency of each character.
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            charFrequencies.put(c, charFrequencies.getOrDefault(c, 0) + 1);
        }

        // Iterate over the string again and check if the frequency of the character is 1.
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charFrequencies.get(c) == 1) {
                return i;
            }
        }

        // If no non-repeating character is found, return -1.
        return -1;
    }
}