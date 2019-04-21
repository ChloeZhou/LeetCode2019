/**
 * Created by keyingzhou on 10/28/18.
 */
public class QuickSort {
    public void quickSort(int[] input) {
        //corner case:
        if (input == null || input.length <= 1) {
            return;
        }
        quickSort(input, 0, input.length - 1);
    }

    private void quickSort(int[] input, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivotIdx = partition(input, left, right);
        quickSort(input, left, pivotIdx - 1);
        quickSort(input, pivotIdx + 1, right);
    }

    private int partition(int[] input, int left, int right) {
        int pivotIdx = left + (int) Math.random() * (right - left + 1);
        int pivot = input[pivotIdx];
        swap(input, pivotIdx, right);
        int start = left;
        int end = right - 1;
        while (start <= end) {
            if (input[end] >= pivot) {
                end--;
            }else if (input[start] < pivot) {
                start++;
            } else {
                swap(input, start++, end--);
            }
        }//start > end
        swap(input, start, right);
        return start;
    }

    private void swap(int[] input, int a, int b) {
        int temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }
}
