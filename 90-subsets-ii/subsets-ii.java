import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    /**
     * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
     *
     * Note:
     *  - The solution set must not contain duplicate subsets.
     *  - The subsets must be in non-descending order.
     *
     * Example:
     *  Input: [1,2,2]
     *  Output:
     *  [
     *    [2],
     *    [1],
     *    [1,2,2],
     *    [2,2],
     *    [1,2],
     *    []
     *  ]
     *
     * @param nums The input array of integers that might contain duplicates.
     * @return A list of lists, where each inner list represents a subset.
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // Sort the input array to handle duplicates effectively.
        generateSubsets(nums, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * Recursive helper function to generate subsets.
     *
     * @param nums   The input array of integers.
     * @param index  The current index in the array.
     * @param currentSubset The current subset being built.
     * @param result The list to store the generated subsets.
     */
    private void generateSubsets(int[] nums, int index, List<Integer> currentSubset, List<List<Integer>> result) {
        // Add a copy of the current subset to the result.
        result.add(new ArrayList<>(currentSubset));

        // Iterate through the remaining elements in the array.
        for (int i = index; i < nums.length; i++) {
            // Skip duplicate elements to avoid duplicate subsets.
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }

            // Include the current element in the subset.
            currentSubset.add(nums[i]);

            // Recursively generate subsets with the current element included.
            generateSubsets(nums, i + 1, currentSubset, result);

            // Backtrack: Remove the current element to explore other possibilities.
            currentSubset.remove(currentSubset.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution subsetsII = new Solution();
        int[] nums = {1, 2, 2};
        List<List<Integer>> subsets = subsetsII.subsetsWithDup(nums);

        System.out.println("Subsets with duplicates:");
        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }
}
