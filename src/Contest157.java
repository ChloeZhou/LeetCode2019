/**
 * Created by keyingzhou on 10/6/19.
 */
import java.util.*;
public class Contest157 {
    public int minCostToMoveChips(int[] chips) {
        //corner case:
        if (chips == null || chips.length < 2) {
            return 0;
        }
        //
        Set<Integer> pos = new HashSet<Integer>();
        for (int i = 0; i < chips.length; i++) {
            pos.add(chips[i]);
        }
        //
        //
        int min = Integer.MAX_VALUE;
        for (Integer position : pos) {
            int cnt = 0;
            for (int i = 0; i < chips.length; i++) {
                if(Math.abs(chips[i] - position) % 2 == 1) {
                    cnt += 1;
                }
            }
            min = Math.min(min, cnt);
        }
        return min;
    }

    public int longestSubsequence(int[] arr, int difference) {
        //corner case:
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //dp array means the longest length of subsequence end with ith element
        int[] dp = new int[arr.length];
        //
        dp[0] = 1;
        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 1; i < arr.length; i++) {
            dp[i] = 1;
            if (map.containsKey(arr[i] - difference)) {
                dp[i] = Math.max(map.get(arr[i] - difference) + 1, dp[i]);
            }
            map.put(arr[i], dp[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int countVowelPermutation(int n) {
        int mod = (int)(1e9 + 7);
        //n path end with different vowels
        long[][] dp = new long[n + 1][5];
        for (int i = 0; i < 5; i++) {
            dp[1][i] = 1;
        }
        //induction rule:
        for (int i = 2; i <= n; i++) {
            dp[i][0] = (dp[i - 1][1] + dp[i - 1][4] + dp[i - 1][2]) % mod;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % mod;
            dp[i][2] = (dp[i - 1][1] + dp[i - 1][3]) % mod;
            dp[i][3] = (dp[i - 1][2]) % mod;
            dp[i][4] = (dp[i - 1][3] + dp[i - 1][2]) % mod;
        }
        //
        long ans = 0;
        for (int i = 0; i < 5; i++) {
            ans = (ans + dp[n][i]) % mod;
        }
        return (int)ans;
    }

    int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int getMaximumGold(int[][] grid) {
        //corner case：
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        //do dfs search
        int ret = 0;
        int n = grid.length, m = grid[0].length;
        int[][] visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != 0) {
                    ret = Math.max(ret, dfs(i, j, visited, grid));
                }
            }
        }
        return ret;
    }

    private int dfs(int i, int j, int[][] visited, int[][] grid) {
        visited[i][j] = 1;
        int n = grid.length, m = grid[0].length;
        int max = 0;
        for (int k = 0; k < 4; k++) {
            int ny = i + d[k][0];
            int nx = j + d[k][1];
            if (ny >= 0 && ny < n && nx >= 0 && nx < m && visited[ny][nx] != 1 && grid[ny][nx] != 0) {
                max = Math.max(max, dfs(ny, nx, visited, grid));
            }
        }
        visited[i][j] = 0;
        return grid[i][j] + max;
    }
}
