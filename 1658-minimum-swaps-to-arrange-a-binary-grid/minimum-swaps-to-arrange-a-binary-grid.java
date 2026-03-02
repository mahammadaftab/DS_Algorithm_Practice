class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] zeros = new int[n];
        
        // 1. Count trailing zeros for each row
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    count++;
                } else {
                    break;
                }
            }
            zeros[i] = count;
        }
        
        int swaps = 0;
        
        // 2. Greedy search for the right row
        for (int i = 0; i < n; i++) {
            int target = n - 1 - i;
            int j = i;
            
            // Find the closest row that has enough trailing zeros
            while (j < n && zeros[j] < target) {
                j++;
            }
            
            // If we couldn't find any valid row, it's impossible
            if (j == n) {
                return -1;
            }
            
            // 3. Bubble the found row up to position i
            while (j > i) {
                // Swap adjacent elements in our tracking array
                int temp = zeros[j];
                zeros[j] = zeros[j - 1];
                zeros[j - 1] = temp;
                
                swaps++;
                j--;
            }
        }
        
        return swaps;
    }
}


