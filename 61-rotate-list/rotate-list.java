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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // 1. Calculate the length of the linked list
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // 2. Reduce k to be within the range of list length
        k = k % length;

        if (k == 0) {
            return head;
        }

        // 3. Find the node to break the list
        ListNode current = head;
        for (int i = 1; i < length - k; i++) {
            current = current.next;
        }

        // 4. Perform the rotation
        ListNode newHead = current.next;
        current.next = null;
        tail.next = head;

        return newHead;
    }
}



