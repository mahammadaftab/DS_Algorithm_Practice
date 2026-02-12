class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int maxLen = 0;
        
        // Iterate over all possible starting positions
        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            int distinctCount = 0;
            int maxFreq = 0;
            
            // Extend the substring to the right
            for (int j = i; j < n; j++) {
                int charIdx = s.charAt(j) - 'a';
                
                // If this is the first time seeing this character in the current window
                if (freq[charIdx] == 0) {
                    distinctCount++;
                }
                
                freq[charIdx]++;
                
                // Track the maximum frequency of any character in the current window
                maxFreq = Math.max(maxFreq, freq[charIdx]);
                
                int currentLen = j - i + 1;
                
                // Validation:
                // If we have 'distinctCount' unique characters, and the most frequent one
                // appears 'maxFreq' times, then for ALL of them to appear 'maxFreq' times,
                // the total length must be exactly (distinctCount * maxFreq).
                if (maxFreq * distinctCount == currentLen) {
                    maxLen = Math.max(maxLen, currentLen);
                }
            }
        }
        
        return maxLen;
    }
}

