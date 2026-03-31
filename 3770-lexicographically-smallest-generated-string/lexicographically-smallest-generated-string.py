class SingleHash:
    def __init__(self, string):
        self.n = len(string)
        self.b = 313
        self.m = 10**18 + 9 # Large prime to avoid collisions
        self.h = [0] * (self.n + 1)
        self.p = [1] * (self.n + 1)
        for i in range(self.n):
            self.p[i+1] = (self.p[i] * self.b) % self.m
            val = ord(string[i]) 
            self.h[i+1] = (self.h[i] * self.b + val) % self.m

    def get_hash(self, l, r):
        if l > r: 
            return 0
        length = r - l + 1
        return (self.h[r+1] - self.h[l] * self.p[length]) % self.m

class Solution:
    # --- Renamed to generateString to match LeetCode's expected signature ---
    def generateString(self, str1: str, str2: str) -> str:
        N, M = len(str1), len(str2)
        L = N + M - 1
        ans = ['?'] * L
        
        # Step 1: Pre-fill 'T' conditions greedily avoiding redundant overwrites
        max_filled = 0
        for i in range(N):
            if str1[i] == 'T':
                start = max(0, max_filled - i)
                for j in range(start, M):
                    ans[i+j] = str2[j]
                max_filled = max(max_filled, i + M)
                
        # Initialize Rolling Hashes
        # We join 'ans' into a string for the initial hash state 
        initial_hash = SingleHash("".join(ans))
        str2_hash = SingleHash(str2)
        
        # Verify 'T's for contradictions
        for i in range(N):
            if str1[i] == 'T':
                if initial_hash.get_hash(i, i+M-1) != str2_hash.get_hash(0, M-1):
                    return ""
                    
        # Find the most recent '?' relative to any position
        prev_q = [-1] * L
        last = -1
        for i in range(L):
            if ans[i] == '?':
                last = i
            prev_q[i] = last
            
        check_list = [[] for _ in range(L)]
        
        # Step 2: Set up checking list for 'F' conditions
        for k in range(N):
            if str1[k] == 'F':
                last_q_idx = prev_q[k+M-1]
                
                if last_q_idx < k:
                    # Case A: Window has no '?' at all. Must ensure it does NOT match str2.
                    if initial_hash.get_hash(k, k+M-1) == str2_hash.get_hash(0, M-1):
                        return ""
                else:
                    # Case B: Identify if the suffix AFTER the last '?' matches str2
                    suffix_l = last_q_idx + 1
                    suffix_r = k + M - 1
                    match = True
                    if suffix_l <= suffix_r:
                        if initial_hash.get_hash(suffix_l, suffix_r) != str2_hash.get_hash(last_q_idx - k + 1, M - 1):
                            match = False
                    
                    # If suffix matches, the final '?' dictates if the 'F' constraint is broken
                    if match:
                        check_list[last_q_idx].append(k)
                        
        # Step 3: Greedy Left-to-Right Pass
        b = initial_hash.b
        m = initial_hash.m
        p = initial_hash.p
        
        # We need a dynamic hash list initialized with 0s
        dyn_h = [0] * (L + 1)
        
        def get_dyn_hash(l, r):
            if l > r: return 0
            length = r - l + 1
            return (dyn_h[r+1] - dyn_h[l] * p[length]) % m
            
        for i in range(L):
            if ans[i] == '?':
                forbidden = set()
                
                # Check all 'F' constraints targeting this specific index as their final unassigned character
                for k in check_list[i]:
                    match = True
                    if k <= i - 1:
                        # Verify if dynamically constructed prefix matches `str2`
                        if get_dyn_hash(k, i - 1) != str2_hash.get_hash(0, i - k - 1):
                            match = False
                            
                    if match:
                        forbidden.add(str2[i - k])
                        
                # Assign the lexicographically smallest un-forbidden character 
                chosen = ''
                for c_code in range(97, 123):  # 'a' to 'z'
                    c = chr(c_code)
                    if c not in forbidden:
                        chosen = c
                        break
                        
                if not chosen:
                    return ""
                ans[i] = chosen
                
            # Step 4: Keep the dynamic rolling hash updated natively
            val = ord(ans[i])
            dyn_h[i+1] = (dyn_h[i] * b + val) % m
            
        return "".join(ans)
