import java.util.*;

class Solution {
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        // 1. Map unique strings to integer IDs
        Map<String, Integer> strToId = new HashMap<>();
        int idCounter = 0;
        
        for (String s : original) {
            if (!strToId.containsKey(s)) strToId.put(s, idCounter++);
        }
        for (String s : changed) {
            if (!strToId.containsKey(s)) strToId.put(s, idCounter++);
        }
        
        int V = strToId.size();
        
        // 2. Initialize Distance Matrix
        long[][] dist = new long[V][V];
        for (long[] row : dist) Arrays.fill(row, Long.MAX_VALUE / 2);
        for (int i = 0; i < V; i++) dist[i][i] = 0;
        
        // Populate initial edges
        for (int i = 0; i < original.length; i++) {
            int u = strToId.get(original[i]);
            int v = strToId.get(changed[i]);
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }
        
        // 3. Floyd-Warshall (All-Pairs Shortest Path)
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                if (dist[i][k] >= Long.MAX_VALUE / 2) continue; // Optimization
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        // 4. Precompute valid lengths to optimize DP
        Set<Integer> lengths = new HashSet<>();
        for (String s : original) lengths.add(s.length());
        
        // 5. Dynamic Programming
        int n = source.length();
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MAX_VALUE / 2);
        dp[n] = 0; // Base case: cost to convert empty string is 0
        
        for (int i = n - 1; i >= 0; i--) {
            // Option 1: Characters are equal, match one character (cost 0)
            if (source.charAt(i) == target.charAt(i)) {
                dp[i] = dp[i + 1];
            }
            
            // Option 2: Try converting substrings starting at i
            for (int len : lengths) {
                if (i + len > n) continue;
                
                String subSrc = source.substring(i, i + len);
                String subTgt = target.substring(i, i + len);
                
                // Only proceed if both substrings are "known" in our graph
                Integer u = strToId.get(subSrc);
                Integer v = strToId.get(subTgt);
                
                if (u != null && v != null) {
                    if (dist[u][v] < Long.MAX_VALUE / 2) {
                        dp[i] = Math.min(dp[i], dist[u][v] + dp[i + len]);
                    }
                }
            }
        }
        
        return dp[0] >= Long.MAX_VALUE / 2 ? -1 : dp[0];
    }
}




