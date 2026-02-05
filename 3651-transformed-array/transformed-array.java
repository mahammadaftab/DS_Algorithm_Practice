class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            // Calculate the target index with circular wrapping
            // (i + nums[i]) % n handles the movement
            // + n ensures we handle negative indices correctly (Java's % operator retains sign)
            // The final % n wraps it back if it exceeds n
            int targetIndex = ((i + nums[i]) % n + n) % n;
            
            result[i] = nums[targetIndex];
        }
        
        return result;
    }
}
