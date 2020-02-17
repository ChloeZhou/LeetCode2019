

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

    Stack<Integer> st1;
    Stack<Integer> st2;
    Stack<Integer> st3;
    public Solution() {
        // Write your solution here.
        st1 = new Stack<Integer>();
        st2 = new Stack<Integer>();
        st3 = new Stack<Integer>();
    }

    public void offerFirst(int element) {
        st1.push(element);
    }

    public void offerLast(int element) {
        st2.push(element);
    }

    public Integer pollFirst() {
        if (st1.isEmpty()) {
            int size = st2.size();
            for (int i = 0; i < size; i++) {
                st1.push(st2.pop());
            }
        }

        return isEmpty() ? null : st1.pop();
    }

    public Integer pollLast() {
        if (st2.isEmpty()) {
            int size = st1.size();
            for (int i = 0; i < size; i++) {
                st2.push(st1.pop());
            }
        }
        return isEmpty() ? null : st2.pop();
    }

    public Integer peekFirst() {
        if (st1.isEmpty()) {
            int size = st2.size();
            for (int i = 0; i < size; i++) {
                st1.push(st2.pop());
            }
        }
        return isEmpty() ? null : st1.peek();
    }

    public Integer peekLast() {
        if (st2.isEmpty()) {
            int size = st1.size();
            for (int i = 0; i < size; i++) {
                st2.push(st1.pop());
            }
        }
        return isEmpty() ? null : st2.peek();
    }

    public int size() {
        return st1.size() + st2.size();
    }

    public boolean isEmpty() {
        return st1.isEmpty() && st2.isEmpty();
    }

}



/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */


