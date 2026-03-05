class Solution {
    public int minOperations(String s) {
        int count = 0;
        int n = s.length();
        
        // We will compare against Pattern A: "010101..."
        // At even indices (0, 2, 4), we expect '0'.
        // At odd indices (1, 3, 5), we expect '1'.
        for (int i = 0; i < n; i++) {
            char expected = (i % 2 == 0) ? '0' : '1';
            
            if (s.charAt(i) != expected) {
                count++;
            }
        }
        
        // The operations for the other pattern is simply (total length - count)
        return Math.min(count, n - count);
    }
}
