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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0); // Create a dummy node to handle cases where the head needs to be removed
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        // Advance fast pointer n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            if (fast == null) {
                return head; // Handle the case where n is larger than the list size (invalid input, but good to check)
            }
            fast = fast.next;
        }

        // Move both pointers until fast reaches the end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Remove the nth node from the end
        slow.next = slow.next.next;

        return dummy.next; // Return the head of the modified list
    }
}

/**
 * Example Usage (Requires ListNode class definition)
 *
 * public class Main {
 *     public static void main(String[] args) {
 *         // Create a sample linked list: 1 -> 2 -> 3 -> 4 -> 5
 *         ListNode head = new ListNode(1);
 *         head.next = new ListNode(2);
 *         head.next.next = new ListNode(3);
 *         head.next.next.next = new ListNode(4);
 *         head.next.next.next.next = new ListNode(5);
 *
 *         Solution solution = new Solution();
 *         int n = 2; // Remove the 2nd node from the end
 *         ListNode newHead = solution.removeNthFromEnd(head, n);
 *
 *         // Print the modified linked list
 *         ListNode current = newHead;
 *         while (current != null) {
 *             System.out.print(current.val + " ");
 *             current = current.next;
 *         }
 *         // Output: 1 2 3 5
 *     }
 * }
 *
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */