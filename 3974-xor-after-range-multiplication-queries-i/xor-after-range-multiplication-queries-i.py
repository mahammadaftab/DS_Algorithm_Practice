class Solution:
    def xorAfterQueries(self, nums: list[int], queries: list[list[int]]) -> int:
        MOD = 10**9 + 7
        
        # 1. Simulate the multiplication jumps for each query
        for l, r, k, v in queries:
            for idx in range(l, r + 1, k):
                nums[idx] = (nums[idx] * v) % MOD
                
        # 2. Calculate the final bitwise XOR sum
        ans = 0
        for num in nums:
            ans ^= num
            
        return ans