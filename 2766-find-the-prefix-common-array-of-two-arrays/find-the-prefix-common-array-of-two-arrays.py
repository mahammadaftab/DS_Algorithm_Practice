class Solution:
    def findThePrefixCommonArray(self, A: list[int], B: list[int]) -> list[int]:
        n = len(A)
        ans = []
        
        # Frequency array of size n + 1 (since numbers go from 1 to n)
        freq = [0] * (n + 1)
        common_count = 0
        
        for i in range(n):
            # Process A[i]
            freq[A[i]] += 1
            if freq[A[i]] == 2:
                common_count += 1
                
            # Process B[i]
            freq[B[i]] += 1
            if freq[B[i]] == 2:
                common_count += 1
                
            # Store the result for the current prefix
            ans.append(common_count)
            
        return ans

