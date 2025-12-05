class Solution {
    public int countPartitions(int[] nums) {
        // Step 1: Calculate the total sum of the array
        long totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        long leftSum = 0;
        int count = 0;

        // Step 2: Iterate through possible partition points.
        // We stop at nums.length - 2 because the right subarray must be non-empty.
        // (A partition at index 'i' puts nums[0]...nums[i] in the left part)
        for (int i = 0; i < nums.length - 1; i++) {
            leftSum += nums[i];
            long rightSum = totalSum - leftSum;

            // Step 3: Check if the difference is even
            // We use % 2 == 0 to handle both positive and negative differences
            if ((leftSum - rightSum) % 2 == 0) {
                count++;
            }
        }

        return count;
    }
}