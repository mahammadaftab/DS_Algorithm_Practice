import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPermutation = new ArrayList<>();
        boolean[] used = new boolean[nums.length]; // Keep track of which numbers are used

        permuteHelper(nums, used, currentPermutation, result);

        return result;
    }

    private void permuteHelper(int[] nums, boolean[] used, List<Integer> currentPermutation, List<List<Integer>> result) {
        if (currentPermutation.size() == nums.length) {
            // If the current permutation has all the numbers, add it to the result
            result.add(new ArrayList<>(currentPermutation)); // Create a copy to avoid modification issues
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // If the number at index i is not used yet
                used[i] = true; // Mark it as used
                currentPermutation.add(nums[i]); // Add it to the current permutation
                permuteHelper(nums, used, currentPermutation, result); // Recursively generate permutations with the new number
                currentPermutation.remove(currentPermutation.size() - 1); // Backtrack: remove the last number
                used[i] = false; // Mark it as unused
            }
        }
    }

    public static void main(String[] args) {
        Solution permutations = new Solution();
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = permutations.permute(nums);

        System.out.println(result);
    }
}