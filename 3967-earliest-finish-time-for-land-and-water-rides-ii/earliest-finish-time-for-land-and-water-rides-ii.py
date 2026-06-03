import bisect

class Solution:
    def earliestFinishTime(self, landStartTime: list[int], landDuration: list[int], waterStartTime: list[int], waterDuration: list[int]) -> int:
        
        def solve_one_direction(start1, duration1, start2, duration2):
            n, m = len(start1), len(start2)
            
            # 1. Pair and sort the second ride category by its opening start time
            rides2 = sorted(zip(start2, duration2), key=lambda x: x[0])
            s2_sorted = [r[0] for r in rides2]
            
            # 2. Precalculate Prefix Minimums for Durations (when ride 2 is already open)
            prefix_min_duration = [float('inf')] * m
            curr_min = float('inf')
            for i in range(m):
                curr_min = min(curr_min, rides2[i][1])
                prefix_min_duration[i] = curr_min
                
            # 3. Precalculate Suffix Minimums for Natural End Times (when we have to wait for ride 2)
            suffix_min_end_time = [float('inf')] * m
            curr_min = float('inf')
            for i in range(m - 1, -1, -1):
                curr_min = min(curr_min, rides2[i][0] + rides2[i][1])
                suffix_min_end_time[i] = curr_min
                
            best_total_finish = float('inf')
            
            # 4. Evaluate each ride option from category 1
            for s1, d1 in zip(start1, duration1):
                f1 = s1 + d1 # Finish time of the first ride
                
                # Find the boundary index where ride 2 transitions from "already open" to "must wait"
                idx = bisect.bisect_right(s2_sorted, f1)
                
                # Zone 1: Ride 2 is already open (indices from 0 to idx - 1)
                if idx > 0:
                    best_total_finish = min(best_total_finish, f1 + prefix_min_duration[idx - 1])
                    
                # Zone 2: We must wait for Ride 2 to open (indices from idx to m - 1)
                if idx < m:
                    best_total_finish = min(best_total_finish, suffix_min_end_time[idx])
                    
            return best_total_finish

        # Strategy check: Plan A (Land then Water) vs Plan B (Water then Land)
        plan_a = solve_one_direction(landStartTime, landDuration, waterStartTime, waterDuration)
        plan_b = solve_one_direction(waterStartTime, waterDuration, landStartTime, landDuration)
        
        return min(plan_a, plan_b)

