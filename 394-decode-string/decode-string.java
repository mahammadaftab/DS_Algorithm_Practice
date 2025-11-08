import java.util.Stack;

class Solution {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0'; // Build the number (e.g., '12' becomes 12)
            } else if (ch == '[') {
                // Push the current count and string to the stacks
                countStack.push(k);
                stringStack.push(currentString);
                // Reset count and string builder for the next decoded part
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                // Pop the count and previous string
                int repeatTimes = countStack.pop();
                StringBuilder decodedString = stringStack.pop();

                // Repeat the current string 'repeatTimes' times and append it to the previous string
                for (int i = 0; i < repeatTimes; i++) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                // Append the character to the current string
                currentString.append(ch);
            }
        }

        return currentString.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String encodedString1 = "3[a]2[bc]";
        String decodedString1 = sol.decodeString(encodedString1);
        System.out.println("Decoded String 1: " + decodedString1); // Output: aaabcbc

        String encodedString2 = "3[a2[c]]";
        String decodedString2 = sol.decodeString(encodedString2);
        System.out.println("Decoded String 2: " + decodedString2); // Output: accaccacc

        String encodedString3 = "2[abc]3[cd]ef";
        String decodedString3 = sol.decodeString(encodedString3);
        System.out.println("Decoded String 3: " + decodedString3); // Output: abcabccdcdcdef

        String encodedString4 = "abc3[cd]xyz";
        String decodedString4 = sol.decodeString(encodedString4);
        System.out.println("Decoded String 4: " + decodedString4); // Output: abccdcdcdxyz
    }
}
