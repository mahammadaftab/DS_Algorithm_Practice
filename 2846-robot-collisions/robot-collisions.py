class Solution:
    def survivedRobotsHealths(self, positions: list[int], healths: list[int], directions: str) -> list[int]:
        n = len(positions)
        # Create a list of [position, health, direction, original_index]
        robots = [[positions[i], healths[i], directions[i], i] for i in range(n)]
        
        # Sort robots by their starting positions (left to right)
        robots.sort(key=lambda x: x[0])
        
        stack = []
        
        for robot in robots:
            # If moving right, just add to stack
            if robot[2] == 'R':
                stack.append(robot)
                continue
                
            # If moving left, resolve potential collisions
            survives = True
            while stack and stack[-1][2] == 'R':
                # Compare healths
                if stack[-1][1] < robot[1]:
                    stack.pop()
                    robot[1] -= 1
                elif stack[-1][1] > robot[1]:
                    stack[-1][1] -= 1
                    survives = False
                    break
                else:
                    # Both have equal health, both are destroyed
                    stack.pop()
                    survives = False
                    break
            
            # If the left-moving robot survived all collisions, add it to stack
            if survives:
                stack.append(robot)
                
        # Sort the surviving robots by their original index to format the output
        stack.sort(key=lambda x: x[3])
        
        return [robot[1] for robot in stack]
