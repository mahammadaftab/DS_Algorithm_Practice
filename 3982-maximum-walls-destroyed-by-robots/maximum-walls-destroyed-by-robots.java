import java.util.Arrays;

class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = robots[i];
            arr[i][1] = distance[i];
        }
        
        // Sort arrays positionally
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(walls);
        
        int prevDp0 = 0;
        int prevDp1 = 0;
        
        for (int i = 0; i < n; i++) {
            int posI = arr[i][0];
            int distI = arr[i][1];
            
            // --- Option 0: Robot i fires left ---
            int L = posI - distI;
            if (i > 0) {
                L = Math.max(L, arr[i-1][0] + 1);
            }
            int countLeft = countWalls(walls, L, posI);
            int ans0Base = prevDp0 + countLeft;
            
            int newDp0 = 0, newDp1 = 0;
            
            // --- Option 1: Robot i fires right ---
            for (int j = 0; j < 2; j++) {
                int R = posI + distI;
                if (i + 1 < n) {
                    int posNext = arr[i+1][0];
                    int distNext = arr[i+1][1];
                    if (j == 0) {
                        R = Math.min(R, Math.max(posI, posNext - distNext - 1));
                    } else {
                        R = Math.min(R, posNext - 1);
                    }
                }
                
                int countRight = countWalls(walls, posI, R);
                int ans1 = prevDp1 + countRight;
                
                if (j == 0) newDp0 = Math.max(ans0Base, ans1);
                if (j == 1) newDp1 = Math.max(ans0Base, ans1);
            }
            
            prevDp0 = newDp0;
            prevDp1 = newDp1;
        }
        
        return prevDp0;
    }
    
    // Quick Binary Search interval counter
    private int countWalls(int[] walls, int A, int B) {
        if (A > B) return 0;
        return upperBound(walls, B) - lowerBound(walls, A);
    }
    
    private int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= target) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}