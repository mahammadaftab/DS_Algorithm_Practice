import java.util.*;

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        // Use a HashMap to store the sorted string (key) and the list of anagrams (value).
        Map<String, List<String>> anagramGroups = new HashMap<>();

        for (String str : strs) {
            // Sort the characters in the string to create a unique key for anagrams.
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = new String(charArray);

            // If the sorted string is already in the map, add the current string to the list of anagrams.
            if (anagramGroups.containsKey(sortedStr)) {
                anagramGroups.get(sortedStr).add(str);
            } else {
                // Otherwise, create a new list with the current string and put it in the map.
                List<String> anagrams = new ArrayList<>();
                anagrams.add(str);
                anagramGroups.put(sortedStr, anagrams);
            }
        }

        // Return the list of lists of anagrams.
        return new ArrayList<>(anagramGroups.values());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example usage:
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result1 = solution.groupAnagrams(strs1);
        System.out.println("Example 1: " + result1);

        String[] strs2 = {""};
        List<List<String>> result2 = solution.groupAnagrams(strs2);
        System.out.println("Example 2: " + result2);

        String[] strs3 = {"a"};
        List<List<String>> result3 = solution.groupAnagrams(strs3);
        System.out.println("Example 3: " + result3);
    }
}
