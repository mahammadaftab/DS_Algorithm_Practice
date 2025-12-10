class Solution {
    public int countPermutations(int[] complexity) {
        long MOD = 1_000_000_007L;
        int n = complexity.length;

        // Step 1: Validate that Computer 0 can unlock everyone else
        // Any computer 'i' requires a strictly smaller complexity predecessor 'j' with j < i.
        // Since 0 is the only initial one, c[0] < c[i] is a hard requirement for all i.
        for (int i = 1; i < n; i++) {
            if (complexity[0] >= complexity[i]) {
                return 0;
            }
        }

        // Step 2: Calculate (n-1)! modulo 10^9 + 7
        // Since 0 satisfies the condition for everyone, the remaining n-1 computers
        // can be unlocked in any order.
        long factorial = 1;
        for (int i = 1; i < n; i++) {
            factorial = (factorial * i) % MOD;
        }

        return (int) factorial;
    }
}


