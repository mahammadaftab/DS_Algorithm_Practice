class Solution:
    def judgeCircle(self, moves: str) -> bool:
        # The robot only returns to origin if Up/Down cancel out AND Left/Right cancel out
        return moves.count('U') == moves.count('D') and moves.count('L') == moves.count('R')