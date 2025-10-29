/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // Create a dummy node to handle the case where the head needs to be removed.
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 'prev' points to the node before the current sequence of nodes.
        // 'curr' is the current node we are inspecting.
        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null) {
            // If the current node has duplicates...
            if (curr.next != null && curr.val == curr.next.val) {
                // Skip all duplicate nodes.
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }
                // Now, 'curr' points to the last duplicate node. Skip all these nodes by
                // connecting 'prev' to the node after the duplicates.
                prev.next = curr.next;
            } else {
                // If the current node is not a duplicate, move 'prev' to 'curr'.
                prev = curr;
            }
            // Move to the next node.
            curr = curr.next;
        }

        // Return the head of the modified list (which is dummy.next).
        return dummy.next;
    }
}