import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator; // Imported for the alternative solution

public class Solution {

    /**
     * 71. Simplify Path
     * ... (docstring) ...
     */
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        String[] components = path.split("/");

        for (String component : components) {
            if (component.equals(".") || component.isEmpty()) {
                // Ignore current directory '.' and empty strings from multiple slashes '//'
                continue;
            } else if (component.equals("..")) {
                // If we see '..', pop from the stack if it's not empty
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                // Otherwise, it's a directory name, push it onto the stack
                stack.push(component);
            }
        }

        // If the stack is empty, the path is just the root directory
        if (stack.isEmpty()) {
            return "/";
        }

        // Build the result string from the stack
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            // Prepend each component to the result.
            // pop() removes from the front (head) of the Deque, which is correct
            // because push() adds to the front.
            result.insert(0, "/" + stack.pop());
        }

        return result.toString();
    }
    
    // An alternative, more idiomatic implementation
    public String simplifyPathAlternative(String path) {
        Deque<String> stack = new ArrayDeque<>();
        String[] components = path.split("/");

        for (String component : components) {
            if (component.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (!component.isEmpty() && !component.equals(".")) {
                stack.addLast(component);
            }
        }

        return "/" + String.join("/", stack);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        System.out.println("--- Original Corrected Method ---");
        System.out.println(solution.simplifyPath("/home/")); // Expected: /home
        System.out.println(solution.simplifyPath("/../")); // Expected: /
        System.out.println(solution.simplifyPath("/home//foo/")); // Expected: /home/foo
        System.out.println(solution.simplifyPath("/a/./b/../../c/")); // Expected: /c
        System.out.println(solution.simplifyPath("/a/../../b/../c//.//")); // Expected: /c
        System.out.println(solution.simplifyPath("/...")); // Expected: /...
        System.out.println(solution.simplifyPath("/.hidden")); //Expected: /.hidden

        System.out.println("\n--- Alternative Method ---");
        System.out.println(solution.simplifyPathAlternative("/home/")); // Expected: /home
        System.out.println(solution.simplifyPathAlternative("/a/./b/../../c/")); // Expected: /c
    }
}








