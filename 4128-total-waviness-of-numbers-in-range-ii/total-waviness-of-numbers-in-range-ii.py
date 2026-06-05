class Solution:
    def totalWaviness(self, num1: int, num2: int) -> int:
        # Convert integers to strings right away to handle index-based positioning
        str_num1 = str(num1)
        str_num2 = str(num2)
        
        def solve(limit_str: str) -> int:
            length = len(limit_str)
            # Memoization table: 
            # (idx, prev_digit, is_less, is_started, trend)
            memo = {}
            
            def dp(idx, prev, is_less, is_started, trend):
                # Base Case: All digits placed
                if idx == length:
                    return 0
                    
                state = (idx, prev, is_less, is_started, trend)
                if state in memo:
                    return memo[state]
                
                # Determine the maximum digit we can place at this index
                limit = 9 if is_less else int(limit_str[idx])
                ans = 0
                
                for d in range(limit + 1):
                    next_is_less = is_less or (d < limit)
                    next_is_started = is_started or (d > 0)
                    
                    if not next_is_started:
                        # Still placing leading zeros
                        ans += dp(idx + 1, -1, next_is_less, False, 0)
                    else:
                        wave_contribution = 0
                        next_trend = 0
                        
                        # If a number has actually started being formed
                        if is_started:
                            # Establish the trend direction between prev and current d
                            if d > prev:
                                next_trend = 1
                            elif d < prev:
                                next_trend = -1
                            else:
                                next_trend = 0
                                
                            # Check for a Peak or Valley configuration
                            if trend == 1 and d < prev:    # Up then Down -> Peak
                                wave_contribution = 1
                            elif trend == -1 and d > prev:  # Down then Up -> Valley
                                wave_contribution = 1
                        
                        # Find how many valid configurations can follow this current choice path
                        num_ways = count_ways(idx + 1, d, next_is_less, True, limit_str)
                        
                        ans += wave_contribution * num_ways + dp(idx + 1, d, next_is_less, True, next_trend)
                        
                memo[state] = ans
                return ans
            
            # Helper to count total paths down the suffix tree
            ways_memo = {}
            def count_ways(idx, prev, is_less, is_started, s_limit):
                if idx == len(s_limit):
                    return 1
                w_state = (idx, prev, is_less, is_started)
                if w_state in ways_memo:
                    return ways_memo[w_state]
                
                limit = 9 if is_less else int(s_limit[idx])
                total_ways = 0
                for d in range(limit + 1):
                    total_ways += count_ways(idx + 1, d, is_less or (d < limit), is_started or (d > 0), s_limit)
                ways_memo[w_state] = total_ways
                return total_ways

            return dp(0, -1, False, False, 0)

        # Calculate range count using prefix subtraction: solve(num2) - solve(num1 - 1)
        ans_num2 = solve(str_num2)
        
        num1_minus_1 = str(num1 - 1)
        ans_num1 = solve(num1_minus_1) if num1 > 0 else 0
        
        return ans_num2 - ans_num1

        