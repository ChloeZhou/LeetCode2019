/**
 * Created by keyingzhou on 11/23/19.
 */
public class Contest164 {
    public int minTimeToVisitAllPoints(int[][] points) {
        //corner case:
        if (points == null || points.length == 0 || points[0].length == 0) {
            return 0;
        }
        //
        int res = 0;
        for (int i = 0; i < points.length - 1; i++) {
            int maxDia = Math.min(Math.abs(points[i + 1][0] - points[i][0]), Math.abs(points[i + 1][1] - points[i][1]));
            int newPosX = points[i + 1][0] > points[i][0] ? points[i][0] + maxDia : points[i][0] - maxDia;
            int newPosY = points[i + 1][1] > points[i][1] ? points[i][1] + maxDia : points[i][1] - maxDia;
            int extra = Math.max(Math.abs(points[i + 1][0] - newPosX), Math.abs(points[i + 1][1] - newPosY));
            res += (maxDia + extra);
        }
        return res;
    }

    public int countServers(int[][] grid) {
        //coner case:
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        //
        int n = grid.length; int m = grid[0].length;
        int[][] visited = new int[n][m];
        int res = 0;
        int[] flagRows = new int[n];
        int[] flagColumns = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    flagRows[i]++;
                    flagColumns[j]++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && (flagRows[i] > 1 || flagColumns[j] > 1)) {
                    res++;
                }
            }
        }

        return res;
    }
}
