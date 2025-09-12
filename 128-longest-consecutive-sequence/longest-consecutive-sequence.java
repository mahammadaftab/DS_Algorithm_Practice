import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestConsecutive(int[] nums) {
        // Handle the edge case of an empty or null array.
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 1. Create a HashSet for fast O(1) average-time lookups.
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        // 2. Iterate through each number in the set.
        for (int num : numSet) {
            
            // 3. THE KEY OPTIMIZATION:
            // Only start a new sequence count if 'num' is the true beginning of a sequence.
            // We know it's a start if the set does NOT contain the number just before it (num - 1).
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // 4. If it's a starting number, enter the while loop to count its sequence length.
                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                // 5. Update the overall longest streak found so far.
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}