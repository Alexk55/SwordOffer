/**
 * Created by Alexk on 2017/5/9.
 */
import java.util.*;

public class Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (input.length < k) {
            return list;
        }
        findKMin(input, 0, input.length - 1, k);
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }

    public void findKMin(int[] a, int begin, int end, int k) {
        if (begin < end) {
            int pos = partition(a, begin, end);
            if (pos == k - 1) {
                return;
            } else if (pos < k - 1) {
                findKMin(a, pos + 1, end, k);
            } else {
                findKMin(a, begin, pos - 1, k);
            }
        }
    }
    public int partition(int[] a, int begin, int end) {
        int pivot = a[begin];
        while (begin < end) {
            while (begin < end && a[end] >= pivot) {
                end--;
            }
            a[begin] = a[end];
            while (begin < end && a[begin] <= pivot) {
                begin++;
            }
            a[end] = a[begin];
        }
        a[begin] = pivot;
        return begin;
    }
}