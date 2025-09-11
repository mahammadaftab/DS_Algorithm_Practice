import java.util.Arrays;

class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            int tempMax = Math.max(current, Math.max(maxSoFar * current, minSoFar * current));
            minSoFar = Math.min(current, Math.min(maxSoFar * current, minSoFar * current));

            maxSoFar = tempMax;
            result = Math.max(result, maxSoFar);
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums1 = {2, 3, -2, 4};
        System.out.println("Maximum product of " + Arrays.toString(nums1) + ": " + solution.maxProduct(nums1));

        int[] nums2 = {-2, 0, -1};
        System.out.println("Maximum product of " + Arrays.toString(nums2) + ": " + solution.maxProduct(nums2));

        int[] nums3 = {-2, 3, -4};
        System.out.println("Maximum product of " + Arrays.toString(nums3) + ": " + solution.maxProduct(nums3));

        int[] nums4 = {-4, -3};
        System.out.println("Maximum product of " + Arrays.toString(nums4) + ": " + solution.maxProduct(nums4));

        int[] nums5 = {-1, -1, -1};
        System.out.println("Maximum product of " + Arrays.toString(nums5) + ": " + solution.maxProduct(nums5));

        int[] nums6 = {0, 2};
        System.out.println("Maximum product of " + Arrays.toString(nums6) + ": " + solution.maxProduct(nums6));

    }
}
