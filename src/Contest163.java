/**
 * Created by keyingzhou on 11/16/19.
 */
import java.util.*;
public class Contest163 {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        //coner case:
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return res;
        }
        //
        int n = grid.length; int m = grid[0].length;
        int total = n*m;
        int[] oneDi = new int[total];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int pos = i*n + j + k;
                if (pos < total) {
                    oneDi[pos] = grid[i][j];
                } else {
                    oneDi[pos % total] = grid[i][j];
                }
            }
        }
        int idx = 0;
        while (idx < total) {
            int colidx = 0;
            List<Integer> rowList = new ArrayList<Integer>();
            while (colidx < m){
                int col = idx % m;
                int row = idx / m;
                rowList.add(grid[row][col]);
                idx++;
                colidx++;
            }
            res.add(rowList);
        }
        return res;
    }
    public TreeNode root = new TreeNode(0);
    public Contest163(TreeNode root) {
        root.val = 0;
        recover(root);
        this.root = root;
    }

    public boolean find(int target) {
        return findHelper(target, this.root);

    }

    private boolean findHelper(int target, TreeNode root) {
        //base case:
        if (this.root == null) {
            return false;
        }
        if (this.root.val == target) {
            return true;
        }
        if (this.root.left == null || this.root.right == null) {
            return this.root.val == target;
        }
        //
        return findHelper(target, this.root.left) || findHelper(target, this.root.right);
    }

    private void recover(TreeNode root) {
        //base case:
        if (root.left == null || root.right == null) {
            return;
        }
        //recurisive rule
        if (root.left != null) {
            root.left.val = 2 * root.val + 1;
        }
        if (root.right != null) {
            root.right.val = 2 * root.val + 2;
        }
        //
        recover(root.left);
        recover(root.right);

    }

    public int maxSumDivThree(int[] nums) {
        //corner case:
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //
        Integer[] cubes = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            cubes[i] = nums[i];
        }
        Arrays.sort(cubes, Collections.reverseOrder());
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < cubes.length; i++) {
            s.append(cubes[i]);
        }
        System.out.println(s.toString());
        Deque<Integer> set1 = new ArrayDeque<Integer>();
        Deque<Integer> set2 = new ArrayDeque<Integer>();
        int sum = 0;
        for (int i = 0; i < cubes.length; i++) {
            int reminder =  cubes[i] % 3;
            if (reminder == 0) {
                sum += cubes[i];
            } else {
                if (reminder == 1) {
                    set1.offerFirst(cubes[i]);
                } else {
                    set2.offerFirst(cubes[i]);
                }
            }
        }
        while (!set1.isEmpty() && !set2.isEmpty()) {
            sum += set1.pollLast();
            sum += set2.pollLast();
        }
        while (!set1.isEmpty() && set1.size() > 3) {
            sum += set1.pollLast();
            sum += set1.pollLast();
            sum += set1.pollLast();
        }
        return sum;
    }
}
