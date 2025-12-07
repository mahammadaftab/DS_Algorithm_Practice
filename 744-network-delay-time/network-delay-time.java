import java.util.*;

class Solution {
    /**
     * LeetCode Problem 743: Network Delay Time
     *
     * You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi)
     * representing the time taken for a signal to travel from node ui to node vi. Return the minimum time it takes for all nodes to receive the signal.
     * If it is impossible for all n nodes to receive the signal, return -1.
     *
     * Example:
     * Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
     * Output: 2
     *
     * @param times A list of travel times as directed edges. Each edge is represented as an array [u, v, w] where u is the source node, v is the destination node, and w is the travel time.
     * @param n The number of nodes in the network.
     * @param k The starting node for the signal.
     * @return The minimum time it takes for all nodes to receive the signal, or -1 if it is impossible.
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // Build the graph as an adjacency list.
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            int source = time[0];
            int target = time[1];
            int weight = time[2];
            graph.computeIfAbsent(source, key -> new ArrayList<>()).add(new int[]{target, weight});
        }

        // Initialize the distance array with infinity for all nodes except the starting node.
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        // Use Dijkstra's algorithm to find the shortest path from the starting node to all other nodes.
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // {distance, node}
        pq.offer(new int[]{0, k});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int d = current[0]; //distance from start node
            int node = current[1];

            if (d > dist[node]) {
                continue; // Skip if we've already found a shorter path to this node
            }

            if (graph.containsKey(node)) {
                for (int[] edge : graph.get(node)) {
                    int neighbor = edge[0];
                    int weight = edge[1];
                    if (dist[neighbor] > dist[node] + weight) {
                        dist[neighbor] = dist[node] + weight;
                        pq.offer(new int[]{dist[neighbor], neighbor});
                    }
                }
            }
        }

        // Find the maximum distance from the starting node to any other node.
        int maxDelay = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1; // If any node is unreachable, return -1.
            }
            maxDelay = Math.max(maxDelay, dist[i]);
        }

        return maxDelay;
    }

    // Example usage (optional, for testing)
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n = 4;
        int k = 2;
        int result = sol.networkDelayTime(times, n, k);
        System.out.println("Network Delay Time: " + result); // Output: 2
    }
}
