/**
 * Created by keyingzhou on 2/8/20.
 */
import java.util.*;
public class Contest175 {
    public int minSteps(String s, String t) {
        //coner case:
        if (s.length() != t.length()) {
            return -1;
        }
        //
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int cnt = 0;
        for (int i = 0; i < t.length(); i++) {
            int num = map.getOrDefault(t.charAt(i), 0);
            if (map.containsKey(t.charAt(i)) && map.get(t.charAt(i)) > 0) {
                map.put(t.charAt(i), num - 1);
            } else {
                cnt++;
            }
        }
        int sum = 0;
        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            sum += e.getValue();
        }

        return cnt + (sum - cnt);
    }
}
