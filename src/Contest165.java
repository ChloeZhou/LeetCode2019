/**
 * Created by keyingzhou on 11/30/19.
 */
import java.util.*;

public class Contest165 {
    public String tictactoe(int[][] moves) {
        //coner case:
        if (moves == null || moves.length == 0 || moves[0].length == 0) {
            return "Pending";
        }
        //
        char[][] plane = new char[3][3];
        for (int i = 0; i < moves.length; i++) {
            if (i % 2 == 0) {
                plane[moves[i][0]][moves[i][1]] = 'X';
            } else {
                plane[moves[i][0]][moves[i][1]] = 'O';
            }
        }
        //check res rows, columns, diags
        String leftdiag = "";
        String rightdiag = "";
        boolean full = true;
        for (int i = 0; i < plane.length; i++) {
            //rows
            String row = "";
            String column = "";
            for (int j = 0; j <plane[0].length; j++) {
                row += plane[i][j];
                column += plane[j][i];
                if (i == j) {
                    leftdiag += plane[i][j];
                }
                if (i + j == 2) {
                    rightdiag += plane[i][j];
                }
                if (plane[i][j] == ' ') {
                    full = false;
                }
            }
            if (row.equals("XXX") || column.equals("XXX")) {
                return "A";
            }
            if (row.equals("OOO") || column.equals("OOO")) {
                return "B";
            }
            System.out.println(row);
            System.out.println(column);
        }
        System.out.println(leftdiag);
        System.out.println(rightdiag);
        if (leftdiag.equals("XXX")|| leftdiag.equals("XXX")) {
            return "A";
        }
        if (rightdiag.equals("OOO") || rightdiag.equals("OOO")) {
            return "B";
        }
        if (full) {
            return "Pending";
        }
        return "Draw";

    }
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        List<Integer> res = new ArrayList<Integer>();
        res.add(0);
        res.add(0);
        //coner case:
        if (tomatoSlices == 0 || cheeseSlices == 0) {
            return res;
        }
        //
        int expectNum = cheeseSlices;
        for (int i = 0; i < expectNum + 1; i++) {
            int j = expectNum - i;
            if (4 * i + 2 * j == tomatoSlices && i + j == expectNum) {
                res.add(i);
                res.add(j);
                return res;
            }
        }
        return res;
    }
    public ListNode mergeSort(ListNode head) {
        // Write your solution here
        //base case:
        if (head == null || head.next == null) {
            return head;
        }
        //recursive
        ListNode slow = head; ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = null;
        ListNode newHeadOne = mergeSort(head);
        ListNode newHeadTwo = mergeSort(fast);
        ListNode dummy = new ListNode(0); ListNode cur = dummy;
        while (newHeadOne != null && newHeadTwo != null) {
            if (newHeadOne.value < newHeadTwo.value) {
                cur.next = newHeadOne;
                newHeadOne = newHeadOne.next;
            } else {
                cur.next = newHeadTwo;
                newHeadTwo = newHeadTwo.next;
            }
            cur = cur.next;
        }

        if (newHeadOne != null) {
            cur.next = newHeadOne;
        }
        if (newHeadTwo != null) {
            cur.next = newHeadTwo;
        }
        return dummy.next;
    }
    public ListNode removeElements(ListNode head, int val) {
        // Write your solution here
        //coner case:
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            if (cur.value == val) {
                prev.next = next;
                cur.next = null;
            } else {
                prev = cur;
            }
            cur = next;
        }
        return dummy.next;
    }


    public void sort(LinkedList<Integer> s1) {
        LinkedList<Integer> s2 = new LinkedList<Integer>();
        // Write your solution here

        while (!s1.isEmpty()) {
            int temp = s1.getLast();
            while (!s2.isEmpty() && s2.getLast() > temp) {
                s1.addLast(s2.getLast());
            }
            s2.addLast(temp);
        }
    }

    public int[] getNoZeroIntegers(int n) {
        //get highest 0
        int temp = n;
        int idx = 0;
        while (temp > 0) {
            if (temp % 10 != 0) {
                temp = temp / 10;
            } else {
                break;
            }
            idx++;
        }
        int[] res = new int[2];
        res[0] = n - temp* (int)Math.pow(10, idx) + 1;
        res[1] = n - res[0];
        return res;
    }
    public int minFlips(int a, int b, int c) {
        int bit = (a | b) ^ c;
        int cnt = 0;
        int i = 0;
        int max = Math.max(a, Math.max(b, c));
        while (i <= Integer.toBinaryString(max).length()) {
            int tempa = (a >> i) & 1;
            int tempb = (b >> i) & 1;
            int tempc = (c >> i) & 1;
            if (tempc == 0 && ((tempa | tempb) != 0)) {
                cnt += tempa == 1 ? 1 : 0;
                cnt += tempb == 1 ? 1 : 0;
            }
            if (tempc == 1 && ((tempa | tempb) != 1)) {
                cnt++;
            }
            i++;
        }
        return cnt;
    }

    class MyQueue {
        Deque<Integer> st1;
        Deque<Integer> st2;
        /** Initialize your data structure here. */
        public MyQueue() {
            this.st1 = new LinkedList<Integer>();
            this.st2 = new LinkedList<Integer>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            st1.offerLast(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (st2.isEmpty()) {
                while (!st1.isEmpty()) {
                    st2.offerLast(st1.pollLast());
                }
            }
            return empty() ? -1 : st2.pollLast();
        }

        /** Get the front element. */
        public int peek() {
            if (st2.isEmpty()) {
                while (!st1.isEmpty()) {
                    st2.offerLast(st1.pollLast());
                }
            }
            return empty() ? -1 : st2.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return st1.isEmpty() && st2.isEmpty();
        }
    }


    public List<String> printVertically(String s) {
        //corner case:
        List<String> res = new ArrayList<String>();
        if (s.length() == 0) {
            return null;
        }
        String[] sArray = s.split(" ");
        Integer max = Integer.MIN_VALUE;
        for (int i = 0; i < sArray.length; i++) {
            max = Math.max(sArray[i].length(), max);
        }
        char[][] a = new char[max][sArray.length];
        for (int i = 0; i < sArray.length; i++) {
            for (int j = 0; j < sArray[i].length(); j++) {
                a[j][i] = sArray[i].charAt(j);
            }
        }
        for (int i = 0; i < a[0].length; i++) {
            StringBuilder sb = new StringBuilder();
            boolean flag = true;
            for (int j = a.length - 1; j >= 0; j--) {
                if (a[i][j] != ' ' && flag) {
                    flag = false;
                    sb.insert(0, a[i][j]);
                } else if (!flag) {
                    sb.insert(0, a[i][j]);
                }
            }
            res.add(sb.toString());
        }
        return res;
    }
}
