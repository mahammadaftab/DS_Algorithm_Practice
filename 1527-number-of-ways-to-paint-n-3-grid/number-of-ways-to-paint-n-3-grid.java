class Solution {
    public int numOfWays(int n) {
        long mod = 1_000_000_007;
        
        // Base case for n = 1
        long abc = 6;
        long aba = 6;
        
        for (int i = 1; i < n; i++) {
            // Calculate next counts based on recurrence relation
            long nextAbc = (2 * abc + 2 * aba) % mod;
            long nextAba = (2 * abc + 3 * aba) % mod;
            
            // Update states
            abc = nextAbc;
            aba = nextAba;
        }
        
        return (int) ((abc + aba) % mod);
    }
}



