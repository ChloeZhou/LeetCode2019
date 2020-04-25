/**
 * Created by keyingzhou on 3/14/20.
 */
import java.util.*;
public class Contest180 {
    public List<Integer> luckyNumbers (int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        Set<Integer> nums = new HashSet<Integer>();
        for (int i = 0; i < matrix.length; i++) {
            int rowMin = Integer.MAX_VALUE;
            for (int j = 0; j < matrix[i].length; j++) {
                rowMin = Math.min(rowMin, matrix[i][j]);
            }
            nums.add(rowMin);
        }
        for (int i = 0; i < matrix[0].length; i++) {
            int colMax = Integer.MIN_VALUE;
            for (int j = 0; j < matrix.length; j++) {
                colMax = Math.max(colMax, matrix[j][i]);
            }
            if (nums.contains(colMax)) {
                res.add(colMax);
            }
        }
        return res;
    }

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        //corner case:
        if (speed == null || efficiency == null || speed.length == 0 || efficiency.length == 0) {
            return 0;
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue(new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if (a[1] == b[1]) {
                    return 0;
                }
                return a[1] < b[1] ? -1 : 1;
            }
        });
        int sumSpeed = 0;
        for (int i = 0; i < speed.length; i++) {
            if (i < k) {
                minHeap.offer(new int[]{i, efficiency[i]});
                sumSpeed += speed[i];
            } else {
                int[] curMin = minHeap.poll();
                int tempSpeed = sumSpeed - speed[curMin[0]] + speed[i];
                //minHeap.offer(new int[]{i, efficiency[i]});
                int[] tempMin = minHeap.peek();
                if (sumSpeed*curMin[1] < tempSpeed*tempMin[1]) {
                    minHeap.offer(new int[]{i, efficiency[i]});
                    sumSpeed = tempSpeed;
                } else {
                    minHeap.offer(curMin);
                }
            }

        }
        int[] min = minHeap.poll();
        return sumSpeed*min[1];


    }
}
