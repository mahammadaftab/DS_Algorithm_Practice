import java.util.HashMap;
import java.util.Map;

class Solution {
    /**
     * Given a string s, find the first non-repeating character in it and return its index.
     * If it does not exist, return -1.
     *
     * Example 1:
     * Input: s = "leetcode"
     * Output: 0
     *
     * Example 2:
     * Input: s = "loveleetcode"
     * Output: 2
     *
     * Example 3:
     * Input: s = "aabb"
     * Output: -1
     *
     * Constraints:
     * 1 <= s.length <= 105
     * @return the index of the first non-repeating character, or -1 if it does not exist
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