import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        // 1. Sort the array to place closest elements together
        Arrays.sort(arr);
        
        List<List<Integer>> result = new ArrayList<>();
        int minDiff = Integer.MAX_VALUE;
        
        // 2. Single pass to find min diff and collect pairs
        for (int i = 0; i < arr.length - 1; i++) {
            int diff = arr[i+1] - arr[i];
            
            // Case 1: Found a new smaller difference
            if (diff < minDiff) {
                minDiff = diff;
                result.clear(); // Discard previous pairs, they weren't the true min
                
                List<Integer> pair = new ArrayList<>();
                pair.add(arr[i]);
                pair.add(arr[i+1]);
                result.add(pair);
            } 
            // Case 2: Found another pair with the same minimum difference
            else if (diff == minDiff) {
                List<Integer> pair = new ArrayList<>();
                pair.add(arr[i]);
                pair.add(arr[i+1]);
                result.add(pair);
            }
        }
        
        return result;
    }
}