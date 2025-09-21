import java.lang.Math;

class Solution {
    public int myAtoi(String s) {
        int sign = 1; // Initialize sign to positive
        int result = 0; // Initialize result
        int index = 0; // Initialize index
        int n = s.length(); // Length of the string

        // Discard leading whitespaces
        while (index < n && s.charAt(index) == ' ') {
            index++;
        }

        // Check for sign
        if (index < n && (s.charAt(index) == '+' || s.charAt(index) == '-')) {
            sign = (s.charAt(index) == '-') ? -1 : 1;
            index++;
        }

        // Read digits until non-digit character or end of string is found
        while (index < n && Character.isDigit(s.charAt(index))) {
            int digit = s.charAt(index) - '0';

            // Check for overflow before multiplying by 10
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            index++;
        }

        return sign * result;
    }
}











