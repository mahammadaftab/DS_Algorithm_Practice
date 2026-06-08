class Solution:
    def pivotArray(self, nums: list[int], pivot: int) -> list[int]:
        less = []
        equal = []
        greater = []
        
        # Single pass to filter numbers into their respective categories
        for num in nums:
            if num < pivot:
                less.append(num)
            elif num == pivot:
                equal.append(num)
            else:
                greater.append(num)
                
        # Combine the buckets together in the correct sequence
        return less + equal + greater