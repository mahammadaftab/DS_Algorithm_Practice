import java.util.Stack;

class Solution {
    /**
     * Evaluates an arithmetic expression in Reverse Polish Notation (RPN).
     * 
     * @param tokens An array of strings representing the RPN expression.
     * @return The result of the evaluation.
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (isOperator(token)) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                int result = performOperation(token, operand1, operand2);
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    /**
     * Checks if a string is an operator (+, -, *, /).
     * 
     * @param token The string to check.
     * @return True if the string is an operator, false otherwise.
     */
    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    /**
     * Performs the arithmetic operation specified by the operator on the two operands.
     * 
     * @param operator The operator (+, -, *, /).
     * @param operand1 The first operand.
     * @param operand2 The second operand.
     * @return The result of the operation.
     */
    private int performOperation(String operator, int operand1, int operand2) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example usage
        String[] tokens1 = {"2", "1", "+", "3", "*"};
        System.out.println("Result 1: " + sol.evalRPN(tokens1)); // Output: 9

        String[] tokens2 = {"4", "13", "5", "/", "+"};
        System.out.println("Result 2: " + sol.evalRPN(tokens2)); // Output: 6

        String[] tokens3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println("Result 3: " + sol.evalRPN(tokens3)); // Output: 22
    }
}




