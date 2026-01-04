class Solution {
    public int sumFourDivisors(int[] nums) {
        int totalSum = 0;

        for (int num : nums) {
            int count = 0;
            int sum = 0;
            
            // We only need to check up to the square root of num
            for (int i = 1; i * i <= num; i++) {
                if (num % i == 0) {
                    // i is a divisor
                    count++;
                    sum += i;
                    
                    // The pair (num / i) is also a divisor, unless i is the square root
                    if (i * i != num) {
                        count++;
                        sum += num / i;
                    }
                }
                // Optimization: If we already found more than 4 divisors, we can stop early
                if (count > 4) break;
            }
            
            // Only add to total if we found exactly 4 divisors
            if (count == 4) {
                totalSum += sum;
            }
        }
        
        return totalSum;
    }
}



