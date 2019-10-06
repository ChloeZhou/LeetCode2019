/**
 * Created by keyingzhou on 9/28/19.
 */
import java.util.*;
public class Contest156 {

    public boolean uniqueOccurrences(int[] arr) {
        //corner case:
        if (arr == null || arr.length < 2) {
            return true;
        }
        //
        Map<Integer, Integer> curMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            curMap.put(arr[i], curMap.getOrDefault(arr[i], 0) + 1);
        }
        Set<Integer> set = new HashSet<Integer>();
        for (Map.Entry<Integer, Integer> e : curMap.entrySet()) {
            if (set.contains(e.getValue())) {
                return false;
            } else {
                set.add(e.getValue());
            }
        }
        return true;
    }

    public int equalSubstring(String s, String t, int maxCost) {
        //corner case:
        if (s.length() == 0 || t.length() == 0) {
            return 0;
        }
        if (s.length() == 1 || t.length() == 1) {
            return Math.abs(s.charAt(1) - t.charAt(1)) > maxCost? 0 : 1;
        }
        //
        int n = s.length();
        int[] dp = new int[n];
        int curCost = Math.abs(s.charAt(0) - t.charAt(0));
        dp[0] = curCost > maxCost ? 0 : 1;
        if (dp[0] == 0) {
            curCost = 0;
        }
        //
        int maxLen = dp[0];
        for (int i = 1; i < n; i++) {
            curCost += Math.abs(s.charAt(i) - t.charAt(i));
            if (curCost > maxCost) {
                dp[i] = Math.abs(s.charAt(i) - t.charAt(i)) > maxCost ? 0 : 1;
                if (dp[i] == 0) {
                    curCost = 0;
                } else {
                    int j = i - dp[i - 1];
                    while (curCost > maxCost && j < s.length()) {
                        curCost -= Math.abs(s.charAt(j) - t.charAt(j));
                        j++;
                    }
                    dp[i] = i - j;

                }
            } else {
                dp[i] = dp[i - 1] + 1;
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    public String removeDuplicates(String s, int k) {
        //corner case:
        if (s == null || s.length() < k) {
            return s;
        }
        //
        Deque<Character> st = new ArrayDeque<Character>();
        Deque<Integer> st2 = new ArrayDeque<Integer>();
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (st.isEmpty()) {
                st.offerLast(s.charAt(i));
                cnt = 1;
                st2.offerLast(cnt);
            } else if (st.getLast() == s.charAt(i)){
                cnt = st2.pollLast() + 1;
                if (cnt == k) {
                    int p = k - 1;
                    while (p > 0) {
                        st.pollLast();
                        p--;
                    }
                } else {
                    st.offerLast(s.charAt(i));
                    st2.offerLast(cnt);
                }
            } else {
                cnt = 1;
                st.offerLast(s.charAt(i));
                st2.offerLast(cnt);
            }
        }
        while (!st.isEmpty()) {
            sb.append(st.pollFirst());
        }
        return sb.toString();
    }

    public int minimumMoves(int[][] grid) {
        //corner case:
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        //do dfs on grid
        //recursive rule:
        //if verHor = true, move right, past start pos
        //if
        boolean verHor = true; //true for Hor, false for ver;
        int[] steps = new int[1];
        steps[0] = 0;
        int[] head = {0, 0};
        int[] tail = {0, 1};
        int[] min = new int[1];
        min[0] = Integer.MAX_VALUE;
        dfs(grid, head, tail, verHor, steps, min);
        return min[0];
    }

    public void dfs (int[][] grid, int[] head, int[] tail, boolean verHor, int[] steps, int[] min) {
        //base case:
        int n = grid.length;
        if (head[0] == n-1 && head[1] == n-1 && tail[0] == n-1 && tail[1] == n-2 ||
                head[0] == n-1 && head[1] == n-2 && tail[0] == n-1 && tail[1] == n-1) {
            min[0] = Math.min(min[0], steps[0]);
            return;
        }
        //recursive rule:
        if (verHor == true) {
            int[] right = {head[0], head[1] + 1};
            int[] rightT = {tail[0], tail[1] + 1};
            if (grid[right[0]][right[1]] != 1) {
                steps[0]++;
                dfs(grid, right, rightT, verHor, steps, min);
            }
        } else {
            int[] right = {head[0], head[1] + 1};
            int[] rightT = {tail[0], tail[1] + 1};
            if (grid[right[0]][right[1]] != 1) {
                steps[0]++;
                dfs(grid, right, rightT, verHor, steps, min);
            }
        }
    }
}
