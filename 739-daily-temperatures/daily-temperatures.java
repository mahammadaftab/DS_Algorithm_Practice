import java.util.Stack;

class Solution {
    /**
     * LeetCode Problem 739: Daily Temperatures
     *
     * Given an array of integers temperatures represents the daily temperatures,
     * return an array answer such that answer[i] is the number of days you have to wait
     * after the i-th day to get a warmer temperature. If there is no future day for which this is possible,
     * keep answer[i] == 0 instead.
     *
     * Example 1:
     * Input: temperatures = [73,74,75,71,69,72,76,73]
     * Output: [1,1,4,2,1,1,0,0]
     *
     * Example 2:
     * Input: temperatures = [30,40,50,60]
     * Output: [1,1,1,0]
     *
     * Example 3:
     * Input: temperatures = [30,60,90]
     * Output: [1,1,0]
     *
     * Constraints:
     * 1 <= temperatures.length <= 10^5
     * 30 <= temperatures[i] <= 100
     *
     * @param temperatures The array of daily temperatures.
     * @return An array where each element is the number of days until a warmer temperature.
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];

        // Stack to store indices of temperatures we're waiting for a warmer day for
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // While the stack is not empty and the current temperature is warmer than
            // the temperature at the index on the top of the stack,
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                // Pop the index from the stack.
                int index = stack.pop();
                // Calculate the number of days until a warmer temperature.
                answer[index] = i - index;
            }
            // Push the current index onto the stack.
            stack.push(i);
        }

        // If there are remaining indices in the stack, it means there was no warmer day,
        // so the answer for those indices is 0 (default value in the initialized array).

        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] temperatures1 = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result1 = sol.dailyTemperatures(temperatures1);
        System.out.print("Example 1: ");
        for (int val : result1) {
            System.out.print(val + " ");
        }
        System.out.println();

        int[] temperatures2 = {30, 40, 50, 60};
        int[] result2 = sol.dailyTemperatures(temperatures2);
        System.out.print("Example 2: ");
        for (int val : result2) {
            System.out.print(val + " ");
        }
        System.out.println();

        int[] temperatures3 = {30, 60, 90};
        int[] result3 = sol.dailyTemperatures(temperatures3);
        System.out.print("Example 3: ");
        for (int val : result3) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}

