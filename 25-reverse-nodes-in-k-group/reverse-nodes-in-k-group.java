/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0); // Dummy node to handle the case when head needs to be updated
        dummy.next = head;
        ListNode pre = dummy; // Pointer to the node before the group to be reversed
        ListNode cur = head; // Pointer to the first node of the group to be reversed

        while (cur != null) {
            ListNode tail = pre; // Pointer to the last node of the group to be reversed before reversal
            
            // Check if there are at least k nodes to reverse
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return dummy.next; // Not enough nodes, return the original list
                }
            }
            
            ListNode nextGroupHead = tail.next; // The head of the next group or null if this is the last group
            
            // Reverse the k nodes
            ListNode[] reversed = reverseList(cur, tail);
            ListNode newHead = reversed[0]; // The new head of current reversed group
            ListNode newTail = reversed[1]; // The tail of current reversed group 

            // Connect the reversed group to the previous and next groups
            pre.next = newHead;
            newTail.next = nextGroupHead;
            
            // Move the pointers to the next group
            pre = newTail;
            cur = nextGroupHead;
        }

        return dummy.next;
    }

    // Helper function to reverse a portion of the linked list
    // This logic is correct for reversing from head to tail (inclusive)
    private ListNode[] reverseList(ListNode head, ListNode tail) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;

        while (prev != tail) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // After the loop:
        // prev is the new head (the old tail)
        // head is the new tail (the old head)
        return new ListNode[]{prev, head};
    }

}