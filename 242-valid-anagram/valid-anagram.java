import java.util.Arrays;

class Solution {
    /**
     * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
     *
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
     * typically using all the original letters exactly once.
     *
     * Example 1:
     * Input: s = "anagram", t = "nagaram"
     * Output: true
     *
     * Example 2:
     * Input: s = "rat", t = "car"
     * Output: false

     */
    public boolean isAnagram(String s, String t) {
        // If the lengths of the strings are different, they cannot be anagrams.
        if (s.length() != t.length()) {
            return false;
        }

        // Convert the strings to character arrays.
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        // Sort the character arrays.
        Arrays.sort(sChars);
        Arrays.sort(tChars);

        // Compare the sorted character arrays.
        return Arrays.equals(sChars, tChars);
    }

    // Alternative solution using frequency counting
    public boolean isAnagramFrequency(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] charCounts = new int[26]; // Assuming lowercase English letters

        for (int i = 0; i < s.length(); i++) {
            charCounts[s.charAt(i) - 'a']++;
            charCounts[t.charAt(i) - 'a']--;
        }

        for (int count : charCounts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        String s1 = "anagram";
        String t1 = "nagaram";
        System.out.println(s1 + " and " + t1 + " are anagrams: " + solution.isAnagram(s1, t1)); // Output: true

        String s2 = "rat";
        String t2 = "car";
        System.out.println(s2 + " and " + t2 + " are anagrams: " + solution.isAnagram(s2, t2)); // Output: false

        String s3 = "listen";
        String t3 = "silent";
         System.out.println(s3 + " and " + t3 + " are anagrams: " + solution.isAnagram(s3, t3));

         String s4 = "a";
         String t4 = "a";
          System.out.println(s4 + " and " + t4 + " are anagrams: " + solution.isAnagram(s4, t4));
    }
}