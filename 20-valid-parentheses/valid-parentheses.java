import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        // Use a stack to keep track of opening parentheses.
        Stack<Character> stack = new Stack<>();

        // Iterate through the string.
        for (char c : s.toCharArray()) {
            // If it's an opening parenthesis, push it onto the stack.
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                // If it's a closing parenthesis, check if the stack is empty or if the top element of the stack doesn't match.
                if (stack.isEmpty()) {
                    return false; // No matching opening parenthesis.
                }

                char top = stack.pop();

                // Check if the closing parenthesis matches the opening parenthesis on the stack.
                if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top != '[')) {
                    return false; // Mismatched parentheses.
                }
            }
        }

        // If the stack is empty at the end, it means all opening parentheses have been matched.
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example usage:
        String s1 = "()";
        System.out.println(s1 + ": " + solution.isValid(s1)); // Output: true

        String s2 = "()[]{}";
        System.out.println(s2 + ": " + solution.isValid(s2)); // Output: true

        String s3 = "(]";
        System.out.println(s3 + ": " + solution.isValid(s3)); // Output: false

        String s4 = "{}";
        System.out.println(s4 + ": " + solution.isValid(s4)); // Output: true

        String s5 = "([{}])";
        System.out.println(s5 + ": " + solution.isValid(s5)); // Output: true

        String s6 = "([{]})";
        System.out.println(s6 + ": " + solution.isValid(s6)); // Output: false

        String s7 = "";
        System.out.println(s7 + ": " + solution.isValid(s7)); // Output: true 
    }
}
//mahammad