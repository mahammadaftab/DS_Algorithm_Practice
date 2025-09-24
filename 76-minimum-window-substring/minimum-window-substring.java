import java.util.HashMap;
import java.util.Map;

class Solution {
    /**
     * Finds the minimum window in a string 's' which contains all the characters of another string 't'.
     *
     * @param s The string to search in.
     * @param t The string containing characters to find.
     * @return The minimum window substring, or an empty string if no such window exists.
     */
    public String minWindow(String s, String t) {
        // Edge case: if s is shorter than t, no window is possible.
        if (s.length() < t.length()) {
            return "";
        }

        // Create a frequency map of characters in t.
        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        int left = 0; // The left pointer of the sliding window
        int minLength = Integer.MAX_VALUE; // Length of the minimum window found so far
        int minLeft = 0; // Starting index of the minimum window
        int required = tMap.size(); // Number of unique characters from t we need in the window
        int formed = 0; // Number of unique characters from t we currently have in the window

        // Create a frequency map for the characters in the current window.
        Map<Character, Integer> windowMap = new HashMap<>();

        // Iterate through the string 's' with the right pointer.
        for (int right = 0; right < s.length(); right++) {
            char charRight = s.charAt(right);

            // Add the new character from the right to our window map.
            windowMap.put(charRight, windowMap.getOrDefault(charRight, 0) + 1);

            // Check if the current character is needed and if its count in the window now matches the required count in t.
            if (tMap.containsKey(charRight) && windowMap.get(charRight).intValue() == tMap.get(charRight).intValue()) {
                formed++;
            }

            // Try to contract the window from the left.
            // This loop runs when we have a valid window (formed == required).
            while (left <= right && formed == required) {
                // Update our minimum window size if the current one is smaller.
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                }

                // Remove the character at the left pointer from our window map.
                char charLeft = s.charAt(left);
                windowMap.put(charLeft, windowMap.get(charLeft) - 1);

                // Check if removing this character makes the window invalid.
                if (tMap.containsKey(charLeft) && windowMap.get(charLeft).intValue() < tMap.get(charLeft).intValue()) {
                    formed--;
                }

                // Move the left pointer to shrink the window.
                left++;
            }
        }

        // If minLength was never updated, no valid window was found.
        // Otherwise, return the minimum window substring.
        return minLength == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLength);
    }
}





