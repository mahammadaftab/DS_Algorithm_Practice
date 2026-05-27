class Solution:
    def numberOfSpecialChars(self, word: str) -> int:
        # Arrays to track indices for the 26 English letters
        last_lowercase = [-1] * 26
        first_uppercase = [-1] * 26
        
        # Single pass to log critical index markers
        for i, char in enumerate(word):
            if char.islower():
                ascii_idx = ord(char) - ord('a')
                last_lowercase[ascii_idx] = i  # Continually update to get the LAST index
            else:
                ascii_idx = ord(char) - ord('A')
                if first_uppercase[ascii_idx] == -1:
                    first_uppercase[ascii_idx] = i  # Only update once to get the FIRST index
                    
        special_count = 0
        
        # Verify conditions for all 26 letters
        for i in range(26):
            if last_lowercase[i] != -1 and first_uppercase[i] != -1:
                # Valid only if the last lowercase appears before the first uppercase
                if last_lowercase[i] < first_uppercase[i]:
                    special_count += 1
                    
        return special_count