/**
 * Created by keyingzhou on 10/27/19.
 */
import java.util.*;
class CustomFunction {
     // Returns f(x, y) for any given positive integers x and y.
    // Note that f(x, y) is increasing with respect to both x and y.
    // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
   public int f(int x, int y) {
       return 0;
   };
};
public class Contest160 {

    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        //binary search:
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int x = 1; x <= 1000; x++) {
            int low = 0, high = 1000;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int ret = customfunction.f(x, mid);
                if (ret == z) {
                    res.add(Arrays.asList(x, mid));
                } else if (ret < z) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return res;
    }
}
