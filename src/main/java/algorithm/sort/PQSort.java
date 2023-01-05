package algorithm.sort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * 堆排序
 * @author lilei
 * @date 2023/1/3 18:16
 */
public class PQSort extends SortExample {
    public static Comparable[] sort(Comparable[] a) {
        a = Arrays.copyOf(a, a.length + 1);
        int N = a.length - 1;
        a[N] = a[0];
        a[0] = null;
        //堆有序
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }
        //排序结果
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
        return Arrays.copyOfRange(a, 1, a.length);
    }

    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a[j], a[j + 1])) j++;
            if (!less(a[k], a[j])) break;
            exch(a, k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        System.out.println(isSorted(100, 10));
    }

    public static boolean isSorted(int N, int T) {
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniformDouble();
            }
            a = (Double[]) PQSort.sort(a);
            //show(a);
            if (!SortExample.isSorted(a)) return false;
        }
        return true;
    }
}
