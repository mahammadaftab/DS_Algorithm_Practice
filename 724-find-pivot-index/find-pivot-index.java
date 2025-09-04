class Solution {
    public int pivotIndex(int[] nums) {
        int totalSum = Arrays.stream(nums).sum();
        int leftSum  = 0;
        
        for(int i=0; i<nums.length; i++)
        {
            if(leftSum == (totalSum - leftSum - nums[i]))
            {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }
    public static void main(String[] args)
    {
        Solution solution = new Solution();
        int[] nums1 = {1, 7, 3, 6, 5, 6};
        System.out.println("Pivot index for nums1:" + solution.pivotIndex(nums1));

        int[] nums2 = {1, 2, 3};
        System.out.println("Pivot index for nums2:" + solution.pivotIndex(nums2));

        int[] nums3 = {2, 1, -1};
        System.out.println("Pivot index for nums3:" + solution.pivotIndex(nums3));
    }
}