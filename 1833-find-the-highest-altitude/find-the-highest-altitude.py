class Solution:
    def largestAltitude(self, gain: list[int]) -> int:
        max_altitude = 0
        current_altitude = 0
        
        # Iterate through each altitude change
        for g in gain:
            current_altitude += g
            # Update the highest point reached so far
            if current_altitude > max_altitude:
                max_altitude = current_altitude
                
        return max_altitude