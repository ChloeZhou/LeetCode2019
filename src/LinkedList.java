/**
 * Created by keyingzhou on 12/25/17.
 */
public class LinkedList {

    public int length (ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    public ListNode getIndex (ListNode head, int index) {
        ListNode cur = head;
        while (cur != null && index > 0) {
            index--;
            cur = cur.next;
        }
        return cur;
    }

    public ListNode appendHead (ListNode head, int n) {
        ListNode newHead = new ListNode(n);
        newHead.next = head;
        return newHead;
    }

    public ListNode appendTail (ListNode head, int n) {
        if (head == null) {
            return new ListNode(n);
        }
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = new ListNode(n);
        return head;
    }

    public ListNode remove (int tar, ListNode head) {
        ListNode cur = head;
        if (head == null) {
            return head;
        }
        if (head.val == tar) {
            return head.next;
        }
        while (cur.next != null) {
            if (cur.next.val == tar) {
                cur.next = cur.next.next;
                return head;
            }
            cur = cur.next;
        }
        return head;
    }

}
