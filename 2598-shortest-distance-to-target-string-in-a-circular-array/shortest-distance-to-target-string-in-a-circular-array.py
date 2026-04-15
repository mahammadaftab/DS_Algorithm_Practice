class Solution:
    # --- Fixed the missing 's' in closestTarget ---
    def closestTarget(self, words: list[str], target: str, startIndex: int) -> int:
        n = len(words)
        min_dist = float('inf')
        
        for i in range(n):
            if words[i] == target:
                # Calculate the two possible paths
                direct_dist = abs(i - startIndex)
                wrap_dist = n - direct_dist
                
                # The shortest distance to THIS specific target
                shortest_to_this = min(direct_dist, wrap_dist)
                
                # Update the global minimum distance
                min_dist = min(min_dist, shortest_to_this)
                
        return min_dist if min_dist != float('inf') else -1
