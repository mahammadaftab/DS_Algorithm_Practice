class Solution {
    public int minDeletionSize(String[] strs) {
        int rows = strs.length;
        int cols = strs[0].length();
        int deleteCount = 0;

        // Iterate through each column (j is column index)
        for (int j = 0; j < cols; j++) {
            // Iterate through each row for the current column (i is row index)
            for (int i = 0; i < rows - 1; i++) {
                // Check if the current char is greater than the char below it
                // charAt(j) accesses the character at column j
                if (strs[i].charAt(j) > strs[i + 1].charAt(j)) {
                    deleteCount++;
                    break; // This column is bad, no need to check further rows
                }
            }
        }

        return deleteCount;
    }
}







