class Solution {
    public String findDifferentBinaryString(String[] nums) {
        StringBuilder ans = new StringBuilder();
        
        for (int i = 0; i < nums.length; i++) {
            // Get the diagonal character
            char diagonalChar = nums[i].charAt(i);
            
            // Append the flipped character to our answer
            if (diagonalChar == '0') {
                ans.append('1');
            } else {
                ans.append('0');
            }
        }
        
        return ans.toString();
    }
}

