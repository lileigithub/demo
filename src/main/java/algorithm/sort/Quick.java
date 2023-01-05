package algorithm.sort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * description
 * @author lilei
 * @date 2022/12/28 15:32
 */
public class Quick extends SortExample {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;//注意 +1
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;//要--j，先运算，后赋值，这样对比和交换的就是当前指针的位置。
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        System.out.println(isSorted(Quick::sort, 100, 100));
    }
}
