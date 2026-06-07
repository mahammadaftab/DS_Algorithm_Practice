# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def createBinaryTree(self, descriptions: list[list[int]]) -> TreeNode | None:
        nodes = {}
        children = set()
        
        # 1. Build nodes and connect pointers
        for parent, child, is_left in descriptions:
            # Ensure parent node object exists
            if parent not in nodes:
                nodes[parent] = TreeNode(parent)
            # Ensure child node object exists
            if child not in nodes:
                nodes[child] = TreeNode(child)
                
            # Connect the relationship
            if is_left == 1:
                nodes[parent].left = nodes[child]
            else:
                nodes[parent].right = nodes[child]
                
            # Log that this node is a child
            children.add(child)
            
        # 2. Find the root node (the only node that is never a child)
        for parent in nodes:
            if parent not in children:
                return nodes[parent]
                
        return None

