/**
 * Created by keyingzhou on 9/7/19.
 */
import java.util.*;
public class Contest135 {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        //corner case:
        if (distance == null || distance.length < 1) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < distance.length; i++) {
            sum += distance[i];
        }
        int s = start < destination ? start : destination;
        int e = start > destination ? start : destination;
        int half = 0;
        for (int i = s; i < e; i++) {
            half += distance[i];
        }
        return Math.min(half, sum - half);
    }

    public String dayOfTheWeek(int day, int month, int year) {
        String[] dayOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int k = day;
        Map<Integer, Integer> monthMap = new HashMap<Integer, Integer>()
        {
            {
                put(1, 11);
                put(2, 12);
                put(3, 1);
                put(4, 2);
                put(5, 3);
                put(6, 4);
                put(7, 5);
                put(8, 6);
                put(9, 7);
                put(10, 8);
                put(11, 9);
                put(12, 10);
            }
        };
        int m = monthMap.get(month);
        int D = 0;
        if (month < 3) {
            D = year % 100 - 1;
            if (D == -1) {
                D = 99;
            }
        } else {
            D = year % 100;
        }
        int C = year / 100;
        System.out.println(D + " " + C + " " + m);
        int f = k + ((13*m-1)/5) + D + (D/4) + (C/4) - 2*C;
        System.out.println(f);
        int idx = f%7;
        if (idx < 0) {
            idx += 7;
        }
        return dayOfWeek[idx];
    }

    public int maximumSum(int[] arr) {
        //corner case:
        if (arr.length == 1) {
            return arr[0];
        }
        //
        int[] startFromI = new int[arr.length];
        int[] endByI = new int[arr.length];
        endByI[0] = arr[0];
        startFromI[arr.length - 1] = arr[arr.length - 1];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            endByI[i] = endByI[i - 1] > 0 ? endByI[i - 1] + arr[i] : arr[i];
            max = Math.max(max, endByI[i]);
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            startFromI[i] = startFromI[i + 1] > 0 ? startFromI[i + 1] + arr[i] : arr[i];
        }
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] < 0) {
                max = Math.max(max, endByI[i - 1] + startFromI[i + 1]);
            }
        }
        return max;
    }
}
