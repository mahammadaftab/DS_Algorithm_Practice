import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    /**
     * LeetCode Problem 40: Combination Sum II
     * 
     * Given a collection of candidate numbers (candidates) and a target number (target),
     * find all unique combinations in candidates where the candidate numbers sum to target.
     * 
     * Each number in candidates may only be used once in one combination.
     * 
     * Note:
     * - All numbers (including target) will be positive integers.
     * - The solution set must not contain duplicate combinations.
     *
     * Example:
     * candidates = [10,1,2,7,6,1,5], target = 8,
     * A solution set is:
     * [
     *   [1, 7],
     *   [1, 2, 5],
     *   [2, 6],
     *   [1, 1, 6]
     * ]
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // Sort the candidates to easily skip duplicates.
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> combination, int[] candidates, int remaining, int start) {
        if (remaining == 0) {
            result.add(new ArrayList<>(combination)); // Found a valid combination
            return;
        }
        if (remaining < 0) {
            return; // Target exceeded
        }

        for (int i = start; i < candidates.length; i++) {
            // Skip duplicate numbers to avoid duplicate combinations
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            combination.add(candidates[i]);
            backtrack(result, combination, candidates, remaining - candidates[i], i + 1); // Recursive call with i+1 to avoid using the same element again.
            combination.remove(combination.size() - 1); // Backtrack: remove the last added element to explore other combinations.
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> combinations = sol.combinationSum2(candidates, target);

        System.out.println("Combinations that sum to " + target + ":");
        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }
    }
}
