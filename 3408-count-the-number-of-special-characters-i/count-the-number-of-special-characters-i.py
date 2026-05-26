class Solution:
    def numberOfSpecialChars(self, word: str) -> int:
        lower_set = set()
        upper_set = set()
        
        # 1. Distribute characters into their respective sets
        for char in word:
            if char.islower():
                lower_set.add(char)
            else:
                upper_set.add(char)
                
        special_count = 0
        
        # 2. Check each lowercase character that we found
        for char in lower_set:
            # If its uppercase counterpart also exists, it's special
            if char.upper() in upper_set:
                special_count += 1
                
        return special_count

