import java.util.Stack;

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
}

class BSTIterator {

    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        pushAll(root);
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        pushAll(node.right);
        return node.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void pushAll(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(7,
                new TreeNode(3),
                new TreeNode(15, new TreeNode(9), new TreeNode(20)));

        BSTIterator iterator = new BSTIterator(root);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */