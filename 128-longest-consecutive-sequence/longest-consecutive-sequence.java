import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Use a HashSet for O(1) lookups and removals.
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        // Iterate through the original numbers.
        for (int num : nums) {
            // If the number is still in the set, it means we haven't processed it yet.
            if (numSet.contains(num)) {
                // Remove the number so we don't count it again.
                numSet.remove(num);
                int currentStreak = 1;

                // Search downwards for consecutive numbers.
                int prev = num - 1;
                while (numSet.contains(prev)) {
                    numSet.remove(prev);
                    currentStreak++;
                    prev--;
                }

                // Search upwards for consecutive numbers.
                int next = num + 1;
                while (numSet.contains(next)) {
                    numSet.remove(next);
                    currentStreak++;
                    next++;
                }
                
                // Update the maximum streak found.
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}