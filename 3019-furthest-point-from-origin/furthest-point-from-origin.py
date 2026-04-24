class Solution:
    def furthestDistanceFromOrigin(self, moves: str) -> int:
        # The absolute difference between L and R gives our net deterministic distance.
        # We then add all wildcards (_) to push us further in that winning direction.
        return abs(moves.count('L') - moves.count('R')) + moves.count('_')