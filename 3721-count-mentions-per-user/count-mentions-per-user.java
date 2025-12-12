import java.util.*;

class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        // Result array to store mention counts per user
        int[] mentions = new int[numberOfUsers];
        
        // Tracks the time when a user comes back online.
        // If onlineTime[i] <= currentTimestamp, the user is currently ONLINE.
        // Initialized to 0, so everyone starts online.
        int[] onlineTime = new int[numberOfUsers]; 

        // Step 1: Sort events chronologically
        events.sort((a, b) -> {
            int timeA = Integer.parseInt(a.get(1));
            int timeB = Integer.parseInt(b.get(1));
            
            // Primary sort by time
            if (timeA != timeB) {
                return Integer.compare(timeA, timeB);
            }
            
            // Secondary sort: OFFLINE status updates must happen BEFORE Messages
            // to ensure 'HERE' mentions are calculated correctly for that timestamp.
            String typeA = a.get(0);
            // If 'a' is OFFLINE, it comes first (-1). Otherwise 'a' is MESSAGE (1).
            return typeA.equals("OFFLINE") ? -1 : 1;
        });

        // Step 2: Process sorted events
        for (List<String> event : events) {
            String type = event.get(0);
            int timestamp = Integer.parseInt(event.get(1));
            String data = event.get(2);

            if (type.equals("OFFLINE")) {
                int userId = Integer.parseInt(data);
                // User goes offline at 'timestamp' for 60 units.
                // They will be back online at timestamp + 60.
                onlineTime[userId] = timestamp + 60;
                
            } else if (type.equals("MESSAGE")) {
                if (data.equals("ALL")) {
                    // Mention EVERYONE regardless of status
                    for (int i = 0; i < numberOfUsers; i++) {
                        mentions[i]++;
                    }
                } else if (data.equals("HERE")) {
                    // Mention only ONLINE users
                    for (int i = 0; i < numberOfUsers; i++) {
                        // Check if user is currently online
                        if (onlineTime[i] <= timestamp) {
                            mentions[i]++;
                        }
                    }
                } else {
                    // Specific user mentions: "id0 id1 id5..."
                    String[] ids = data.split(" ");
                    for (String idStr : ids) {
                        // parse "id123" -> 123
                        int id = Integer.parseInt(idStr.substring(2));
                        mentions[id]++;
                    }
                }
            }
        }

        return mentions;
    }
}