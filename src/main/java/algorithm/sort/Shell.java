package algorithm.sort;

/**
 * 希尔排序（优化的插入排序）
 * @author lilei
 * @date 2022/12/27 11:04
 */
public class Shell extends SortExample {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;//步长
        while (h < N / 3) h = h * 3 + 1;//步长序列 1 4 13 40
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        System.out.println(isSorted(Shell::sort, 100, 100));
    }
}
