class Solution:
    def maxDistance(self, nums1: list[int], nums2: list[int]) -> int:
        i, j = 0, 0
        m, n = len(nums1), len(nums2)
        max_dist = 0
        
        while i < m and j < n:
            if nums1[i] <= nums2[j]:
                # Valid pair: Update the maximum distance and stretch j further right
                max_dist = max(max_dist, j - i)
                j += 1
            else:
                # Invalid pair: nums2[j] is too small, so we must advance i to find a smaller nums1[i]
                i += 1
                
        return max_dist
