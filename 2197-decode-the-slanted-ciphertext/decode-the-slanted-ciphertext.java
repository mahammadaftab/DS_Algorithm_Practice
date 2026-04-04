class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int n = encodedText.length();
        if (n == 0) {
            return "";
        }
        
        int cols = n / rows;
        StringBuilder sb = new StringBuilder();
        
        // Iterate over every starting column in the top row
        for (int startCol = 0; startCol < cols; startCol++) {
            // Walk down the diagonal
            for (int r = 0; r < rows; r++) {
                int c = startCol + r;
                
                // If the diagonal falls off the right side of the grid, stop
                if (c >= cols) {
                    break;
                }
                
                // Map the 2D coordinates back to the 1D string index
                sb.append(encodedText.charAt(r * cols + c));
            }
        }
        
        // Convert to string and remove trailing spaces
        return sb.toString().stripTrailing();
    }
}

