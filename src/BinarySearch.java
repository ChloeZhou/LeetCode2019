/**
 * Created by keyingzhou on 10/28/18.
 */
public class BinarySearch {
    public int search(int target, int[] array) {
        //corner case:
        if (array == null || array.length == 0) {
            return -1;
        }
        int s = 0;
        int e = array.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (array[mid] == target) {
                return mid;
            }
            if (array[mid] > target) {
                e = mid;
            } else {
                s = mid;
            }
        }
        return -1;
    }
}
