class Solution {
    public int subarraySum(int[] nums, int K) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 1);

        for(int num : nums)
        {
            sum +=num;
            if(sumMap.containsKey(sum - K))
            {
                count += sumMap.get(sum - K);
            }
            sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        int[] nums1 = {1, 1, 1};
        int K1 = 2;
        int result1 = solution.subarraySum(nums1, K1);
        System.out.println("Test Case 1:" + result1);

        int[] nums2 = {1, 2, 3};
        int K2 = 3;
        int result2 = solution.subarraySum(nums2, K2);
        System.out.println("Test Case 2:" + result2);

        int[] nums3 = {1, -1, 0};
        int K3 = 0;
        int result3 = solution.subarraySum(nums3, K3);
        System.out.println("Test Case 3:" + result3);
    }
}