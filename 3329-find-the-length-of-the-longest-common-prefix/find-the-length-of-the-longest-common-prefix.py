class Solution:
    def longestCommonPrefix(self, arr1: list[int], arr2: list[int]) -> int:
        prefixes = set()
        
        # 1. Store all possible prefixes from arr1
        for num in arr1:
            while num > 0:
                prefixes.add(num)
                num //= 10  # Chop off the last digit
                
        max_len = 0
        
        # 2. Check prefixes of arr2 against our set
        for num in arr2:
            while num > 0:
                # The moment we find a match, it is the longest possible for this number
                if num in prefixes:
                    max_len = max(max_len, len(str(num)))
                    break
                num //= 10
                
        return max_len
