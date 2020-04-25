/**
 * Created by keyingzhou on 3/28/20.
 */
import java.util.*;
public class Contest182 {

    public int numTeams(int[] rating) {
        //corner case:
        if (rating == null || rating.length == 0) {
            return 0;
        }
        //
        int res = 0;
        //dp[i] means dp[i] numbers in the increasing sequence from 0th to ith num, must include i
        //dp: base case: dp[0] = 1;
        //induction rule: dp[i] = dp[j] + 1 ( 0 <= j < i)
        int[] dp = new int[rating.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (rating[i] > rating[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] >= 3) {
                res++;
            }
        }
        Arrays.fill(dp, 1);
        //dp[i] means dp[i] numbers in the decreasing sequence from 0th to ith num, must include i
        //dp: base case: dp[0] = 1;
        //induction rule: dp[i] = dp[j] + 1 ( 0 <= j < i)
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (rating[i] < rating[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] >= 3) {
                res++;
            }
        }
        return res;
    }

    public String minWindow(String source, String target) {
        // Write your solution here
        String res = "";
        //coner case:
        if (source.length() < target.length()) {
            return res;
        }
        //s and e pointer.
        // {A:1, B:1, C: 1}
        //
        int n = source.length();
        int slow = 0; int matchCount = 0; int idex = 0; int min = n + 1;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < target.length(); i++) {
            map.put(target.charAt(i), map.getOrDefault(target.charAt(i), 0) + 1);
        }
        for (int fast = 0; fast < n; fast++) {
            char cur = source.charAt(fast);
            if (!map.containsKey(cur)) {
                continue;
            }
            int count = map.get(cur);
            map.put(cur, count - 1);
            //if count match
            if (count == 1) {
                matchCount++;
            }
            //if all char match
            while (matchCount == map.size()) {
                if (min > fast - slow + 1) {
                    min = fast - slow + 1;
                    idex = slow;
                }
                if (map.containsKey(source.charAt(slow))) {
                    matchCount--;
                    map.put(source.charAt(slow), map.get(source.charAt(slow)) + 1);
                }
                slow++;
            }

        }
        return min == n + 1 ? res : source.substring(idex, idex + min);
    }
}
