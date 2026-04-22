class Solution:
    def twoEditWords(self, queries: list[str], dictionary: list[str]) -> list[str]:
        ans = []
        
        for query in queries:
            for dict_word in dictionary:
                # Count how many characters differ at the same index
                edits = 0
                for c1, c2 in zip(query, dict_word):
                    if c1 != c2:
                        edits += 1
                    
                    # Optimization: Early exit if we already exceeded 2 edits
                    if edits > 2:
                        break
                        
                # If we finished the word with 2 or fewer edits, it's a match
                if edits <= 2:
                    ans.append(query)
                    break # Stop checking this query against the rest of the dictionary
                    
        return ans

