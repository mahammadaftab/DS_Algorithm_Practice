import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    /**
     * LeetCode Problem 438: Find All Anagrams in a String
     *
     * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
     * You may return the answer in any order.
     *
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
     * typically using all the original letters exactly once.
     *
     * Example 1:
     * Input: s = "cbaebabacd", p = "abc"
     * Output: [0,6]
     * Explanation:
     * The substring with start index = 0 is "cba", which is an anagram of "abc".
     * The substring with start index = 6 is "bac", which is an anagram of "abc".
     *
     * Example 2:
     * Input: s = "abab", p = "ab"
     * Output: [0,1,2]
     * Explanation:
     * The substring with start index = 0 is "ab", which is an anagram of "ab".
     * The substring with start index = 1 is "ba", which is an anagram of "ab".
     * The substring with start index = 2 is "ab", which is an anagram of "ab".
     *
     * Constraints:
     * 1 <= s.length, p.length <= 3 * 104
     * s and p consist of lowercase English letters.
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }

        int[] pCharCounts = new int[26];
        int[] sCharCounts = new int[26];

        // Count character occurrences in p
        for (char c : p.toCharArray()) {
            pCharCounts[c - 'a']++;
        }

        // Initial window in s
        for (int i = 0; i < p.length(); i++) {
            sCharCounts[s.charAt(i) - 'a']++;
        }

        // Slide the window through s
        for (int i = 0; i <= s.length() - p.length(); i++) {
            if (Arrays.equals(sCharCounts, pCharCounts)) {
                result.add(i);
            }

            // Slide the window: remove the leftmost character and add the rightmost character
            if (i < s.length() - p.length()) {
                sCharCounts[s.charAt(i) - 'a']--; // Remove leftmost character
                sCharCounts[s.charAt(i + p.length()) - 'a']++; // Add rightmost character
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        String s1 = "cbaebabacd";
        String p1 = "abc";
        System.out.println("Anagrams in '" + s1 + "' of '" + p1 + "': " + solution.findAnagrams(s1, p1)); // Output: [0, 6]

        String s2 = "abab";
        String p2 = "ab";
        System.out.println("Anagrams in '" + s2 + "' of '" + p2 + "': " + solution.findAnagrams(s2, p2)); // Output: [0, 1, 2]

        String s3 = "";
        String p3 = "a";
        System.out.println("Anagrams in '" + s3 + "' of '" + p3 + "': " + solution.findAnagrams(s3, p3)); // Output: []

        String s4 = "a";
        String p4 = "aa";
        System.out.println("Anagrams in '" + s4 + "' of '" + p4 + "': " + solution.findAnagrams(s4, p4)); // Output: []
    }
}
