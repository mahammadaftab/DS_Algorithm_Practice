class Solution:
    def minOperations(self, grid: list[list[int]], x: int) -> int:
        arr = []
        for row in grid:
            arr.extend(row)
            
        # 1. The Modulo Check
        mod = arr[0] % x
        for num in arr:
            if num % x != mod:
                return -1
                
        # 2. Find the Median
        arr.sort()
        median = arr[len(arr) // 2]
        
        # 3. Calculate minimum operations
        ops = 0
        for num in arr:
            ops += abs(num - median) // x
            
        return ops