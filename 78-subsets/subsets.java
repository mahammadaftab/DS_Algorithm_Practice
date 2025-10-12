import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * Generates all possible subsets of a given set using bit manipulation.
     * 
     * @param nums The input array representing the set.
     * @return A list of lists, where each inner list represents a subset.
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        // The total number of subsets is 2^n
        for (int i = 0; i < (1 << n); i++) {
            List<Integer> subset = new ArrayList<>();
            // Iterate through each element of the input array
            for (int j = 0; j < n; j++) {
                // If the j-th bit of i is set, then include nums[j] in the current subset
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            result.add(subset);
        }

        return result;
    }

    public static void main(String[] args) {
        Solution subsetsGenerator = new Solution();
        int[] nums = {1, 2, 3};
        List<List<Integer>> allSubsets = subsetsGenerator.subsets(nums);

        System.out.println("Subsets of {1, 2, 3}: ");
        for (List<Integer> subset : allSubsets) {
            System.out.println(subset);
        }
    }
}