class Solution {
    public int numSteps(String s) {
        int steps = 0;
        int carry = 0;
        
        // Traverse from right to left, stopping before the first bit
        for (int i = s.length() - 1; i > 0; i--) {
            int bit = s.charAt(i) - '0';
            
            if (bit + carry == 1) {
                // Odd number: takes 2 steps (add 1, then divide by 2)
                steps += 2;
                carry = 1; // Adding 1 generates a carry
            } else {
                // Even number (bit+carry == 0 or 2): takes 1 step (divide by 2)
                steps += 1;
                // carry remains unchanged (if it was 0, it stays 0; if 1, it stays 1)
            }
        }
        
        // If there's a leftover carry, we need 1 final step to reduce '10' to '1'
        return steps + carry;
    }
}

