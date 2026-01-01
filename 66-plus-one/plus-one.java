class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        
        // Move from the last index to the first
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            
            // If digit is 9, it becomes 0
            digits[i] = 0;
        }
        
        // If we exit the loop, it means the number was all 9s (e.g., 999)
        // We need a new array with size n + 1 (e.g., 1000)
        int[] newNumber = new int[n + 1];
        newNumber[0] = 1;
        
        return newNumber;
    }
}







