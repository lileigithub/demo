package algorithm.sort;

/**
 * 三向切分的快速排序
 * @author lilei
 * @date 2022/12/30 14:44
 */
public class Quick3way extends SortExample {

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int compareTo = a[i].compareTo(v);
            if (compareTo < 0) exch(a, i++, lt++);
            else if (compareTo > 0) exch(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    public static void main(String[] args) {
        System.out.println(isSorted(Quick3way::sort, 100, 10));
    }
}
