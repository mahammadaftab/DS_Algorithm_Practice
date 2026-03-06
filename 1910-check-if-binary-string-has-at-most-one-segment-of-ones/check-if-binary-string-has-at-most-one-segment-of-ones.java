class Solution {
    public boolean checkOnesSegment(String s) {
        // If "01" exists, there is more than one segment of 1s.
        return !s.contains("01");
    }
}

