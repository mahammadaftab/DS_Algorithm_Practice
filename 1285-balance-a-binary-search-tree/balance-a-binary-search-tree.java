import java.util.ArrayList;
import java.util.List;

class Solution {
    List<TreeNode> sortedNodes = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        // Step 1: Flatten the tree into a sorted list using in-order traversal
        inorderTraversal(root);
        
        // Step 2: Reconstruct a balanced tree from the sorted list
        return buildBalancedBST(0, sortedNodes.size() - 1);
    }

    private void inorderTraversal(TreeNode node) {
        if (node == null) return;
        inorderTraversal(node.left);
        sortedNodes.add(node);
        inorderTraversal(node.right);
    }

    private TreeNode buildBalancedBST(int start, int end) {
        if (start > end) return null;

        // Pick the middle element to ensure balance
        int mid = start + (end - start) / 2;
        TreeNode node = sortedNodes.get(mid);

        // Recursively build left and right subtrees
        node.left = buildBalancedBST(start, mid - 1);
        node.right = buildBalancedBST(mid + 1, end);

        return node;
    }
}
