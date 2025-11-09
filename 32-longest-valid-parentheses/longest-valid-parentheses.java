import java.util.Stack;

class Solution {
    /**
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
     *
     * Example 1:
     * Input: "(()"
     * Output: 2
     * Explanation: The longest valid parentheses substring is "()"
     *
     * Example 2:
     * Input: ")()())"
     * Output: 4
     * Explanation: The longest valid parentheses substring is "()()"
     *
     * @param s The input string containing parentheses.
     * @return The length of the longest valid parentheses substring.
     */
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // Push -1 to handle the case when the stack is empty

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i); // If the stack becomes empty, push the current index
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek()); // Calculate the length
                }
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        String s1 = "(()";
        System.out.println("Longest valid parentheses for \"" + s1 + "\": " + solution.longestValidParentheses(s1)); // Expected: 2

        String s2 = ")()())";
        System.out.println("Longest valid parentheses for \"" + s2 + "\": " + solution.longestValidParentheses(s2)); // Expected: 4

        String s3 = "()(())";
        System.out.println("Longest valid parentheses for \"" + s3 + "\": " + solution.longestValidParentheses(s3)); // Expected: 6

        String s4 = "";
        System.out.println("Longest valid parentheses for \"" + s4 + "\": " + solution.longestValidParentheses(s4)); // Expected: 0

        String s5 = "((";
        System.out.println("Longest valid parentheses for \"" + s5 + "\": " + solution.longestValidParentheses(s5)); // Expected: 0

        String s6 = "()";
        System.out.println("Longest valid parentheses for \"" + s6 + "\": " + solution.longestValidParentheses(s6)); // Expected: 2

        String s7 = "(()(((()";
        System.out.println("Longest valid parentheses for \"" + s7 + "\": " + solution.longestValidParentheses(s7)); // Expected: 2

         String s8 = "()(())(((())";
        System.out.println("Longest valid parentheses for \"" + s8 + "\": " + solution.longestValidParentheses(s8)); // Expected: 6
    }
}







