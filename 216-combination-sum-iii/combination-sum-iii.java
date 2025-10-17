import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * LeetCode Problem 216: Combination Sum III
     * Find all valid combinations of k numbers that sum up to n such that:
     * - Only numbers 1 through 9 are used.
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSum3Helper(k, n, 1, new ArrayList<>(), result);
        return result;
    }

    /**
     * Recursive helper function to find combinations.
     *
     * @param k      Remaining number of elements to select.
     * @param target Remaining target sum.
     * @param start  Starting number for the current selection (1 to 9).
     * @param currentCombination The current combination being built.
     * @param result The list to store the valid combinations.
     */
    private void combinationSum3Helper(int k, int target, int start, List<Integer> currentCombination, List<List<Integer>> result) {
        // Base cases:
        if (target < 0) {
            return; // Target sum exceeded, invalid combination
        }
        if (k == 0) {
            if (target == 0) {
                result.add(new ArrayList<>(currentCombination)); // Found a valid combination
            }
            return; // k is zero, but target is not zero, so the combination is invalid
        }

        // Recursive step:
        for (int i = start; i <= 9; i++) {
            currentCombination.add(i);
            combinationSum3Helper(k - 1, target - i, i + 1, currentCombination, result);
            currentCombination.remove(currentCombination.size() - 1); // Backtrack: remove the last added element
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int k = 3;
        int n = 7;
        List<List<Integer>> result = solution.combinationSum3(k, n);
        System.out.println(result); // Expected output: [[1, 2, 4]]

        k = 3;
        n = 9;
        result = solution.combinationSum3(k, n);
        System.out.println(result); // Expected output: [[1, 2, 6], [1, 3, 5], [2, 3, 4]]
    }
}