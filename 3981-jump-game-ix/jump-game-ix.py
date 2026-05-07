class Solution:
    def maxValue(self, nums: list[int]) -> list[int]:
        n = len(nums)
        ans = [0] * n
        
        # 1. Precompute the prefix maximums
        pre_max = [0] * n
        pre_max[0] = nums[0]
        for i in range(1, n):
            pre_max[i] = max(pre_max[i - 1], nums[i])
            
        suf_min = float('inf')
        
        # 2. Traverse from right to left to evaluate boundaries
        for i in range(n - 1, -1, -1):
            if pre_max[i] > suf_min:
                # Boundary is open: inherit the right side's maximum reach
                ans[i] = ans[i + 1]
            else:
                # Boundary is closed: stuck with the prefix maximum
                ans[i] = pre_max[i]
                
            # Update the suffix minimum for the next step leftwards
            suf_min = min(suf_min, nums[i])
            
        return ans

