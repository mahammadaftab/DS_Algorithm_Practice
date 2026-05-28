class TrieNode:
    def __init__(self):
        self.children = {}
        # Stores the index of the best matching word passing through or ending at this node
        self.best_word_index = -1

class Solution:
    def stringIndices(self, wordsContainer: list[str], wordsQuery: list[str]) -> list[int]:
        root = TrieNode()
        
        # Helper function to check if word 'i' is better than word 'j'
        def is_better(i, j):
            if j == -1:
                return True
            if len(wordsContainer[i]) < len(wordsContainer[j]):
                return True
            if len(wordsContainer[i]) == len(wordsContainer[j]) and i < j:
                return True
            return False

        # 1. Build the Trie with reversed words
        for idx, word in enumerate(wordsContainer):
            curr = root
            # Even the root note needs a default fallback answer (longest match length 0)
            if is_better(idx, curr.best_word_index):
                curr.best_word_index = idx
                
            # Insert the word backwards
            for char in reversed(word):
                if char not in curr.children:
                    curr.children[char] = TrieNode()
                curr = curr.children[char]
                
                # Update the node's best word tracker on the fly
                if is_better(idx, curr.best_word_index):
                    curr.best_word_index = idx

        # 2. Process each query
        ans = []
        for query in wordsQuery:
            curr = root
            # Walk down the trie using the reversed query characters
            for char in reversed(query):
                if char in curr.children:
                    curr = curr.children[char]
                else:
                    break  # Suffix match breaks here
            
            # The node we ended on holds the pre-calculated best index
            ans.append(curr.best_word_index)
            
        return ans

