class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        
        // Track the validity of the 4 possible rotations: 0, 90, 180, 270 degrees
        boolean[] validRotations = {true, true, true, true};
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                
                // If a mismatch is found, that specific rotation is permanently invalidated
                if (mat[i][j] != target[i][j]) {
                    validRotations[0] = false; 
                }
                if (mat[n - 1 - j][i] != target[i][j]) {
                    validRotations[1] = false; 
                }
                if (mat[n - 1 - i][n - 1 - j] != target[i][j]) {
                    validRotations[2] = false; 
                }
                if (mat[j][n - 1 - i] != target[i][j]) {
                    validRotations[3] = false; 
                }
            }
        }
        
        // If any of the rotations survived the check, it's a match
        return validRotations[0] || validRotations[1] || validRotations[2] || validRotations[3];
    }
}

