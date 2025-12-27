import java.util.*;

class Solution {
    public int mostBooked(int n, int[][] meetings) {
        // Step 1: Sort meetings by start time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        // 'count' tracks how many times each room is used
        int[] count = new int[n];

        // 'freeRooms' stores indices of available rooms (Min-Heap by index)
        PriorityQueue<Integer> freeRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            freeRooms.offer(i);
        }

        // 'busyRooms' stores {endTime, roomIndex} (Min-Heap by endTime, then roomIndex)
        // We use 'long' for time to prevent overflow from accumulated delays
        PriorityQueue<long[]> busyRooms = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            return Long.compare(a[1], b[1]);
        });

        for (int[] meeting : meetings) {
            long start = meeting[0];
            long end = meeting[1];
            long duration = end - start;

            // Step 2: Release rooms that finish before the current meeting starts
            while (!busyRooms.isEmpty() && busyRooms.peek()[0] <= start) {
                freeRooms.offer((int) busyRooms.poll()[1]);
            }

            // Step 3: Allocate a room
            if (!freeRooms.isEmpty()) {
                // Case A: A room is currently free
                int room = freeRooms.poll();
                count[room]++;
                busyRooms.offer(new long[]{end, room});
            } else {
                // Case B: No room is free. Wait for the earliest one to finish.
                long[] busyRoom = busyRooms.poll();
                long currentEndTime = busyRoom[0];
                int room = (int) busyRoom[1];
                
                count[room]++;
                
                // The new meeting starts exactly when the room frees up
                long newEndTime = currentEndTime + duration;
                busyRooms.offer(new long[]{newEndTime, room});
            }
        }

        // Step 4: Find the room with maximum meetings (lowest index breaks ties)
        int maxMeetings = 0;
        int bestRoom = 0;
        
        for (int i = 0; i < n; i++) {
            if (count[i] > maxMeetings) {
                maxMeetings = count[i];
                bestRoom = i;
            }
        }

        return bestRoom;
    }
}






