/**
 * Created by keyingzhou on 11/2/19.
 */
import java.util.*;
public class Contest161 {

    public int minimumSwap(String s1, String s2) {
        //corner case:
        if (s1.length() != s2.length()) {
            return -1;
        }
        int[] cnt = new int[2];
        int res = -1;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == 'x') {
                cnt[0]++;
            } else {
                cnt[1]++;
            }
            if (s2.charAt(i) == 'x') {
                cnt[0]++;
            } else {
                cnt[1]++;
            }
        }
        if (cnt[0] % 2 != 0 || cnt[1] % 2 != 0) {
            return -1;
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                String s = s1.charAt(i) + "" + s2.charAt(i);
                map.put(s, map.getOrDefault(s,0) + 1);
            }
        }
        res = map.getOrDefault("xy", 0) / 2 + map.getOrDefault("yx", 0) / 2;
        res += 2 * (map.getOrDefault("xy", 0) % 2);
        return res;

    }

    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder();
        int open = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else if (c == ')') {
                if (open == 0) continue;
                open--;
            }
            sb.append(c);
        }

        for (int i = sb.length() - 1; i >= 0 && open > 0; i--) {
            if (sb.charAt(i) == '(') {
                sb.deleteCharAt(i);
                open--;
            }
        }

        return sb.toString();
    }

    public int numberOfSubarrays(int[] nums, int k) {
        //corner case:
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //
        List<Integer> idx = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 != 0) {
                idx.add(i);
            }
        }
        //
        if (idx.size() < k) {
            return 0;
        }
        int res = 0;
        int start = 0;
        int end = k - 1;
        while (end < idx.size()) {
            int right = end + 1 < idx.size() ? idx.get(end + 1) : nums.length;
            int left = start - 1 >= 0 ? idx.get(start - 1) : 0;
            int leftNums = idx.get(start) - left + 1;
            int rightNums = right - idx.get(end);
            if (leftNums > 0 && rightNums > 0) {
                res += rightNums * leftNums;
            }
            end++;
            start++;
        }
        return res;
    }
}
