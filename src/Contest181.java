/**
 * Created by keyingzhou on 3/21/20.
 */
import java.util.*;
public class Contest181 {
    public int[] createTargetArray(int[] nums, int[] index) {
        //coner case:
        if (nums == null || nums.length == 0 || index == null || index.length == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        int tailIdx = 0;
        for (int i = 0; i < index.length; i++) {
            int idx = index[i];
            if (res[idx] == -1) {
                res[idx] = nums[i];
                tailIdx = idx;
            } else {
                for (int j = tailIdx; j >= idx; j--) {
                    res[j + 1] = res[j];
                }
                tailIdx++;
                res[idx] = nums[i];
            }
        }
        return res;

    }

    public int sumFourDivisors(int[] nums) {
        //coner case:
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int divisorNum = 0;
            int[] divisors = new int[10];
            for (int j = 1; j < 10; j++) {
                if (nums[i] % j == 0) {
                    int other = nums[i] / j;
                    if (other >= 10) {
                        divisors[divisorNum++] = other;
                    }
                    divisors[divisorNum++] = j;
                }
            }
            if (divisorNum == 4) {
                for (int k = 0; k < 4; k++) {
                    sum += divisors[k];
                }
            }
        }
        return sum;
    }

    public TreeNode balanceBST(TreeNode root) {
        //coner case:
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        //Pre-order traverse to get numbers.
        List<Integer> tmp = new ArrayList<Integer>();
        preOrder(root, tmp);
        root = getBalanceTree(tmp, 0, tmp.size() - 1);
        return root;
    }

    private void preOrder(TreeNode root, List<Integer> arr) {
        //base case:
        if (root == null) {
            return;
        }
        preOrder(root.left, arr);
        arr.add(root.val);
        preOrder(root.right, arr);

    }

    private TreeNode getBalanceTree(List<Integer> arr, int start, int end) {
        if (start == end) {
            return new TreeNode(arr.get(start));
        }
        if (start > end) {
            return null;
        }
        int mid = (end - start) / 2;
        TreeNode root = new TreeNode(arr.get(mid));
        root.left = getBalanceTree(arr, start, mid - 1);
        root.right = getBalanceTree(arr, mid + 1, end);
        return root;
    }

    int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public int numIslands(char[][] grid) {
        // Write your solution here
        //coner case:
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n =  grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    dfs(grid, visited, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, boolean[][] visited, int x, int y) {
        //base case:
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length
                || grid[x][y] == '0' || visited[x][y]) {
            return;
        }
        //
        visited[x][y] = true;
        for (int i = 0; i < dir.length; i++) {
            dfs(grid, visited, x + dir[i][0], y + dir[i][1]);
        }
    }

    public String minWindow(String source, String target) {
        // Write your solution here
        String res = "";
        //coner case:
        if (source == null || target == null) {
            return res;
        }
        //s and e pointer.
        // {A:1, B:1, C: 1}
        //
        int n = source.length();
        int s = 0; int e = 0;
        int min = source.length();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < target.length(); i++) {
            map.put(target.charAt(i), map.getOrDefault(target.charAt(i), 0) + 1);
        }
        while (e < n) {
            if (map.containsKey(source.charAt(e))) {
                int num = map.get(source.charAt(e));
                if (num == 0) {
                    while (s <= e && map.get(source.charAt(e)) == 0) {
                        if (map.containsKey(source.charAt(s))) {
                            map.put(source.charAt(s), map.get(source.charAt(s)) + 1);
                        }
                        s++;
                    }

                }
                map.put(source.charAt(e), map.get(source.charAt(e)) - 1);
                if (checkMap(map)) {
                    if (min > e - s + 1) {
                        res = source.substring(s, e + 1);
                        min = e - s + 1;
                    }
                }
            }
            e++;
        }
        while (s < n) {
          if (map.containsKey(source.charAt(s)) && checkMap(map)){
              if (min > e - s) {
                  res = source.substring(s, e);
              }
              break;
          }
          s++;
        }
        return res;
    }

    private boolean checkMap(Map<Character, Integer> map){
        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            if (e.getValue() != 0) {
                return false;
            }
        }
        return true;
    }

    class UndergroundSystem {
        class Info {
            String startName;
            int t;
            public Info(String stationName, int t) {
                this.startName = stationName;
                this.t = t;
            }
        }
        private Map<Integer, Info> checkinId;
        private Map<String, Map<String, int[]>> avgMap;
        public UndergroundSystem() {
            checkinId = new HashMap<Integer, Info>();
            avgMap = new HashMap<String, Map<String, int[]>>();
        }

        public void checkIn(int id, String stationName, int t) {
            Info information = new Info(stationName, t);
            checkinId.put(id, information);
        }

        public void checkOut(int id, String stationName, int t) {
            Info information = checkinId.get(id);
            Map<String, int[]> timeMap = avgMap.getOrDefault(information.startName, new HashMap<String, int[]>());
            int[] avg = timeMap.getOrDefault(stationName, new int[]{0, 0});
            int averNum = (avg[0]*avg[1] + (t - information.t)) / (avg[1] + 1);
            int[] newAvg = new int[]{averNum, avg[1]+1};
            timeMap.put(stationName, newAvg);
            avgMap.put(information.startName, timeMap);
            checkinId.remove(id);
        }

        public double getAverageTime(String startStation, String endStation) {
            return avgMap.get(startStation).get(endStation)[0];
        }
    }
}
