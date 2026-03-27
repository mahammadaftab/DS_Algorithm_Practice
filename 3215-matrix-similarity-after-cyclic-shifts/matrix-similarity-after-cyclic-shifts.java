class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        
        // A shift of length n brings us back to the start, so we simplify k
        k = k % n; 
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                if (i % 2 == 0) {
                    // Even rows: Left shift check
                    if (mat[i][j] != mat[i][(j + k) % n]) {
                        return false;
                    }
                } else {
                    // Odd rows: Right shift check
                    // We add 'n' before taking modulo to avoid negative numbers in Java
                    if (mat[i][j] != mat[i][(j - k + n) % n]) {
                        return false;
                    }
                }
                
            }
        }
        
        return true;
    }
}