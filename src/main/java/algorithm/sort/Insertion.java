package algorithm.sort;

/**
 * 插入排序
 * @author lilei
 * @date 2022/12/26 17:55
 */
public class Insertion extends SortExample {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j - 1, j);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{9, 4, 2, 3, 6, 2, 8};
        sort(a);
        show(a);
    }
}
