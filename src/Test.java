import java.util.*;
import java.util.LinkedList;

/**
 * Created by keyingzhou on 6/14/17.
 */
public class Test {
    public static void main(String[] arg) {
        //int[][] input = {{0, 1}, {0, 2}, {1, 2}};
        //Solution s = new Solution();
        //int[] nums = {4, 5, 6, 7, 0, 1, 2};
        //ListNode head = new ListNode(2);
        //head.next = new ListNode(1);
        //head.next.next = new ListNode(3);
        //head.next.next.next = new ListNode(5);
        //head.next.next.next.next = new ListNode(2);
        //int[][] matrix = s.generateMatrix(5);
        /*for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }*/
        int INF = Integer.MAX_VALUE;
        //String[] inputA = {"i.......lovegoogle@example.com", "....ab@example.com", "ab+adfaekwjf@example.com","ab+ad@example.com", "ab..@example.com", "i.l.o.v.e.g.o.o.g.l.e@example.com", "ilovegoogle@example.com"};
        TreeNode node1 = new TreeNode(5);
        node1.left = new TreeNode(3);
        node1.left.right = new TreeNode(4);
        node1.left.left = new TreeNode(2);
        node1.left.left.left = new TreeNode(1);
        node1.right = new TreeNode(6);
        node1.right.right = new TreeNode(8);
        node1.right.right.left = new TreeNode(7);
        node1.right.right.right = new TreeNode(9);
        char[][] maze = {{'0','E','0','0'},{'E','0','W','E'},{'0','E','0','0'}};
        int[] start = {4, 3};
        int[] end ={0,1};
        //System.out.println(s.increasingBST(node1));
        //System.out.println(s.solution(inputA));
        int[][] buttons = {{30, 40},{20, 40},{60, 70}};
        //int[] output = {130, 160};
        int[] wages = {4,8,2,2,7};
        //int[] A = {1,0,1,1,0};
        //String[] A = {"amazon","apple","facebook","google","leetcode"};
        //int[][] b = {{0, 0, 1, 0},{0, 1, 1, 0},{0, 1, 0, 1}};
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        //root.left.right = new TreeNode(5);
        //root.right.left = new TreeNode(6);
        String[] A = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
        String B = "Hackerrank to practice";
        //TreeNode B = root.left.right;
        //s.addNum(-4);
        //int[] output = s.reOrder(4);
        //["NumArray","sumRange","sumRange","sumRange","update","update","update","sumRange","update","sumRange","update"]
        //[[[0,9,5,7,3]],[4,4],[2,4],[3,3],[4,5],[1,7],[0,8],[1,2],[1,9],[4,4],[3,4]
        int[][] nums1 = {{0, 1, 1, 0, 1},
                {0, 1, 0, 1, 0},{0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0}};
        String[] nums2 = {"ift","efd","dnete","tef","fdn"};
        //s.countNodes(nums1);
        Solution s = new Solution();
        Contest184 c = new Contest184();
        //System.out.println(s.shortestSuperstring(nums2));
        String res = "iftefdnete";
        //System.out.println(res.length() + " " + s.shortestSuperstring(nums2).length());
        String[] cpdomains = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        //int[][] input = {{0,0,1,0,0},{0,1,0,1,0},{0,1,1,1,0}};
        //String[] input = {"i", "love", "leetcode", "i", "love", "coding"};
        List<int[]> a = new ArrayList<int[]>();
        a.add(new int[]{1, 8});
        a.add(new int[]{2, 15});
        a.add(new int[]{3, 9});
        List<int[]> b = new ArrayList<int[]>();
        b.add(new int[]{1, 8});
        b.add(new int[]{2, 11});
        b.add(new int[]{3, 12});
        //int  n = 1000000000, a = 2, b1 = 217983653, c1 = 336916467;
//        ListNode head = new ListNode(0);
//        s.pollLast();
//        s.peekFirst();
//        s.offerLast(217);
//        System.out.println(s.peekFirst());
//        s.offerFirst(188);
//        System.out.println(s.pollLast());
//        System.out.println(s.isEmpty());
//        s.offerLast(77);
//        s.offerLast(234);
//        System.out.println(s.peekLast());
//        System.out.println(s.pollFirst());
//        Deque<int[]> q = new LinkedList<>();
        String[] input = {"croakcrook"};
        System.out.println(c.minNumberOfFrogs("aoocrrackk"));
//        for (int i = 0; i < result.size(); i++) {
//            System.out.println(result.get(i)[0] + " " + result.get(i)[1]);
//        }
        //System.out.println(c.getPairs(a, b, 20));
        //q.offerFirst(new int[]{row, col});
//        for (int i = 0; i < nums1.length; i++) {
//            System.out.print(nums1[i]);
//        }
        //s.addAtIndex(1, 9);
        //s.addAtIndex(1, 5);

    }

}