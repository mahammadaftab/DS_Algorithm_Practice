class Solution:
    def mapWordWeights(self, words: list[str], weights: list[int]) -> str:
        result = []
        
        for word in words:
            # 1. Calculate the total weight of the word
            word_weight = 0
            for char in word:
                char_idx = ord(char) - ord('a')
                word_weight += weights[char_idx]
                
            # 2. Compress the weight modulo 26
            remainder = word_weight % 26
            
            # 3. Apply the reverse alphabetical mapping (0 -> 'z', 1 -> 'y', ...)
            mapped_char = chr(ord('z') - remainder)
            result.append(mapped_char)
            
        # Combine the characters into the final resulting string
        return "".join(result)
