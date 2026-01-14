import java.util.*;

class Solution {
    public double separateSquares(int[][] squares) {
        // 1. Collect all unique X coordinates for compression
        Set<Integer> xCoords = new TreeSet<>();
        for (int[] sq : squares) {
            xCoords.add(sq[0]);
            xCoords.add(sq[0] + sq[2]);
        }
        
        List<Integer> uniqueX = new ArrayList<>(xCoords);
        Map<Integer, Integer> xMap = new HashMap<>();
        for (int i = 0; i < uniqueX.size(); i++) {
            xMap.put(uniqueX.get(i), i);
        }
        
        // 2. Create Events: [y, type, x_start, x_end]
        // type: 1 for add, -1 for remove
        List<Event> events = new ArrayList<>();
        for (int[] sq : squares) {
            events.add(new Event(sq[1], 1, sq[0], sq[0] + sq[2]));
            events.add(new Event(sq[1] + sq[2], -1, sq[0], sq[0] + sq[2]));
        }
        
        // Sort by Y
        events.sort((a, b) -> Integer.compare(a.y, b.y));
        
        // 3. Sweep Line
        SegmentTree st = new SegmentTree(uniqueX);
        double totalArea = 0;
        
        // We store strips to avoid re-calculating during the search phase
        // List of [y_bottom, y_top, width_of_union]
        List<double[]> strips = new ArrayList<>();
        
        for (int i = 0; i < events.size(); i++) {
            Event e = events.get(i);
            
            // If we have moved up from the previous y, calculate the area of the strip we just passed
            if (i > 0) {
                int prevY = events.get(i - 1).y;
                int currY = e.y;
                if (currY > prevY) {
                    double width = st.query();
                    double stripHeight = currY - prevY;
                    double stripArea = width * stripHeight;
                    
                    totalArea += stripArea;
                    strips.add(new double[]{prevY, currY, width});
                }
            }
            
            // Update the active squares in the segment tree
            st.update(0, 0, uniqueX.size() - 1, xMap.get(e.x1), xMap.get(e.x2), e.type);
        }
        
        // 4. Find the split line
        double target = totalArea / 2.0;
        double currentSum = 0;
        
        for (double[] strip : strips) {
            double bottom = strip[0];
            double top = strip[1];
            double width = strip[2];
            double area = width * (top - bottom);
            
            if (currentSum + area >= target) {
                // The split line is inside this strip
                double needed = target - currentSum;
                return bottom + (needed / width);
            }
            currentSum += area;
        }
        
        return events.get(events.size() - 1).y;
    }
    
    static class Event {
        int y, type, x1, x2;
        Event(int y, int type, int x1, int x2) {
            this.y = y; this.type = type; this.x1 = x1; this.x2 = x2;
        }
    }
    
    static class SegmentTree {
        int[] count; // How many squares cover this node
        double[] len; // The length of the union of squares in this node
        List<Integer> xCoords;
        
        public SegmentTree(List<Integer> xCoords) {
            this.xCoords = xCoords;
            int n = xCoords.size();
            count = new int[4 * n];
            len = new double[4 * n];
        }
        
        // Range update
        public void update(int node, int start, int end, int l, int r, int val) {
            if (l >= end || r <= start) return; // Out of range
            
            if (l <= start && end <= r) {
                count[node] += val;
            } else {
                int mid = (start + end) / 2;
                update(node * 2 + 1, start, mid, l, r, val);
                update(node * 2 + 2, mid, end, l, r, val);
            }
            
            // Update length for this node
            if (count[node] > 0) {
                // If this node is covered by at least one square, its length is the full width
                len[node] = xCoords.get(end) - xCoords.get(start);
            } else if (end - start == 1) {
                // Leaf node with count 0
                len[node] = 0;
            } else {
                // Internal node with count 0: sum of children
                len[node] = len[node * 2 + 1] + len[node * 2 + 2];
            }
        }
        
        public double query() {
            return len[0];
        }
    }
}