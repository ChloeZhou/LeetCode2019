

import java.util.*;
import java.util.LinkedList;

/**
 * Created by keyingzhou on 6/14/17.
 */
/*
第二题，给两个array,一个storese, 一个houses,要求输出每个house最近的stores的位置，store和house都有重复。我用的Binary Search。
*/
//[3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4
//                                                                  []
//                                      [1]                                                     []
//         [1, 2]                                                   [1]                     [2]                             []
//  [1, 2, 3]                       [1, 2]                  [1, 3]          [1]
// [1, 2, 3, 4] [1, 2, 3]   [1, 2, 4]      [1, 2]     [1, 3, 4]  [1, 3]   [1]  [1, 4]
class Solution {
    public int rangeSumBST(TreeNode root, int L, int R) {
        //corner case:
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val <= R && root.val >= L ? root.val : 0;
        }
        int sum = 0;
        //recursion rule:
        if (root.val < L) {
            sum = rangeSumBST(root.right, L, R);
        } else if (root.val > R) {
            sum = rangeSumBST(root.left, L, R);
        } else {
            if (root.left != null) {
                sum += rangeSumBST(root.left, L, root.val);
            }
            if (root.right != null) {
                sum += rangeSumBST(root.right, root.val, R);
            }
            sum += root.val;
        }
        return sum;
    }

}



/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */


