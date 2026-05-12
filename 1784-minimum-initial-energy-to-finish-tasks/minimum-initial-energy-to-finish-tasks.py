class Solution:
    def minimumEffort(self, tasks: list[list[int]]) -> int:
        # Sort tasks descending by the gap: (minimum - actual)
        tasks.sort(key=lambda x: x[1] - x[0], reverse=True)
        
        current_energy = 0
        total_initial_energy = 0
        
        for actual, minimum in tasks:
            # If we don't have enough energy to start, we must increase our initial pool
            if current_energy < minimum:
                shortfall = minimum - current_energy
                total_initial_energy += shortfall
                current_energy += shortfall
                
            # Perform the task, consuming the actual energy
            current_energy -= actual
            
        return total_initial_energy