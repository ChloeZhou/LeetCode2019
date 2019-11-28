import java.util.ArrayList;
import java.util.List;

/**
 * Created by keyingzhou on 11/9/19.
 */
public class Contest162 {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        //coner case:
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (colsum == null || colsum.length == 0) {
            return res;
        }
        List<Integer> upperArray = new ArrayList<Integer>();
        int upperCnt = 0;
        for (int i = 0; i < colsum.length; i++) {
            if (colsum[i] >= 1 && upperCnt < upper) {
                upperArray.add(1);
                upperCnt++;
            } else {
                upperArray.add(0);
            }
        }
        int lowerCnt = 0;
        List<Integer> lowerArray = new ArrayList<Integer>();
        for (int i = 0; i < colsum.length; i++) {
            if ((colsum[i] == 1 && upperArray.get(i) == 0 || colsum[i] == 2 && upperArray.get(i) == 1) && lowerCnt < lower) {
                lowerArray.add(1);
                lowerCnt++;
            } else {
                lowerArray.add(0);
            }
        }
        for (int i = 0; i < colsum.length; i++) {
            if (upperArray.get(i) + lowerArray.get(i) < colsum[i]) {
                return res;
            }
        }
        res.add(upperArray);
        res.add(lowerArray);
        return res;
    }

    int[][] DIRECTION = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int closedIsland(int[][] grid) {
        //coner case:
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        //list
        //
        int n = grid.length; int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    boolean res = dfs(grid, i, j, visited);
                    if (res) {
                        cnt++;
                    }
                }

            }
        }
        return cnt;
    }
    private boolean dfs(int[][] grid, int row, int col, boolean[][] visited) {
        //base case:
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || visited[row][col]) {
            return false;
        }
        if (grid[row][col] == 1) {
            return true;
        }
        //
        visited[row][col] = true;
        boolean[] res = new boolean[4];
        for (int i = 0; i < DIRECTION.length; i++) {
            int rowNext = row + DIRECTION[i][0];
            int colNext = col + DIRECTION[i][1];
                res[i] = dfs(grid, rowNext, colNext, visited);
        }
        return res[0] && res[1] && res[2] && res[3];
    }

    public int[] quickSort(int[] array) {
        // Write your solution here
        //coner case:
        if (array == null || array.length < 2) {
            return array;
        }
        //
        quickSortHelper(array, 0, array.length - 1);
        return array;
    }

    private void quickSortHelper(int[] array, int left, int right) {
        //base case:
        if (left >= right) {
            return;
        }
        //
        int pivot = pickPivot(array, left, right);
        quickSortHelper(array, left, pivot);
        quickSortHelper(array, pivot + 1, right);
    }

    private int pickPivot(int[] array, int left, int right) {
        //
        int pivot = (int)(Math.random() * ((right - left) + 1)) + left;
        swap(array, right, pivot);
        int start = left;
        int end = right - 1;
        while (start <= end) {
            if (array[start] < array[right]) {
                start++;
            } else {
                swap(array, start, end);
                end--;
            }
        }
        swap(array, start, right);
        return pivot;
    }

    private void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

}
