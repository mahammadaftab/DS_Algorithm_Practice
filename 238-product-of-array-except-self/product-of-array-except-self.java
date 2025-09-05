class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        int[] leftProducts = new int[n];
        int[] rightProducts = new int[n];
        int[] result = new int[n];

        leftProducts[0] =1;
        for(int i=1; i<n; i++)
        {
            leftProducts[i] = leftProducts[i - 1] * nums[i - 1];
        }

        rightProducts[n - 1] =1;
        for(int i=n-2; i>=0; i--)
        {
            rightProducts[i] = rightProducts[i + 1] * nums[i + 1];
        }

        for(int i=0; i<n; i++)
        {
            result[i] = leftProducts[i] * rightProducts[i];
        }
        return result;
    }

    public int[] productExceptSelfOptimized(int[] nums)
    {
        int n = nums.length;
        int[] result = new int[n];

        result[0] = 1;
        for(int i=1; i<n; i++)
        {
            result[i] = result[i - 1] * nums[i - 1];
        }

        int rightProduct = 1;
        for(int i=n-1; i>=0; i--)
        {
            result[i]=result[i]*rightProduct;
            rightProduct *= nums[i];
        }
        return result;
    }

    public static void main(String[] args)
    {
        Solution sol = new Solution();
        int[] nums = {1, 2, 3, 4};
        int[] result = sol.productExceptSelf(nums);
        System.out.println(Arrays.toString(result));

        int[] nums2 = {-1, 1, 0, -3, 3};
        int[] result2 = sol.productExceptSelf(nums2);
        System.out.println(Arrays.toString(result2));
        
        int[] nums3 = {1, 2, 3, 4, 5};
        int[] result3 = sol.productExceptSelf(nums3);
        System.out.println(Arrays.toString(result3));
    }
}