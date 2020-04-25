/**
 * Created by keyingzhou on 4/4/20.
 */
import java.util.*;
public class Biweekly23 {
    public int countLargestGroup(int n) {
        Map<Integer, List<Integer>> rec = new HashMap<Integer, List<Integer>>();
        //corner case:
        for (int i = 1; i <= n; i++) {
            int sum = 0; int cur = i;
            while (cur > 0) {
                sum += cur % 10;
                cur /= 10;
            }
            List<Integer> res = rec.getOrDefault(sum, new ArrayList<Integer>());
            res.add(i);
            rec.put(sum, res);
        }
        int max = 0;
        for (Map.Entry<Integer, List<Integer>> en : rec.entrySet()) {
            max = Math.max(max, en.getValue().size());
        }
        return max;
    }

    public int numSteps(String s) {
        //corner case:
        if (s == null || s.length() == 0) {
            return 0;
        }
        //
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        int cnt = 0;
        while (sb.length() != 1) {
            int end = sb.length() - 1;
            if (sb.charAt(end) == '0') {
                sb.deleteCharAt(end);
            } else {
                sb.deleteCharAt(end);
                sb.append('0');
                end--;
                if (sb.charAt(end) == '0') {
                    sb.deleteCharAt(end);
                    sb.insert(end, '1');
                } else {
                    while (end > 0 && sb.charAt(end) == '1') {
                        sb.deleteCharAt(end);
                        sb.insert(end, '0');
                        end--;
                    }
                    if (end > 0) {
                        sb.deleteCharAt(end);
                        sb.insert(end, '1');
                    } else if (end == 0 && sb.charAt(0) == '1') {
                        sb.deleteCharAt(0);
                        sb.append('0');
                        sb.insert(0, '1');
                    }
                }

            }
            cnt++;
        }
        return cnt;
    }


    public String stoneGameIII(int[] stoneValue) {
        //corner case:
        if (stoneValue == null || stoneValue.length == 0) {
            return "Tie";
        }
        //dp[i] means the max score can get from position i
        //base case: dp[n - 1] = stonValue[n - 1];
        //induction rule dp[i] = Math.max(dp[i], suffix[i - j] - dp[i - j] + (suffix[i] - suffix[i - j]))
        int n = stoneValue.length;
        int[] suffix = new int[n];
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                suffix[i] = stoneValue[i];
            } else {
                suffix[i] = suffix[i + 1] + stoneValue[i];
            }
        }
        dp[n - 1] = stoneValue[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = suffix[i + 1] - dp[i + 1] + stoneValue[i];
            for (int j = i + 2; j < i + 4 && j <= n; j++) {
                if (j == n) {
                    dp[i] = Math.max(dp[i], suffix[i]);
                } else {
                    dp[i] = Math.max(dp[i], (suffix[j] - dp[j]) + (suffix[i] - suffix[j]));
                }
            }
        }
        return dp[0] * 2 == suffix[0] ? "Tie" : dp[0] * 2 > suffix[0] ? "Alice" : "Bob";
    }
}
