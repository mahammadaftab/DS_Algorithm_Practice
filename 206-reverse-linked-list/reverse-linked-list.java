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
    public ListNode reverseList(ListNode head) {
        ListNode prev = null; // Initialize previous node to null
        ListNode curr = head; // Initialize current node to head
        ListNode next = null; // Initialize next node to null

        while (curr != null) {
            next = curr.next; // Store the next node
            curr.next = prev; // Reverse the pointer of the current node
            prev = curr;       // Move previous node to the current node
            curr = next;       // Move current node to the next node
        }

        return prev; // After the loop, prev will be the new head of the reversed list
    }
}

