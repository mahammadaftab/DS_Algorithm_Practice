class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        // We need at least 4 elements to satisfy 0 < p < q < n-1
        if (n < 4) return false;
        
        int i = 0;
        
        // 1. Find the first peak (p)
        // Climb up while strictly increasing
        while (i < n - 1 && nums[i] < nums[i + 1]) {
            i++;
        }
        
        // p must be > 0 and < n-1 (actually p < q < n-1, so p < n-2 is implicit)
        if (i == 0 || i == n - 1) return false;
        
        // 2. Find the valley (q)
        // Climb down while strictly decreasing
        int j = i;
        while (j < n - 1 && nums[j] > nums[j + 1]) {
            j++;
        }
        
        // q must be > p and < n-1
        if (j == i || j == n - 1) return false;
        
        // 3. Verify the final ascent
        // Climb up while strictly increasing
        int k = j;
        while (k < n - 1 && nums[k] < nums[k + 1]) {
            k++;
        }
        
        // If we successfully reached the end, it is trionic
        return k == n - 1;
    }
}
