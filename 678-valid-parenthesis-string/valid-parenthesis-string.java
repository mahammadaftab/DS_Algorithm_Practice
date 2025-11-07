import java.util.Stack;

class Solution {
    /**
     * Given a string s containing only three types of characters: '(', ')' and '*', return true if      *
     * Example 1:
     * Input: s = "()"
     * Output: true
     *
     * Example 2:
     * Input: s = "(*)"
     * Output: true
     *
     * Example 3:
     * Input: s = "(*))"
     * Output: true
     *
     * Constraints:
     * 1 <= s.length <= 100
     * s[i] is '(', ')' or '*'.
     */
    public boolean checkValidString(String s) {
        Stack<Integer> open = new Stack<>();
        Stack<Integer> star = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                open.push(i);
            } else if (c == '*') {
                star.push(i);
            } else {
                if (!open.isEmpty()) {
                    open.pop();
                } else if (!star.isEmpty()) {
                    star.pop();
                } else {
                    return false;
                }
            }
        }

        while (!open.isEmpty() && !star.isEmpty()) {
            if (open.peek() > star.peek()) {
                return false;
            }
            open.pop();
            star.pop();
        }

        return open.isEmpty();
    }

    // Alternative, space optimized solution with two counters
    public boolean checkValidStringOptimized(String s) {
        int low = 0;  // Minimum number of unmatched open parentheses
        int high = 0; // Maximum number of unmatched open parentheses

        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low--;
                high--;
            } else {
                low--; // Treat '*' as ')'
                high++; // Treat '*' as '('
            }

            // Maintain low >= 0. If low < 0, it means there are more ')' than '(',
            // and we can treat some '*' as '(' to balance it.
            low = Math.max(low, 0);

            // If high < 0, it means there are more ')' than '(' and '*', which is invalid.
            if (high < 0) {
                return false;
            }
        }

        // After processing the whole string, if low == 0, it means all '(' are matched,
        // and the string is valid.
        return low == 0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Test cases
        String s1 = "()";
        System.out.println("s1 = " + s1 + ", isValid = " + sol.checkValidStringOptimized(s1)); // Expected: true

        String s2 = "(*)";
        System.out.println("s2 = " + s2 + ", isValid = " + sol.checkValidStringOptimized(s2)); // Expected: true

        String s3 = "(*))";
        System.out.println("s3 = " + s3 + ", isValid = " + sol.checkValidStringOptimized(s3)); // Expected: true

        String s4 = "((";
        System.out.println("s4 = " + s4 + ", isValid = " + sol.checkValidStringOptimized(s4)); // Expected: false

        String s5 = "(***))";
        System.out.println("s5 = " + s5 + ", isValid = " + sol.checkValidStringOptimized(s5)); // Expected: true

        String s6 = "(*(()";
        System.out.println("s6 = " + s6 + ", isValid = " + sol.checkValidStringOptimized(s6)); // Expected: false
    }
}
