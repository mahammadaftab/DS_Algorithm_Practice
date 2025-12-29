import java.util.*;

class Solution {
    // Map to store allowed transitions.
    // Key: "AB" (the base pair), Value: List of allowed top blocks ['C', 'D']
    Map<String, List<Character>> transitions = new HashMap<>();
    
    // Memoization map to store results for specific row configurations.
    // Key: "XYZ" (a constructed row), Value: Boolean (result)
    // We only strictly need to store failures, but map handles both.
    Map<String, Boolean> memo = new HashMap<>();

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        // Step 1: Preprocess allowed transitions into a map for O(1) lookup
        for (String s : allowed) {
            String base = s.substring(0, 2);
            char top = s.charAt(2);
            transitions.computeIfAbsent(base, k -> new ArrayList<>()).add(top);
        }
        
        // Step 2: Start DFS
        return dfs(bottom, new StringBuilder());
    }

    // Recursive function to build the pyramid
    private boolean dfs(String currentRow, StringBuilder nextRow) {
        // Base Case 1: We reached the top (row length 1)
        if (currentRow.length() == 1) {
            return true;
        }

        // Base Case 2: We finished building the next row.
        if (nextRow.length() == currentRow.length() - 1) {
            String nextRowString = nextRow.toString();
            
            // Check memoization: if we already tried this row and it failed, return false
            if (memo.containsKey(nextRowString)) {
                return false;
            }
            
            // Recurse deeper with the new row as the base
            if (dfs(nextRowString, new StringBuilder())) {
                return true;
            }
            
            // If failed, mark in memo so we don't try this row pattern again
            memo.put(nextRowString, false);
            return false;
        }

        // Recursive Step: Try to place a block at the next position
        int k = nextRow.length();
        String base = currentRow.substring(k, k + 2);

        // If blocks are allowed on this base
        if (transitions.containsKey(base)) {
            for (char block : transitions.get(base)) {
                nextRow.append(block); // Choose
                
                if (dfs(currentRow, nextRow)) { // Explore
                    return true;
                }
                
                nextRow.deleteCharAt(nextRow.length() - 1); // Backtrack
            }
        }

        return false;
    }
}





