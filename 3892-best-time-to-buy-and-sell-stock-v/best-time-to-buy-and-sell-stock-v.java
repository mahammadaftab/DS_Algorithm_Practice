import java.util.Arrays;

class Solution {
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        // dp[j][0] -> Max profit after j transactions, ending Neutral
        // dp[j][1] -> Max profit during j-th transaction, Holding Long
        // dp[j][2] -> Max profit during j-th transaction, Holding Short
        
        // Initialize with a very small number to represent unreachable states.
        // using Long.MIN_VALUE / 2 to prevent overflow during additions.
        long INF = Long.MIN_VALUE / 2;
        long[][] dp = new long[k + 1][3];
        
        for (long[] row : dp) {
            Arrays.fill(row, INF);
        }
        
        // Base case: 0 transactions done, state is Neutral, profit is 0.
        dp[0][0] = 0;
        
        for (int price : prices) {
            long[][] nextDP = new long[k + 1][3];
            
            // Initialize nextDP with current values (option to "Skip" the day)
            for (int j = 0; j <= k; j++) {
                nextDP[j] = dp[j].clone();
            }
            
            // Iterate through transaction counts (1 to k)
            for (int j = 1; j <= k; j++) {
                // --- OPENING POSITIONS (Start transaction j) ---
                // Depend on 'dp' (yesterday) to ensure we don't open same day we closed j-1.
                
                // 1. Start Long: From Neutral state of (j-1) transactions
                // Transition: Neutral(j-1) -> Long(j) | Cost: -price
                nextDP[j][1] = Math.max(nextDP[j][1], dp[j - 1][0] - price);
                
                // 2. Start Short: From Neutral state of (j-1) transactions
                // Transition: Neutral(j-1) -> Short(j) | Gain: +price (Short selling gives cash now)
                nextDP[j][2] = Math.max(nextDP[j][2], dp[j - 1][0] + price);
                
                // --- CLOSING POSITIONS (Finish transaction j) ---
                
                // 3. Close Long: From Long state of j transactions
                // Transition: Long(j) -> Neutral(j) | Gain: +price
                nextDP[j][0] = Math.max(nextDP[j][0], dp[j][1] + price);
                
                // 4. Close Short: From Short state of j transactions
                // Transition: Short(j) -> Neutral(j) | Cost: -price (Buying back costs money)
                nextDP[j][0] = Math.max(nextDP[j][0], dp[j][2] - price);
            }
            
            dp = nextDP;
        }
        
        // The answer is the max profit in the Neutral state after any number of transactions (0 to k).
        long maxProfit = 0;
        for (int j = 0; j <= k; j++) {
            maxProfit = Math.max(maxProfit, dp[j][0]);
        }
        
        return maxProfit;
    }
}







