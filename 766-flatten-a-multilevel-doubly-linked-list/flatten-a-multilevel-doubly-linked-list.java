/*
 * Definition for a Node.
 * class Node {
 *     public int val;
 *     public Node prev;
 *     public Node next;
 *     public Node child;
 * };
 */

class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }

        Node current = head;

        while (current != null) {
            // If current node doesn't have a child, continue
            if (current.child == null) {
                current = current.next;
                continue;
            }

            // If current node has a child
            Node childHead = current.child;

            // Find the tail of the child list
            Node childTail = childHead;
            while (childTail.next != null) {
                childTail = childTail.next;
            }

            // Connect the child list to the main list
            Node nextNode = current.next;  // Store the next node to avoid losing it

            current.next = childHead;
            childHead.prev = current;

            // Connect the tail of the child list to the next node in the main list
            childTail.next = nextNode;
            if (nextNode != null) {
                nextNode.prev = childTail;
            }

            // Remove the child pointer from the current node
            current.child = null;

            // Move to the next node in the flattened list
            current = current.next;
        }

        return head;
    }
}

// Definition for a Node.
/*class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node prev, Node next, Node child) {
        this.val = val;
        this.prev = prev;
        this.next = next;
        this.child = child;
    }
};
*/