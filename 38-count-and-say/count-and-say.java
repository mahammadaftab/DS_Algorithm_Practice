import java.util.*;
import java.lang.*;

class Solution {
    /**
     * 38. Count and Say
     * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
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