class Solution:
    def maxDistance(self, side: int, points: list[list[int]], k: int) -> int:
        # Helper to unroll 2D coordinates into a 1D perimeter coordinate [0, 4*side)
        def get_1d(x, y):
            if y == 0: return x                             # Bottom Edge
            if x == side: return side + y                   # Right Edge
            if y == side: return 2 * side + (side - x)      # Top Edge
            if x == 0: return 3 * side + (side - y)         # Left Edge
            
        # Map 2D points to 1D and sort them
        arr = sorted(get_1d(x, y) for x, y in points)
        n = len(arr)
        
        # Duplicate the array to simulate the circular wrap-around
        arr2 = arr + [d + 4 * side for d in arr]
        
        def check(D):
            # nxt[i] stores the index of the first point >= D distance away
            nxt = [2 * n] * (2 * n + 1)
            j = 0
            for i in range(2 * n):
                while j < 2 * n and arr2[j] - arr2[i] < D:
                    j += 1
                nxt[i] = j
                
            # Optimization: We only need to test starting points up to the first valid gap
            limit = min(n, nxt[0])
            for i in range(limit):
                curr = i
                # Simulate k greedy jumps
                for _ in range(k):
                    curr = nxt[curr]
                    # Early exit if our jumps exceed the circumference of the square
                    if curr > i + n:  
                        break
                
                # If we successfully made k jumps within one loop, this distance is valid!
                if curr <= i + n:
                    return True
            return False

        # Binary Search for the optimal distance
        low = 1
        high = (4 * side) // k
        ans = 1
        
        while low <= high:
            mid = (low + high) // 2
            if check(mid):
                ans = mid
                low = mid + 1  # Distance works, try to go bigger
            else:
                high = mid - 1 # Distance is too large, scale back
                
        return ans