class Solution {
    public boolean canBeEqual(String s1, String s2) {
        // Check if the even indices (0 and 2) contain the exact same characters
        boolean evensMatch = (s1.charAt(0) == s2.charAt(0) && s1.charAt(2) == s2.charAt(2)) ||
                             (s1.charAt(0) == s2.charAt(2) && s1.charAt(2) == s2.charAt(0));
                             
        // Check if the odd indices (1 and 3) contain the exact same characters
        boolean oddsMatch = (s1.charAt(1) == s2.charAt(1) && s1.charAt(3) == s2.charAt(3)) ||
                            (s1.charAt(1) == s2.charAt(3) && s1.charAt(3) == s2.charAt(1));
                            
        // Both parity groups must be valid for the whole string to be valid
        return evensMatch && oddsMatch;
    }
}