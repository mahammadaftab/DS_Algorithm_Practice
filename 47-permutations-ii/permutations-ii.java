import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // Sort the input array to handle duplicates efficiently.
        boolean[] used = new boolean[nums.length]; // Keep track of which numbers have been used in the current permutation.
        List<Integer> currentPermutation = new ArrayList<>();

        permuteHelper(nums, used, currentPermutation, result);

        return result;
    }

    private void permuteHelper(int[] nums, boolean[] used, List<Integer> currentPermutation, List<List<Integer>> result) {
        if (currentPermutation.size() == nums.length) {
            result.add(new ArrayList<>(currentPermutation)); // Add a copy of the current permutation to the result.
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue; // Skip if the number has already been used.
            }

            // Skip duplicate numbers to generate unique permutations.
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            used[i] = true; // Mark the number as used.
            currentPermutation.add(nums[i]); // Add the number to the current permutation.
            permuteHelper(nums, used, currentPermutation, result); // Recursively generate permutations.
            currentPermutation.remove(currentPermutation.size() - 1); // Backtrack: remove the number from the current permutation.
            used[i] = false; // Mark the number as unused.
        }
    }

    public static void main(String[] args) {
        Solution permutationsII = new Solution();
        int[] nums = {1, 1, 2};
        List<List<Integer>> result = permutationsII.permuteUnique(nums);
        System.out.println(result);
        // Expected output: [[1, 1, 2], [1, 2, 1], [2, 1, 1]]
    }
}