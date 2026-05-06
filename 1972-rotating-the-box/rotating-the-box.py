class Solution:
    def rotateTheBox(self, box: list[list[str]]) -> list[list[str]]:
        ROWS, COLS = len(box), len(box[0])
        
        # 1. Apply Horizontal Gravity
        for r in range(ROWS):
            empty_spot = COLS - 1
            # Scan from right to left
            for c in range(COLS - 1, -1, -1):
                if box[r][c] == '*':
                    # Obstacle resets the drop floor
                    empty_spot = c - 1
                elif box[r][c] == '#':
                    # Swap the stone to the lowest available spot
                    box[r][c], box[r][empty_spot] = box[r][empty_spot], box[r][c]
                    empty_spot -= 1
                    
        # 2. Rotate 90 degrees clockwise
        rotated = []
        for c in range(COLS):
            new_row = []
            for r in range(ROWS - 1, -1, -1):
                new_row.append(box[r][c])
            rotated.append(new_row)
            
        return rotated
