import java.util.*;

class Solution {
    /**
     * LeetCode Problem 134: Gas Station
     *
     * There are n gas stations along a circular route, where the amount of gas at the i-th station is gas[i].
     *
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the i-th station to the next (i+1)-th station.
     * You begin the journey with an empty tank at one of the gas stations.
     *
     * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction,
     * otherwise return -1. If there exists a solution, it is guaranteed to be unique
     *
     * Example 1:
     *
     * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
     * Output: 3
     * Explanation:
     * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
     * Travel to station 4. Your tank = 4 - 1 + 5 = 8
     * Travel to station 0. Your tank = 8 - 2 + 1 = 7
     * Travel to station 1. Your tank = 7 - 3 + 2 = 6
     * Travel to station 2. Your tank = 6 - 4 + 3 = 5
     * Travel to station 3. The cost is 5. Your gas is actually sufficient. Your tank = 5 - 5 = 0
     * You can complete the circuit from station 3.
     *
     * Example 2:
     *
     * Input: gas = [2,3,4], cost = [3,4,3]
     * Output: -1
     * Explanation:
     * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
     * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
     * Travel to station 0. Your tank = 4 - 3 + 2 = 3
     * Travel to station 1. Your tank = 3 - 3 + 3 = 3
     * You cannot travel to station 2, as it takes 4 unit of gas, and we only have 3 unit of gas.
     *
     * Constraints:
     *
     * n == gas.length == cost.length
     * 1 <= n <= 105
     * 0 <= gas[i], cost[i] <= 104
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;

        // If total gas is less than total cost, then no solution exists
        int totalGas = 0;
        int totalCost = 0;
        for (int i = 0; i < n; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }

        if (totalGas < totalCost) {
            return -1;
        }

        // Iterate through each gas station as a potential starting point
        int start = 0;
        int currentGas = 0;

        for (int i = 0; i < n; i++) {
            currentGas += gas[i] - cost[i];

            // If at any point, currentGas becomes negative, it means we cannot reach
            // from 'start' to 'i'. Hence we choose the next station 'i + 1' as the new 'start'
            if (currentGas < 0) {
                start = i + 1;
                currentGas = 0;
            }
        }

        return start;
    }
}
