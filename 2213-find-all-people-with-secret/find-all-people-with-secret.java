import java.util.*;

class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // Step 1: Sort meetings by time (ascending)
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[2], b[2]));
        
        // Boolean array to track who knows the secret
        boolean[] known = new boolean[n];
        known[0] = true;
        known[firstPerson] = true;
        
        int m = meetings.length;
        int i = 0;
        
        // Step 2: Process meetings in batches (grouped by time)
        while (i < m) {
            int currentTime = meetings[i][2];
            int j = i;
            
            // Find the range [i, j) of meetings happening at 'currentTime'
            while (j < m && meetings[j][2] == currentTime) {
                j++;
            }
            
            // Step 3: Build the graph for this specific time slice
            Map<Integer, List<Integer>> graph = new HashMap<>();
            Set<Integer> peopleInSlice = new HashSet<>();
            
            for (int k = i; k < j; k++) {
                int p1 = meetings[k][0];
                int p2 = meetings[k][1];
                
                graph.computeIfAbsent(p1, x -> new ArrayList<>()).add(p2);
                graph.computeIfAbsent(p2, x -> new ArrayList<>()).add(p1);
                peopleInSlice.add(p1);
                peopleInSlice.add(p2);
            }
            
            // Step 4: BFS to propagate the secret within this group
            Queue<Integer> queue = new LinkedList<>();
            for (int person : peopleInSlice) {
                if (known[person]) {
                    queue.offer(person);
                }
            }
            
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                
                if (!graph.containsKey(curr)) continue;
                
                for (int neighbor : graph.get(curr)) {
                    if (!known[neighbor]) {
                        known[neighbor] = true; // Mark as known immediately
                        queue.offer(neighbor);
                    }
                }
            }
            
            // Move pointer to the next time group
            i = j;
        }
        
        // Step 5: Collect results
        List<Integer> result = new ArrayList<>();
        for (int k = 0; k < n; k++) {
            if (known[k]) {
                result.add(k);
            }
        }
        
        return result;
    }
}









