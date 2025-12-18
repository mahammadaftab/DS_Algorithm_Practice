class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long baseProfit = 0;

        // Step 1: Calculate the total profit from the original strategy
        for (int i = 0; i < n; i++) {
            baseProfit += (long) strategy[i] * prices[i];
        }

        // Initialize maxProfit with baseProfit (in case modification only reduces profit)
        long maxProfit = baseProfit;

        // Current window sums
        long currentOriginalWindowProfit = 0;
        long currentSecondHalfPrices = 0;

        // Step 2: Initialize the first window [0, k-1]
        for (int i = 0; i < k; i++) {
            // Track the profit the original strategy made in this window
            currentOriginalWindowProfit += (long) strategy[i] * prices[i];

            // Track the sum of prices in the second half of the window
            // The modified strategy sells (action 1) in the second half [k/2, k-1]
            if (i >= k / 2) {
                currentSecondHalfPrices += prices[i];
            }
        }

        // Calculate profit for the first window
        // Logic: Remove original profit of this window, add 0 (first half), add prices (second half)
        long currentModifiedProfit = baseProfit - currentOriginalWindowProfit + currentSecondHalfPrices;
        maxProfit = Math.max(maxProfit, currentModifiedProfit);

        // Step 3: Slide the window from index 1 to n - k
        for (int i = 1; i <= n - k; i++) {
            int leavingIndex = i - 1;
            int enteringIndex = i + k - 1;
            int midEnteringIndex = i + k / 2 - 1; // The index that just entered the second half
            int midLeavingIndex = i + k / 2 - 1;  // Wait, let's trace carefully.
            
            // --- Update Original Profit Sum ---
            // Remove the element that left the window
            currentOriginalWindowProfit -= (long) strategy[leavingIndex] * prices[leavingIndex];
            // Add the element that entered the window
            currentOriginalWindowProfit += (long) strategy[enteringIndex] * prices[enteringIndex];

            // --- Update Second Half Prices Sum ---
            // The range of the second half shifts from [i-1 + k/2, i-1 + k - 1] to [i + k/2, i + k - 1]
            // 1. Remove the element that was at the start of the OLD second half 
            //    (It is now the end of the NEW first half, so it shouldn't be in this sum anymore)
            //    The old second half started at: (i - 1) + k / 2
            currentSecondHalfPrices -= prices[(i - 1) + k / 2];
            
            // 2. Add the element that just entered the window (it's at the end of the new second half)
            currentSecondHalfPrices += prices[enteringIndex];

            // Calculate new total profit
            currentModifiedProfit = baseProfit - currentOriginalWindowProfit + currentSecondHalfPrices;
            maxProfit = Math.max(maxProfit, currentModifiedProfit);
        }

        return maxProfit;
    }
}

