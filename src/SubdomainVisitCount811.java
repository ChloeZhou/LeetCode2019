/**
 * Created by keyingzhou on 4/21/19.
 */
import java.util.*;
public class SubdomainVisitCount811 {
    public List<String> subdomainVisits(String[] cpdomains) {
        //corner case:
        List<String> result = new ArrayList<>();
        if (cpdomains == null || cpdomains.length == 0) {
            return result;
        }
        //
        Map<String, Integer> map = new HashMap<>();
        for (String s : cpdomains) {
            String[] splitMap = s.split(" ");
            int num = Integer.parseInt(splitMap[0]);
            String domains = splitMap[1];
            int i = 0;
            while (i < domains.length()) {
                if (i == 0 || domains.charAt(i - 1) == '.') {
                    String domain = domains.substring(i);
                    map.put(domain, map.getOrDefault(domain,0) + num);
                }
                i++;
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String ele = entry.getValue() + " " + entry.getKey();
            result.add(ele);
        }
        return result;
    }

}
