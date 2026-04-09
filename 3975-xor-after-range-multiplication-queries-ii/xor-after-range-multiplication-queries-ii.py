import math

class Solution:
    def xorAfterQueries(self, nums: list[int], queries: list[list[int]]) -> int:
        n = len(nums)
        MOD = 10**9 + 7
        B = int(math.sqrt(n))
        
        # total_mult[i] will store the final accumulated multiplier for nums[i]
        total_mult = [1] * n
        
        # Group small queries by their step size 'k'
        small_q = [[] for _ in range(B + 1)]
        
        for l, r, k, v in queries:
            if k > B:
                # Large k: Brute force it
                for i in range(l, r + 1, k):
                    total_mult[i] = (total_mult[i] * v) % MOD
            else:
                # Small k: Queue it for the difference array
                small_q[k].append((l, r, v))
                
        # Process small queries using multiplicative difference arrays
        for k in range(1, B + 1):
            if not small_q[k]:
                continue
                
            diff = [1] * n
            for l, r, v in small_q[k]:
                diff[l] = (diff[l] * v) % MOD
                
                # Calculate the exact final index this query hits
                last_idx = l + ((r - l) // k) * k
                
                if last_idx + k < n:
                    # Cancel out the multiplier using modular inverse
                    inv_v = pow(v, MOD - 2, MOD)
                    diff[last_idx + k] = (diff[last_idx + k] * inv_v) % MOD
                    
            # Sweep the difference array to propagate the multipliers
            for i in range(n):
                if i >= k:
                    diff[i] = (diff[i] * diff[i - k]) % MOD
                # Apply the resolved multipliers to our master total
                total_mult[i] = (total_mult[i] * diff[i]) % MOD
                
        # Calculate the final XOR sum
        ans = 0
        for i in range(n):
            final_val = (nums[i] * total_mult[i]) % MOD
            ans ^= final_val
            
        return ans

