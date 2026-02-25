import java.util.Arrays;

class Solution {
    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        
        // 1. Encode both criteria into a single integer
        for (int i = 0; i < n; i++) {
            int bitCount = Integer.bitCount(arr[i]);
            // Multiply bit count by 10001 (strictly greater than max arr[i] of 10000)
            arr[i] += bitCount * 10001;
        }
        
        // 2. Sort the array normally. 
        // The bit count carries the most weight, and the original value breaks ties.
        Arrays.sort(arr);
        
        // 3. Decode the array back to the original values
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i] % 10001;
        }
        
        return arr;
    }
}