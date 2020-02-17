/**
 * Created by keyingzhou on 2/1/20.
 */
import java.util.*;
public class Contest174 {
    public int[] kWeakestRows(int[][] mat, int k) {
        //coner case:
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return new int[k];
        }
        //
        PriorityQueue<List<Integer>> min = new PriorityQueue<List<Integer>>(new Comparator<List<Integer>>(){
            public int compare(List<Integer> a, List<Integer> b) {
                if (a.get(1) == b.get(1)) {
                    return 0;
                }
                return a.get(1) < b.get(1) ? -1 : 1;
            }
        });
        for (int i = 0; i < mat.length; i++) {
            int cnt = 0;
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    cnt++;
                }
            }
            List<Integer> ele = new ArrayList<Integer>();
            ele.add(i);
            ele.add(cnt);
            min.add(ele);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = min.poll().get(0);
        }
        return res;
    }

    public int minSetSize(int[] arr) {
        //conrer case:
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //
        int size = arr.length / 2;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        PriorityQueue<int[]> max = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[1] == b[1]) {
                    return 0;
                }
                return a[1] > b[1] ? -1 : 1;
            }
        });
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            int[] eArr = new int[2];
            eArr[0] = e.getKey(); eArr[1] = e.getValue();
            max.offer(eArr);
        }
        int cntSize = 0; int cnt = 0;
        while (cntSize < size) {
            int[] cur = max.poll();
            cntSize += cur[1];
            cnt++;
        }
        return cnt;
    }
}
