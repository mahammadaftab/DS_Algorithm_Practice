import java.util.HashMap;
import java.util.Map;

// Definition for a Node.
/*class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/




public class Solution {
    /**
     * LeetCode Problem 138: Copy List with Random Pointer
     *
     * This method creates a deep copy of a linked list with random pointers.
     * It uses a HashMap to store the mapping between original nodes and their copies.
     *
     * @param head The head of the original linked list.
     * @return The head of the copied linked list.
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // Use a HashMap to store the mapping between original nodes and their copies.
        Map<Node, Node> nodeMap = new HashMap<>();

        // First pass: Create new nodes and store them in the HashMap.
        Node current = head;
        while (current != null) {
            nodeMap.put(current, new Node(current.val));
            current = current.next;
        }

        // Second pass: Set the next and random pointers of the copied nodes.
        current = head;
        while (current != null) {
            Node copyNode = nodeMap.get(current);
            copyNode.next = nodeMap.get(current.next);
            copyNode.random = nodeMap.get(current.random);
            current = current.next;
        }

        // Return the head of the copied list.
        return nodeMap.get(head);
    }

    // Optional main method for testing
    public static void main(String[] args) {
        // Example usage:
        Node node1 = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;

        Solution solution = new Solution();
        Node copiedList = solution.copyRandomList(node1);

        // Print the copied list to verify
        Node current = copiedList;
        while (current != null) {
            System.out.println("Value: " + current.val);
            if (current.random != null) {
                System.out.println("Random: " + current.random.val);
            } else {
                System.out.println("Random: null");
            }
            current = current.next;
        }
    }
}