import java.util.*;

class Solution {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        int n = code.length;
        List<Integer> validIndices = new ArrayList<>();
        
        // Priority map for sorting order
        Map<String, Integer> priority = new HashMap<>();
        priority.put("electronics", 0);
        priority.put("grocery", 1);
        priority.put("pharmacy", 2);
        priority.put("restaurant", 3);

        // Step 1: Filter valid coupons
        for (int i = 0; i < n; i++) {
            // Check active status
            if (!isActive[i]) continue;
            
            // Check valid business line
            if (!priority.containsKey(businessLine[i])) continue;
            
            // Check code validity (alphanumeric + underscore, non-empty)
            if (isValidCode(code[i])) {
                validIndices.add(i);
            }
        }

        // Step 2: Sort valid indices based on the problem's criteria
        Collections.sort(validIndices, (a, b) -> {
            int p1 = priority.get(businessLine[a]);
            int p2 = priority.get(businessLine[b]);
            
            // Primary sort: Business Line Priority
            if (p1 != p2) {
                return Integer.compare(p1, p2);
            }
            // Secondary sort: Lexicographical order of code
            return code[a].compareTo(code[b]);
        });

        // Step 3: Extract result
        List<String> result = new ArrayList<>();
        for (int index : validIndices) {
            result.add(code[index]);
        }
        
        return result;
    }

    // Helper to validate code format
    private boolean isValidCode(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            // Allow a-z, A-Z, 0-9, and underscore '_'
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        return true;
    }
}



