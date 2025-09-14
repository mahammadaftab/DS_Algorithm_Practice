import java.util.Arrays;

class Solution {
    /**
     * LeetCode Problem 42: Trapping Rain Water
     * 
     * Given n non-negative integers representing an elevation map where the width of each bar is 1,
     * compute how much water it can trap after raining.
     *
     * Example:
     * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * Output: 6
     * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
     * In this case, 6 units of rain water (blue section) are being trapped.
     
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // Calculate the maximum height to the left for each position
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }

        // Calculate the maximum height to the right for each position
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }

        int trappedWater = 0;
        // Calculate the trapped water at each position
        for (int i = 0; i < n; i++) {
            trappedWater += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return trappedWater;
    }

    //Alternative solution with O(1) Space complexity using Two Pointers
    public int trapTwoPointers(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int ans = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    ans += (leftMax - height[left]);
                }
                ++left;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    ans += (rightMax - height[right]);
                }
                --right;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example usage
        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int trappedWater1 = solution.trap(height1);
        System.out.println("Trapped water: " + trappedWater1); // Output: 6

        int[] height2 = {4, 2, 0, 3, 2, 5};
        int trappedWater2 = solution.trap(height2);
        System.out.println("Trapped water: " + trappedWater2); // Output: 9

        int[] height3 = {4,2,3};
        int trappedWater3 = solution.trap(height3);
        System.out.println("Trapped water: " + trappedWater3); // Output: 1

         int[] height4 = {5,4,1,2};
        int trappedWater4 = solution.trap(height4);
        System.out.println("Trapped water: " + trappedWater4); // Output: 1
    }
}