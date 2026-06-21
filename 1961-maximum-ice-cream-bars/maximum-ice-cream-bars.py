class Solution:
    def maxIceCream(self, costs: list[int], coins: int) -> int:
        # 1. Sort the costs to prioritize the cheapest ice cream bars
        costs.sort()
        count = 0
        
        # 2. Buy sequentially until we run out of coins
        for cost in costs:
            if coins >= cost:
                coins -= cost
                count += 1
            else:
                # Since the array is sorted, we cannot afford any remaining bars
                break
                
        return count
        