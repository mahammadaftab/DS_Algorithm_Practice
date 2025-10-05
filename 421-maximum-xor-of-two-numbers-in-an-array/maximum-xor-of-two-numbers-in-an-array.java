import java.util.HashSet;
import java.util.Set;

public class Solution {

    /**
     * LeetCode Problem: 421. Maximum XOR of Two Numbers in an Array
     * Difficulty: Hard
     * Topic: Bit Manipulation
     */
    public int findMaximumXOR(int[] nums) {
        int max_xor = 0; // Initialize the maximum XOR to 0
        int mask = 0; // Initialize the mask to 0

        // Iterate from the most significant bit to the least significant bit (31 to 0)
        for (int i = 31; i >= 0; i--) {
            mask |= (1 << i); // Update the mask by including the current bit

            Set<Integer> prefixes = new HashSet<>(); // Create a set to store prefixes
            for (int num : nums) {
                prefixes.add(num & mask); // Add the prefix of each number to the set
            }

            int temp_max = max_xor | (1 << i); // Create a temporary max XOR by setting the current bit

            // Check if there exist two prefixes in the set whose XOR is equal to temp_max
            for (int prefix : prefixes) {
                if (prefixes.contains(temp_max ^ prefix)) {
                    max_xor = temp_max; // If found, update the max XOR
                    break; // No need to check further in this iteration
                }
            }
        }

        return max_xor; // Return the maximum XOR
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example usage
        int[] nums1 = {3, 10, 5, 25, 2, 8};
        int max_xor1 = solution.findMaximumXOR(nums1);
        System.out.println("Maximum XOR for nums1: " + max_xor1); // Expected: 28

        int[] nums2 = {0};
        int max_xor2 = solution.findMaximumXOR(nums2);
        System.out.println("Maximum XOR for nums2: " + max_xor2); // Expected: 0

        int[] nums3 = {2, 4};
        int max_xor3 = solution.findMaximumXOR(nums3);
        System.out.println("Maximum XOR for nums3: " + max_xor3); // Expected: 6
    }
}