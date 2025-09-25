import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    /**
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