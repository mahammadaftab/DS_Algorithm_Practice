class Solution {
    /**
     * Given a string s, find the longest palindromic substring in s.
     *
     * Example 1:
     * Input: s = "babad"
     * Output: "bab"
     * Note: "aba" is also a valid answer.
     *
     * Example 2:
     * Input: s = "cbbd"
     * Output: "bb"
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            // Check for odd length palindromes
            int len1 = expandAroundCenter(s, i, i);
            // Check for even length palindromes
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);

            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left;
        int R = right;

        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }

        return R - L - 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        String s1 = "babad";
        String result1 = solution.longestPalindrome(s1);
        System.out.println("Longest palindrome in '" + s1 + "': " + result1); // Expected: bab or aba

        String s2 = "cbbd";
        String result2 = solution.longestPalindrome(s2);
        System.out.println("Longest palindrome in '" + s2 + "': " + result2); // Expected: bb

        String s3 = "a";
        String result3 = solution.longestPalindrome(s3);
        System.out.println("Longest palindrome in '" + s3 + "': " + result3); // Expected: a

        String s4 = "ac";
        String result4 = solution.longestPalindrome(s4);
        System.out.println("Longest palindrome in '" + s4 + "': " + result4); // Expected: a or c
    }
}