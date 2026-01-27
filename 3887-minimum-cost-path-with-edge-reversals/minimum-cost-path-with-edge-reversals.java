import java.util.*;

class Solution {
    public int minCost(int n, int[][] edges) {
        // Build the graph
        // node -> list of {neighbor, weight}
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            
            // Original direction: cost w
            graph.get(u).add(new int[]{v, w});
            // Reverse direction: cost 2 * w
            graph.get(v).add(new int[]{u, 2 * w});
        }
        
        // Dijkstra's Algorithm
        // PriorityQueue stores {node, cost}, sorted by cost
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[]{0, 0});
        
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];
            int d = curr[1];
            
            // Optimization: If we found a shorter way to u already, skip
            if (d > dist[u]) continue;
            
            // If we reached the target
            if (u == n - 1) return d;
            
            for (int[] neighbor : graph.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];
                
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }
        
        return -1; // Unreachable
    }
}