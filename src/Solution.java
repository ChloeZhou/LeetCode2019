

import java.util.*;
import java.util.LinkedList;

/**
 * Created by keyingzhou on 6/14/17.
 */
/*
第二题，给两个array,一个storese, 一个houses,要求输出每个house最近的stores的位置，store和house都有重复。我用的Binary Search。
*/
//[3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4
//                                                                  []
//                                      [1]                                                     []
//         [1, 2]                                                   [1]                     [2]                             []
//  [1, 2, 3]                       [1, 2]                  [1, 3]          [1]
// [1, 2, 3, 4] [1, 2, 3]   [1, 2, 4]      [1, 2]     [1, 3, 4]  [1, 3]   [1]  [1, 4]
class Solution {
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



/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */


