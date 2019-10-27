/**
 * Created by keyingzhou on 10/12/19.
 */
import java.util.*;
public class Contest158 {
    public int balancedStringSplit(String s) {
        //corner case:
        if (s == null || s.length() < 2) {
            return 0;
        }
        //
        int[] dict = new int[2];
        int ret = 0;
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                dict[0]++;
            } else {
                dict[1]++;
            }
            if (dict[0] == dict[1]) {
                ret++;
                dict[0] = 0;
                dict[1] = 0;
            }
        }
        return ret;
    }

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        //coner case:
        if (queens == null || king == null) {
            return null;
        }
        //
        int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        int[][] chass = new int[8][8];
        for (int i = 0; i < queens.length; i++) {
            chass[queens[i][0]][queens[i][1]] = 1;
        }
        for (int i = 0; i < dir.length; i++) {
            int ny = king[0] + dir[i][0];
            int nx = king[1] + dir[i][1];
            while (nx >= 0 && ny >= 0 && nx < 8 && ny < 8) {
                if (chass[ny][nx] == 1) {
                    List<Integer> ans = new ArrayList<Integer>();
                    ans.add(ny);
                    ans.add(nx);
                    ret.add(ans);
                    break;
                } else{
                    ny += dir[i][0];
                    nx += dir[i][1];
                }
            }
        }
        return ret;
    }
    public int dieSimulator(int n, int[] rollMax) {
        //int[i][j] dp = the ith roll end with idx jj:
        //base: int[1][j] dp = 1
        int mod = (int)1e9 + 7;
        long notEndWith = 0;
        long res = (long)Math.pow(6, n);
        for (int i = 0; i < 6; i++) {
            int length = n - rollMax[i] - 1;
            notEndWith = (long)Math.pow(6, length);
            res -= notEndWith;

        }
        return (int)(res % mod);
    }
}
