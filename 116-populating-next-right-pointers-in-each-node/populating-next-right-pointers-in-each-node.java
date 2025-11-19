import java.util.LinkedList;
import java.util.Queue;

/*
// Definition for a Node.
*/
/*class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    /**
     * Populates the next right pointers in each node of a perfect binary tree.
     * 
     * @param root The root of the perfect binary tree.
     * @return The root of the modified tree with next pointers populated.
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Node prev = null;

            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();

                if (prev != null) {
                    prev.next = current;
                }

                prev = current;

                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            // After each level, the last node's next should be null (implicit in problem statement)
            //prev.next = null; This is not explicitly needed because the problem description states perfect binary tree, and the level ends with the last node having a next of null already.
        }

        return root;
    }


    /**
     * Alternative Solution (Constant Space - Optimal)
     */
     public Node connectConstantSpace(Node root) {
        if (root == null) return null;
        
        Node levelStart = root;
        
        while (levelStart != null) {
            Node current = levelStart;
            while (current != null) {
                if (current.left != null) {
                    current.left.next = current.right;
                }
                if (current.right != null && current.next != null) {
                    current.right.next = current.next.left;
                }
                
                current = current.next;
            }
            levelStart = levelStart.left;
        }
        
        return root;
    }


    public static void main(String[] args) {
        // Example Usage (Requires Node definition above)
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        Solution sol = new Solution();
        Node connectedRoot = sol.connect(root); // Or sol.connectConstantSpace(root) for constant space solution

        // Verification (Example)
        System.out.println("Root value: " + connectedRoot.val);
        System.out.println("Root.left.next value: " + connectedRoot.left.next.val); // Should be 3
        System.out.println("Root.left.left.next value: " + connectedRoot.left.left.next.val); // Should be 5
    }
}

