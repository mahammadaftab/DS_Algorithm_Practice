import java.util.*;

class Solution {
    public int maxTwoEvents(int[][] events) {
        // Step 1: Sort events by Start Time.
        // This allows us to process the timeline linearly.
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 2: Min-Heap to store {endTime, value}.
        // Ordered by endTime so we can efficiently find events that finish before the current one starts.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        int maxVal = 0; // Tracks the max value of any single event that has completely finished
        int result = 0;

        for (int[] event : events) {
            int start = event[0];
            int end = event[1];
            int value = event[2];

            // Step 3: Pop all events from the heap that end BEFORE the current event starts.
            // These are the valid candidates for the "first" event in the pair.
            while (!pq.isEmpty() && pq.peek()[0] < start) {
                int[] completedEvent = pq.poll();
                maxVal = Math.max(maxVal, completedEvent[1]);
            }

            // Step 4: Calculate the best score if we include the current event.
            // It is the current value + the best value found among disjoint previous events.
            result = Math.max(result, value + maxVal);

            // Step 5: Add the current event to the heap to be considered for future events.
            pq.offer(new int[]{end, value});
        }

        return result;
    }
}









