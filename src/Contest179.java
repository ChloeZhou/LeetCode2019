/**
 * Created by keyingzhou on 3/7/20.
 */
import java.util.*;
public class Contest179 {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        //coner case:
        if (manager == null || informTime == null || manager.length == 0
                || informTime.length == 0 || n != manager.length || n != informTime.length) {
            return 0;
        }
        //
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < manager.length; i++) {
            if (map.containsKey(manager[i])) {
                List<Integer> children = map.get(manager[i]);
                children.add(i);
                map.put(manager[i], children);
            } else {
                List<Integer> children = new ArrayList<Integer>();
                children.add(manager[i]);
                map.put(manager[i], children);
            }
        }
        //
        int[] max = new int[1];
        dfs(headID, map, max, 0, informTime);
        return max[0];
    }

    private void dfs (int headID, Map<Integer, List<Integer>> map, int[] max, int pathTime, int[] informTime) {
        //base case:
        if (!map.containsKey(headID)) {
            max[0] = Math.max(max[0], pathTime);
            return;
        }
        //
        List<Integer> child = map.get(headID);
        pathTime += informTime[headID];
        for (int i = 0; i < child.size(); i++) {
            dfs(child.get(i), map, max, pathTime, informTime);
        }
    }

    public int numTimesAllBlue(int[] light) {
        //corner case:
        if (light == null || light.length == 0) {
            return 0;
        }
        //
        int start = light.length;
        int end = 0;
        int turnOn = 0;
        int res = 0;
        for (int i = 0; i < light.length; i++) {
            start = Math.min(start, light[i]);
            end = Math.max(end, light[i]);
            turnOn += light[i];
            if (turnOn == ((start + end) * (end - start + 1) / 2)) {
                res++;
            }
        }
        return res;
    }

    public long reverseBits(long n) {
        // Write your solution here
        int start = 0; int end = 31;
        while (start < end) {
            if ((((n >> start)&1)^((n >> end)&1)) == 1) {
                n ^= ((1<<start) | (1<<end));
            }
            start++;
            end--;
        }
        return n;
    }
}
