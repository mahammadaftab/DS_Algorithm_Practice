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
    public ListNode partition(ListNode head, int x) {
        // Create two dummy nodes to represent the two partitions
        ListNode lessHead = new ListNode(0); // Head of the list containing nodes less than x
        ListNode greaterEqualHead = new ListNode(0); // Head of the list containing nodes greater than or equal to x

        // Pointers to traverse the two partitions
        ListNode lessTail = lessHead;
        ListNode greaterEqualTail = greaterEqualHead;

        // Traverse the original list and partition the nodes
        ListNode current = head;
        while (current != null) {
            if (current.val < x) {
                lessTail.next = current;
                lessTail = current;
            } else {
                greaterEqualTail.next = current;
                greaterEqualTail = current;
            }
            current = current.next;
        }

        // Terminate the greaterEqualTail list to avoid cycles
        greaterEqualTail.next = null;

        // Concatenate the two partitions
        lessTail.next = greaterEqualHead.next; // Link the lessTail to the start of greaterEqual list

        // Return the head of the partitioned list
        return lessHead.next; // Return the partitioned list after dummy node
    }
    
}