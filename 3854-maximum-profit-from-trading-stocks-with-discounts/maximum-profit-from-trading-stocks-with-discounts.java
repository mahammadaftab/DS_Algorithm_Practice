import java.util.*;

class Solution {
    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        // Build Adjacency List (Graph)
        // Adjusting 1-based index (input) to 0-based index (implementation)
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : hierarchy) {
            adj.get(edge[0] - 1).add(edge[1] - 1);
        }

        // Run Tree DP starting from Root (index 0)
        int[][] result = dfs(0, adj, present, future, budget);
        
        // Return max profit for the root with the given budget.
        // Root has no parent, so we use the "parent not bought" state (index 0).
        return result[0][budget];
    }

    // Returns int[2][budget+1]
    // res[0] = DP table if parent NOT bought
    // res[1] = DP table if parent BOUGHT
    private int[][] dfs(int u, List<List<Integer>> adj, int[] present, int[] future, int budget) {
        // Initialize base DP tables for children aggregation
        // dpChildren[0] -> aggregated results if u is NOT bought (children see parent=false)
        // dpChildren[1] -> aggregated results if u IS bought (children see parent=true)
        int[] dpChildrenNoBuy = new int[budget + 1];
        int[] dpChildrenBuy = new int[budget + 1];

        // 1. Aggregation Step (Merge children results)
        for (int v : adj.get(u)) {
            int[][] childRes = dfs(v, adj, present, future, budget);
            dpChildrenNoBuy = merge(dpChildrenNoBuy, childRes[0], budget);
            dpChildrenBuy = merge(dpChildrenBuy, childRes[1], budget);
        }

        // 2. Decision Step for Node u
        int[][] res = new int[2][budget + 1];

        // --- Calculate res[0] (Parent of u NOT bought) ---
        // Option A: Don't buy u. (Uses dpChildrenNoBuy)
        // Option B: Buy u at FULL PRICE. (Uses dpChildrenBuy)
        int costFull = present[u];
        int profitFull = future[u] - costFull;
        
        for (int w = 0; w <= budget; w++) {
            // Default: Don't buy u
            res[0][w] = dpChildrenNoBuy[w];
            // Try buying u (if budget allows)
            if (w >= costFull) {
                res[0][w] = Math.max(res[0][w], dpChildrenBuy[w - costFull] + profitFull);
            }
        }

        // --- Calculate res[1] (Parent of u BOUGHT) ---
        // Option A: Don't buy u. (Uses dpChildrenNoBuy) - same as above
        // Option B: Buy u at DISCOUNT PRICE. (Uses dpChildrenBuy)
        int costHalf = present[u] / 2;
        int profitHalf = future[u] - costHalf;

        for (int w = 0; w <= budget; w++) {
            // Default: Don't buy u
            res[1][w] = dpChildrenNoBuy[w];
            // Try buying u (if budget allows)
            if (w >= costHalf) {
                res[1][w] = Math.max(res[1][w], dpChildrenBuy[w - costHalf] + profitHalf);
            }
        }

        return res;
    }

    // Standard Knapsack Merge: combines two DP tables
    // newDP[w] = max(dp1[k] + dp2[w-k])
    private int[] merge(int[] dp1, int[] dp2, int budget) {
        int[] newDP = new int[budget + 1];
        // Since budget is small (<= 160), O(Budget^2) is efficient enough (~25k ops)
        for (int w = budget; w >= 0; w--) {
            for (int k = 0; k <= w; k++) {
                newDP[w] = Math.max(newDP[w], dp1[k] + dp2[w - k]);
            }
        }
        return newDP;
    }
}




