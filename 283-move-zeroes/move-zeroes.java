import java.util.Arrays;

public class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int insertPos = 0;

        for (int num : nums) {
            if (num != 0) {
                nums[insertPos++] = num;
            }
        }

        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums1 = {0, 1, 0, 3, 12};
        solution.moveZeroes(nums1);
        System.out.println("nums1: " + Arrays.toString(nums1));

        int[] nums2 = {0};
        solution.moveZeroes(nums2);
        System.out.println("nums2: " + Arrays.toString(nums2));

        int[] nums3 = {1, 0};
        solution.moveZeroes(nums3);
        System.out.println("nums3: " + Arrays.toString(nums3));

        int[] nums4 = {0, 0, 1};
        solution.moveZeroes(nums4);
        System.out.println("nums4: " + Arrays.toString(nums4));
    }
}
