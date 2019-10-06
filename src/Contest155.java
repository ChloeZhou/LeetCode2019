/**
 * Created by keyingzhou on 9/21/19.
 */
import java.util.*;
public class Contest155 {

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> res = new ArrayList<List<Integer>> ();
        //corner case:
        if (arr == null || arr.length <= 1) {
            return res;
        }
        //
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        Set<Integer> idx = new TreeSet<Integer>();
        for (int i = 0; i < arr.length - 1; i++) {
            int cur = arr[i + 1] - arr[i];
            if (cur < min) {
                idx.clear();
                idx.add(i);
                min = cur;
            } else if (cur == min) {
                idx.add(i);
            }
        }
        for (int arrIdx : idx) {
            List<Integer> re = new ArrayList<Integer>();
            re.add(arr[arrIdx]);
            re.add(arr[arrIdx + 1]);
            res.add(re);
        }
        return res;
    }

    private long gcd (long a, long b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
    private long lcm (long a, long b) {
        return a * b / gcd(a, b);
    }

    private int count (int num, int a, int b, int c) {
        return (int) (num / a + num / b + num / c +
                        - lcm(a, b) - lcm(a, c) - lcm(b, c)
                        + lcm(a, lcm(b, c)));
    }
    public int nthUglyNumber(int n, int a, int b, int c) {
        int start = a, end = Integer.MAX_VALUE;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int co = count(mid, a, b, c);
            if (co==n) {
                return mid;
            } else if (co < n) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        //corner case:
        if (s == null || s.length() <= 1 || pairs == null || pairs.size() == 0) {
            return s;
        }
        //
        char[] sArray = s.toCharArray();
        Map<Integer, List<Character>> c = new HashMap<>();
        UnionFind uf = new UnionFind(s.length());
        for (List<Integer> pair : pairs) {
            int a  = pair.get(0);
            int b  = pair.get(1);
            uf.union(a, b);
        }

        for (int i = 0; i < s.length(); i++) {
            int head = uf.find(i);
            List<Character> chs = c.computeIfAbsent(head, (dummy) -> new ArrayList<>());
            chs.add(sArray[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int head = uf.find(i);
            List<Character> chs = c.get(head);
            sb.append(chs.get(0));
            chs.remove(0);
        }
        return sb.toString();
    }


}
