class Solution:
    def processStr(self, s: str) -> str:
        # We use a list as a stack/buffer for O(1) operations
        result = []
        
        for char in s:
            if 'a' <= char <= 'z':
                # Append lowercase letters
                result.append(char)
            elif char == '*':
                # Backspace operation: remove last element if it exists
                if result:
                    result.pop()
            elif char == '#':
                # Duplicate the current list content
                result.extend(result)
            elif char == '%':
                # Reverse the list in-place
                result.reverse()
                
        return "".join(result)