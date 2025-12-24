import java.util.Arrays;

class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        // Step 1: Calculate the total number of apples
        int totalApples = 0;
        for (int a : apple) {
            totalApples += a;
        }
        
        // Step 2: Sort capacities in ascending order
        Arrays.sort(capacity);
        
        int boxesUsed = 0;
        int i = capacity.length - 1; // Start from the end (largest box)
        
        // Step 3: Greedily use the largest boxes until all apples are covered
        while (totalApples > 0) {
            totalApples -= capacity[i];
            boxesUsed++;
            i--;
        }
        
        return boxesUsed;
    }
}