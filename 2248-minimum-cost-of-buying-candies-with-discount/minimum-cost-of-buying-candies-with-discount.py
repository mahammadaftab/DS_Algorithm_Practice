class Solution:
    def minimumCost(self, cost: list[int]) -> int:
        # Sort candies from most expensive to cheapest
        cost.sort(reverse=True)
        
        total_cost = 0
        
        for i in range(len(cost)):
            # Every 3rd item (index 2, 5, 8...) is our free item!
            if (i + 1) % 3 == 0:
                continue
            total_cost += cost[i]
            
        return total_cost

