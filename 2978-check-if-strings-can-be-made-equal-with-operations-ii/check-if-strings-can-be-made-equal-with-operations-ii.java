class Solution {
    public boolean checkStrings(String s1, String s2) {
        int[] evenCount = new int[26];
        int[] oddCount = new int[26];
        
        int n = s1.length();
        
        // Build the frequency arrays in a single pass
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                evenCount[s1.charAt(i) - 'a']++;
                evenCount[s2.charAt(i) - 'a']--;
            } else {
                oddCount[s1.charAt(i) - 'a']++;
                oddCount[s2.charAt(i) - 'a']--;
            }
        }
        
        // Verify that all counts have neutralized to 0
        for (int i = 0; i < 26; i++) {
            if (evenCount[i] != 0 || oddCount[i] != 0) {
                return false; // A mismatch was found in one of the parity pools
            }
        }
        
        return true;
    }
}
