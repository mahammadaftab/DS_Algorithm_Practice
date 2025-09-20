import java.util.Arrays;

class Solution {
    /**
     * LeetCode Problem 14: Longest Common Prefix
     *
     * Find the longest common prefix string amongst an array of strings.
     *
     * If there is no common prefix, return an empty string "".
     *
     * Example 1:
     * Input: strs = ["flower","flow","flight"]
     * Output: "fl"
     *
     * Example 2:
     * Input: strs = ["dog","racecar","car"]
     * Output: ""
     * Explanation: There is no common prefix among the input strings.
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Sort the array of strings lexicographically.
        Arrays.sort(strs);

        // Get the first and last strings in the sorted array.
        String first = strs[0];
        String last = strs[strs.length - 1];

        // Find the common prefix between the first and last strings.
        int i = 0;
        while (i < first.length() && i < last.length() && first.charAt(i) == last.charAt(i)) {
            i++;
        }

        // Return the common prefix.
        return first.substring(0, i);
    }

    //Alternative approach: Iterate through each character of the first string
    //and check if all other strings have the same character at the same index.
    public String longestCommonPrefixAlternative(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    //Another alternative approach: Compare characters vertically
    public String longestCommonPrefixVertical(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}