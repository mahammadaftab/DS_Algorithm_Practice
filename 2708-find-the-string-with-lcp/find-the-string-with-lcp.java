class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];
        char currentChar = 'a';
        
        // 1. Greedily construct the lexicographically smallest string
        for (int i = 0; i < n; i++) {
            if (word[i] == 0) { // If unassigned
                if (currentChar > 'z') return ""; // Exceeded 26 lowercase letters
                
                word[i] = currentChar;
                // Find all other indices that must share this character
                for (int j = i + 1; j < n; j++) {
                    if (lcp[i][j] > 0) {
                        word[j] = currentChar;
                    }
                }
                currentChar++; // Move to the next available letter
            }
        }
        
        // 2. Validate the constructed string against the LCP matrix
        // We traverse backwards to build the LCP dynamically
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int expectedLCP = 0;
                
                if (word[i] == word[j]) {
                    // 1 + the LCP of the remaining suffixes
                    expectedLCP = 1 + ((i + 1 < n && j + 1 < n) ? lcp[i + 1][j + 1] : 0);
                }
                
                if (lcp[i][j] != expectedLCP) {
                    return ""; // Contradiction found
                }
            }
        }
        
        return new String(word);
    }
}