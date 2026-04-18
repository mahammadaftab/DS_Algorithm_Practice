class Solution:
    def mirrorDistance(self, n: int) -> int:
        # Convert to string, reverse it, convert back to int, and calculate absolute difference
        return abs(n - int(str(n)[::-1]))
