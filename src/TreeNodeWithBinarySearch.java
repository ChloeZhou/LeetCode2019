/**
 * Created by keyingzhou on 1/20/20.
 */
public class TreeNodeWithBinarySearch {
    public boolean bsTreeNode(TreeNode root) {
        //corner case:
        if (root == null) {
            return false;
        }
        //
        int height = getHeight(root);
        int size = (int)Math.pow(2, height);
        while (root.left != null && root.right != null) {

        }
        return false;
    }

    private int getHeight(TreeNode root) {
        return 0;
    }
}
