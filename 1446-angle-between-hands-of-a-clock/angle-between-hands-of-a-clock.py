class Solution:
    def angleClock(self, hour: int, minutes: int) -> float:
        # 1. Calculate the minute hand's position in degrees
        minutes_angle = minutes * 6
        
        # 2. Calculate the hour hand's position in degrees (including the minute drift)
        hour_angle = (hour % 12) * 30 + minutes * 0.5
        
        # 3. Find the absolute difference
        angle = abs(hour_angle - minutes_angle)
        
        # 4. Return the smaller internal angle
        return min(angle, 360 - angle)
