import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public String makeLargestSpecial(String s) {
        int count = 0;
        int start = 0;
        List<String> substrings = new ArrayList<>();
        
        // 1. Decompose the string into valid components
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                count++;
            } else {
                count--;
            }
            
            // When count reaches 0, we found an irreducible special substring
            if (count == 0) {
                // 2. Recurse on the inner part (excluding the outer '1' and '0')
                String inner = s.substring(start + 1, i);
                String maximizedInner = makeLargestSpecial(inner);
                
                // Wrap it back up and add to our list
                substrings.add("1" + maximizedInner + "0");
                
                // Move the start pointer for the next component
                start = i + 1;
            }
        }
        
        // 3. Sort the components in descending order
        Collections.sort(substrings, Collections.reverseOrder());
        
        // 4. Combine them back together
        return String.join("", substrings);
    }
}


