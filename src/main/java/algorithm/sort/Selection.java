package algorithm.sort;

/**
 * 选择排序
 * 选择最小的交换
 * 0 到 N-1 的任意 i 都会进行一次交换和 N-1-i 次比较，
 * 因此总共有 N次交换以及 (N-1)+(N-2)+...+2+1=N(N-1)/2 ～ N2/2 次比较。
 * @author lilei
 * @date 2022/12/26 15:52
 */
public class Selection extends SortExample {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{9, 4, 2, 3, 6, 2, 8};
        sort(a);
        show(a);
    }
}
