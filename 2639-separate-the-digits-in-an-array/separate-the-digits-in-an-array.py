class Solution:
    def separateDigits(self, nums: list[int]) -> list[int]:
        ans = []
        for num in nums:
            # Convert the number to a string to loop through its digits
            for digit in str(num):
                # Convert the character back to an integer and store it
                ans.append(int(digit))
                
        return ans

        # Alternative 1-liner:
        # return [int(digit) for num in nums for digit in str(num)]