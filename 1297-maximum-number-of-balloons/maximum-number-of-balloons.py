from collections import Counter

class Solution:
    def maxNumberOfBalloons(self, text: str) -> int:
        # 1. Count the frequency of all characters in the input text
        counts = Counter(text)
        
        # 2. Return the strict minimum bottleneck scaled by requirements
        return min(
            counts['b'],
            counts['a'],
            counts['l'] // 2,
            counts['o'] // 2,
            counts['n']
        )