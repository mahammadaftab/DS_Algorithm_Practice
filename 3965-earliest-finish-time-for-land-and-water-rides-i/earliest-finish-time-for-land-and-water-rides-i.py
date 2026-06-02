class Solution:
    def earliestFinishTime(self, landStartTime: list[int], landDuration: list[int], waterStartTime: list[int], waterDuration: list[int]) -> int:
        
        def calculate_sequence(start1: list[int], duration1: list[int], start2: list[int], duration2: list[int]) -> int:
            # Step 1: Find the absolute earliest completion time for the first ride type
            earliest_first_finish = min(s + d for s, d in zip(start1, duration1))
            
            # Step 2: Enumerate all options for the second ride type and take the minimum overall finish time
            # We can start ride 2 at max(its opening time, when we finished ride 1)
            earliest_total_finish = min(max(s2, earliest_first_finish) + d2 for s2, d2 in zip(start2, duration2))
            
            return earliest_total_finish

        # Test Case Option 1: Experiencing Land Ride first, then Water Ride
        land_then_water = calculate_sequence(landStartTime, landDuration, waterStartTime, waterDuration)
        
        # Test Case Option 2: Experiencing Water Ride first, then Land Ride
        water_then_land = calculate_sequence(waterStartTime, waterDuration, landStartTime, landDuration)
        
        # Return the minimum total finish time among both sequences
        return min(land_then_water, water_then_land)
