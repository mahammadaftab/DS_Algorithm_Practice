from collections import deque, defaultdict

class Solution:
    def minJumps(self, nums: list[int]) -> int:
        n = len(nums)
        if n <= 1:
            return 0
            
        max_val = max(nums)
        
        # 1. Sieve of Eratosthenes to find the Smallest Prime Factor (SPF)
        spf = list(range(max_val + 1))
        for i in range(2, int(max_val**0.5) + 1):
            if spf[i] == i:
                for j in range(i * i, max_val + 1, i):
                    if spf[j] == j:
                        spf[j] = i
                        
        # 2. Build the Teleport Buckets (Prime -> List of Indices)
        buckets = defaultdict(list)
        for i, num in enumerate(nums):
            temp = num
            while temp > 1:
                p = spf[temp]
                buckets[p].append(i)
                # Remove all identical factors to only process unique primes
                while temp % p == 0:
                    temp //= p
                    
        # 3. Breadth-First Search
        queue = deque([0])
        visited = [False] * n
        visited[0] = True
        
        jumps = 0
        while queue:
            size = len(queue)
            for _ in range(size):
                curr = queue.popleft()
                
                # Destination reached
                if curr == n - 1:
                    return jumps
                    
                # Evaluate Adjacent Steps
                for nxt in (curr - 1, curr + 1):
                    if 0 <= nxt < n and not visited[nxt]:
                        visited[nxt] = True
                        queue.append(nxt)
                        
                # Evaluate Prime Teleportation
                val = nums[curr]
                # A number is prime if it is greater than 1 and its SPF is itself
                if val >= 2 and spf[val] == val:
                    if val in buckets:
                        for nxt in buckets[val]:
                            if not visited[nxt]:
                                visited[nxt] = True
                                queue.append(nxt)
                        # CRITICAL: Delete the bucket to ensure O(N) linear performance
                        del buckets[val]
                        
            jumps += 1
            
        return -1

