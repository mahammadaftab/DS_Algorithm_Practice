class Solution:
    def longestBalanced(self, s: str) -> int:
        n = len(s)
        # Identify the unique characters present to determine the mask size
        unique_chars = sorted(list(set(s)))
        K = len(unique_chars)
        char_to_idx = {c: i for i, c in enumerate(unique_chars)}
        
        max_len = 0
        
        # Iterate through every possible non-empty subset of characters (mask)
        # 1 << K is 2^K. For K=5, this loops 1 to 31.
        for mask in range(1, 1 << K):
            # Identify which characters are allowed in this mask
            allowed_indices = []
            for i in range(K):
                if (mask >> i) & 1:
                    allowed_indices.append(i)
            
            # Optimization: If mask has only 1 bit, it's just a single char repetition.
            # (e.g., "aaaa"). The logic below handles it, but requires empty tuple states.
            
            # Counts for the allowed characters
            counts = {idx: 0 for idx in allowed_indices}
            
            # Map stores: State Tuple -> First Index
            # Initial state: all differences are 0 at index -1
            initial_state = tuple([0] * (len(allowed_indices) - 1))
            state_map = {initial_state: -1}
            
            # Traverse the string
            for i, char in enumerate(s):
                idx = char_to_idx[char]
                
                # Check if the current char is allowed in our active mask
                if not ((mask >> idx) & 1):
                    # Forbidden char encountered! 
                    # Reset counts and map because the substring is broken.
                    counts = {idx: 0 for idx in allowed_indices}
                    state_map = {initial_state: i}
                    continue
                
                # Update count for the current character
                counts[idx] += 1
                
                # Compute the current state (differences between adjacent allowed chars)
                # Example: for {a, b, c}, state is (count[a]-count[b], count[b]-count[c])
                current_state_list = []
                for k in range(len(allowed_indices) - 1):
                    u = allowed_indices[k]
                    v = allowed_indices[k+1]
                    current_state_list.append(counts[u] - counts[v])
                
                state_tuple = tuple(current_state_list)
                
                # Check if we have seen this relative state before
                if state_tuple in state_map:
                    max_len = max(max_len, i - state_map[state_tuple])
                else:
                    state_map[state_tuple] = i
                    
        return max_len