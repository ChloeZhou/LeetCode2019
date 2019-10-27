/**
 * Created by keyingzhou on 10/20/19.
 */
import java.util.*;
public class Contest159 {
    public boolean checkStraightLine(int[][] coordinates) {
        //corner case:
        if (coordinates == null || coordinates.length < 2 || coordinates[0].length != 2) {
            return false;
        }
        //
        double deltaX = coordinates[1][0] - coordinates[0][0];
        double deltaY = coordinates[1][1] - coordinates[0][1];
        double tri = -1;
        if (deltaX != 0) {
            tri = deltaY / deltaX;
        }
        for (int i = 1; i < coordinates.length; i++) {
            deltaX = coordinates[i][0] - coordinates[i - 1][0];
            deltaY = coordinates[i][1] - coordinates[i - 1][1];
            if (deltaX == 0 && tri == -1) {
                continue;
            }
            double tmpTri = deltaY / deltaX;
            if (tmpTri == 0 && tri == 0) {
                continue;
            }
            else if (tmpTri != tri) {
                return false;
            }
        }
        return true;
    }

    public List<String> removeSubfolders(String[] folder) {
        List<String> ret = new ArrayList<String>();
        //corner case:
        if (folder == null || folder.length == 0) {
            return ret;
        }
        //
        Arrays.sort(folder);
        String cur = folder[0];
        ret.add(cur);
        for (int i = 1; i < folder.length; i++) {
            int pos = folder[i].indexOf(cur);
            if (pos != 0 || (pos == 0 && folder[i].length() > cur.length() && folder[i].charAt(cur.length()) != '/')) {
                cur = folder[i];
                ret.add(folder[i]);
            }
        }
        //
        return ret;
    }

    public int balancedString(String s) {
        //coner case:
        if (s == null || s.length() < 4) {
            return -1;
        }
        //
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        char[] dict = {'Q', 'W', 'E', 'R'};
        for (char c : dict) {
            map.put(c, 0);
        }
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
        }
        int avg = s.length() / 4; int start = 0;
        int res = s.length();
        for (int j = 0; j < s.length(); j++) {
            map.put(s.charAt(j), map.get(s.charAt(j)) - 1);
            while (start < s.length() && map.get('Q') <= avg && map.get('W') <= avg
                    && map.get('E') <= avg && map.get('R') <= avg) {
                res = Math.min(res, j - start + 1);
                map.put(s.charAt(start), map.get(s.charAt(start)) + 1);
                start++;
            }
        }
        return res;
    }
}
