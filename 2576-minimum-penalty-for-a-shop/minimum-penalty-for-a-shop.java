class Solution {
    public int bestClosingTime(String customers) {
        // 'currentPenalty' tracks the relative change in penalty compared to closing at hour 0.
        int currentPenalty = 0;
        
        // 'minPenalty' tracks the lowest relative penalty encountered so far.
        int minPenalty = 0;
        
        // 'bestHour' stores the index of the best closing time found so far.
        // Default is 0 (closing immediately).
        int bestHour = 0;
        
        for (int i = 0; i < customers.length(); i++) {
            char type = customers.charAt(i);
            
            if (type == 'Y') {
                // If we stay open for 'Y', we reduce the penalty (compared to being closed)
                currentPenalty--;
            } else {
                // If we stay open for 'N', we increase the penalty
                currentPenalty++;
            }
            
            // If we found a new lowest penalty point, update the best hour.
            // Note: We perform this check AFTER processing index 'i', 
            // so this corresponds to closing at hour 'i + 1'.
            if (currentPenalty < minPenalty) {
                minPenalty = currentPenalty;
                bestHour = i + 1;
            }
        }
        
        return bestHour;
    }
}