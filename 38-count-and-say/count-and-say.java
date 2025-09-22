import java.util.*;
import java.lang.*;

class Solution {
    /**
     * 38. Count and Say
     * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
     * 
     * countAndSay(1) = "1"
     * countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a different digit string.
     * To determine how you "say" a digit string, read each group of same digits from left to right, counting the number of those digits. For example, the string "3322251" is read as "two 3s, three 2s, one 5, one 1".
     *
     */
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String prev = countAndSay(n - 1);
        StringBuilder result = new StringBuilder();
        int count = 1;

        for (int i = 0; i < prev.length(); i++) {
            if (i + 1 < prev.length() && prev.charAt(i) == prev.charAt(i + 1)) {
                count++;
            } else {
                result.append(count);
                result.append(prev.charAt(i));
                count = 1;
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.countAndSay(1)); // Output: 1
        System.out.println(sol.countAndSay(4)); // Output: 1211
        System.out.println(sol.countAndSay(5)); // Output: 111221
    }
}