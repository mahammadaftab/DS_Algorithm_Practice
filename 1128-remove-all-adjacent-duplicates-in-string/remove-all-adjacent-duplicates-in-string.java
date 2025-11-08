import java.util.Stack;

class Solution {
    /**
     * Given a string s, remove all adjacent duplicate characters from it.
     * You may perform this operation repeatedly until there are no more adjacent duplicates.
     *
     * Return the final string after all such removals have been made.
     *
     * Example 1:
     * Input: s = "abbaca"
     * Output: "ca"
     * Explanation:
     * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
     *
     * Example 2:
     * Input: s = "azxxzy"
     * Output: "ay"
     *
     * Constraints:
     * 1 <= s.length <= 10^5
     * s consists of lowercase English letters.
     */
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop()); // Insert at the beginning to maintain the correct order
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        String s1 = "abbaca";
        String result1 = solution.removeDuplicates(s1);
        System.out.println("Input: " + s1 + ", Output: " + result1); // Expected: ca

        String s2 = "azxxzy";
        String result2 = solution.removeDuplicates(s2);
        System.out.println("Input: " + s2 + ", Output: " + result2); // Expected: ay

        String s3 = "aaaaaaaa";
        String result3 = solution.removeDuplicates(s3);
        System.out.println("Input: " + s3 + ", Output: " + result3); // Expected: ""

        String s4 = "aab";
        String result4 = solution.removeDuplicates(s4);
        System.out.println("Input: " + s4 + ", Output: " + result4); // Expected: "b"
    }
}

