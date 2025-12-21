class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        
        // sorted[i] will be true if rows i and i+1 are already strictly sorted 
        // by previous columns (e.g., "abc" vs "acc" -> sorted at index 1).
        boolean[] sorted = new boolean[n - 1];
        int deleteCount = 0;

        // Iterate through each column
        for (int j = 0; j < m; j++) {
            boolean mustDelete = false;
            
            // Check if this column causes a conflict
            for (int i = 0; i < n - 1; i++) {
                // If rows i and i+1 are NOT already sorted, we need to check this column.
                if (!sorted[i]) {
                    // If current char is greater than next, the order is broken.
                    if (strs[i].charAt(j) > strs[i + 1].charAt(j)) {
                        mustDelete = true;
                        break;
                    }
                }
            }

            if (mustDelete) {
                // We cannot keep this column. Delete it and move to next.
                // Note: We do NOT update the 'sorted' array because this column is ignored.
                deleteCount++;
            } else {
                // We can keep this column. Now update the 'sorted' status for rows.
                for (int i = 0; i < n - 1; i++) {
                    // If this column strictly sorts rows i and i+1, mark them as sorted.
                    if (strs[i].charAt(j) < strs[i + 1].charAt(j)) {
                        sorted[i] = true;
                    }
                }
            }
        }

        return deleteCount;
    }
}








