import java.util.*;
import java.util.LinkedList;

/**
 * Created by keyingzhou on 10/28/18.
 */
public class IterativeTraverse {
    public List<Integer> postOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        //corner case:
        if (root == null) {
            return res;
        }
        //
        Deque<TreeNode> st1 = new LinkedList<>();
        Deque<TreeNode> st2 = new LinkedList<>();
        st1.offerFirst(root);
        while (!st1.isEmpty()) {
            TreeNode cur = st1.pollFirst();
            st2.offerFirst(cur);
            if (cur.left != null) {
                st1.offerFirst(cur.left);
            }
            if (cur.right != null) {
                st1.offerFirst(cur);
            }
        }
        while (!st2.isEmpty()) {
            res.add(st2.pollFirst().val);
        }
        return res;
    }

    public List<Integer> inOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        //corner case:
        if (root == null) {
            return res;
        }
        //
        Deque<TreeNode> st = new LinkedList<>();
        TreeNode cur = root;
        while (!st.isEmpty() || cur != null) {
            if (cur != null) {
                st.offerFirst(cur);
                cur = cur.left;
            } else {
                cur = st.pollFirst();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }
}
