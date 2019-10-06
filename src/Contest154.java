/**
 * Created by keyingzhou on 9/14/19.
 */
import java.util.*;
public class Contest154 {
    public int maxNumberOfBalloons(String text) {
        //corner case:
        if (text == null || text.length() < 7) {
            return 0;
        }
        //
        Map<Character, Integer> ch = new HashMap<Character, Integer>();
        ch.put('b', 1);
        ch.put('a', 1);
        ch.put('l', 2);
        ch.put('o', 2);
        ch.put('n', 1);
        Map<Character, Integer> textMap = new HashMap<Character, Integer>();
        for (int i = 0; i < text.length(); i++) {
            if (ch.containsKey(text.charAt(i))) {
                textMap.put(text.charAt(i), textMap.getOrDefault(text.charAt(i), 0) + 1);
            }
        }
        int min = Integer.MAX_VALUE;
        for (Character item : textMap.keySet()){
            min = Math.min(min, textMap.get(item) / ch.get(item));
        }
        return min;
    }

    public String reverseParentheses(String s) {
        //corner case:
        if (s == null || s.length() <= 2) {
            return s;
        }
        //
        char[] sChar = s.toCharArray();
        String res = "";
        Deque<Integer> dq = new ArrayDeque<Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                dq.offerFirst(i);
            }
            if (s.charAt(i) == ')') {
                int st = dq.pollFirst();
                reverseString(sChar, st, i);
            }
        }
        for (int i = 0; i < sChar.length; i++) {
            if (sChar[i] != '(' && sChar[i] != ')') {
                res += sChar[i];
            }
        }
        return res;
    }

    private void reverseString(char[] s, int st, int e) {
        if (s == null || s.length <= 1) {
            return;
        }
        char temp = 'a';
        while (st <= e) {
            temp = s[st];
            s[st] = s[e];
            s[e] = temp;
            st++;
            e--;
        }
    }

    public int kConcatenationMaxSum(int[] arr, int k) {
        //coner case:
        if (arr == null || k == 0) {
            return 0;
        }
        //
        int mod  = (int) Math.pow(10,9)+7;
        long[] dp = k >= 2 ? new long[arr.length * 2] : new long[arr.length];
        int[] newArr = k >= 2 ? Arrays.copyOf(arr, arr.length + arr.length) : arr;
        if (newArr.length == 2*arr.length) {
            for (int i = 0; i < arr.length; i++) {
                newArr[i + arr.length] = arr[i];
            }
        }
        dp[0] = newArr[0];
        long res = dp[0];
        for (int i = 1; i < newArr.length; i++) {
            dp[i] = dp[i - 1] > 0 ? (dp[i - 1] + newArr[i])%mod : newArr[i];
            res = Math.max(res, dp[i]);
        }
        int n = arr.length;
        long[] dp2 = new long[n];
        dp2[n - 1] = arr[n - 1];
        long res2 = dp2[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            dp2[i] = (dp2[i + 1] + arr[i])%mod;
            res2 = Math.max(res2, dp2[i]);
        }
        long[] dp3 = new long[n];
        dp3[0] = arr[0];
        long res3 = dp3[0];
        for (int i = 1; i < arr.length; i++) {
            dp3[i] = (dp3[i - 1] + arr[i])%mod;
            res3 = Math.max(res3, dp3[i]);
        }

        long sum = 0;
        long res4 = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = (sum+arr[i])%mod;
        }
        if (sum > 0 && k > 2) {
            res4 = (sum * (k - 2))%mod;
            res4 += res2 + res3;
            return (int)(Math.max(res, res4));
        }
        return (int)(Math.max(res,0));
    }
}
