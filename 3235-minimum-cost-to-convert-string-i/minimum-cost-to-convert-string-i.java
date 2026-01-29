import java.util.Arrays;

class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        // 1. Initialize Distance Matrix
        // We use a simplified graph size of 26 for 'a'-'z'
        long[][] dist = new long[26][26];
        
        // Fill with a large value representing infinity
        // We use Long.MAX_VALUE / 2 to prevent overflow during addition
        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE / 2);
        }
        
        // Distance to self is always 0
        for (int i = 0; i < 26; i++) {
            dist[i][i] = 0;
        }
        
        // 2. Populate initial edges
        for (int i = 0; i < original.length; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            // If multiple edges exist, keep the one with minimum cost
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }
        
        // 3. Floyd-Warshall Algorithm (All-Pairs Shortest Path)
        for (int k = 0; k < 26; k++) {          // Intermediate node
            for (int i = 0; i < 26; i++) {      // Source node
                for (int j = 0; j < 26; j++) {  // Destination node
                    // If going through k is cheaper than current path i->j
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        // 4. Calculate Total Cost
        long totalCost = 0;
        int n = source.length();
        
        for (int i = 0; i < n; i++) {
            int u = source.charAt(i) - 'a';
            int v = target.charAt(i) - 'a';
            
            if (u == v) continue;
            
            if (dist[u][v] >= Long.MAX_VALUE / 2) {
                return -1; // Transformation impossible
            }
            
            totalCost += dist[u][v];
        }
        
        return totalCost;
    }
}
