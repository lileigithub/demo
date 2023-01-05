package algorithm.sort;

/**
 * description
 * @author lilei
 * @date 2022/12/28 10:06
 */
public class MergeBU extends SortExample {
    private static Comparable[] aux;

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {//复制数组
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz += sz) {
            for (int i = 0; i < N; i += sz + sz) {
                merge(a, i, i + sz - 1, Math.min(i + sz + sz - 1, N - 1));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(isSorted(MergeBU::sort, 1000, 100));
    }
}
