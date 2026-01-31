class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int low = 0;
        int high = letters.length;

        // Standard Binary Search for Upper Bound
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            if (letters[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        // If low == letters.length, it wraps around to 0
        return letters[low % letters.length];
    }
}


