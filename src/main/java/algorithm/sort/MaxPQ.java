package algorithm.sort;

/**
 * 最大元素优先队列
 * @author lilei
 * @date 2023/1/3 16:34
 */
public class MaxPQ<Key extends Comparable<Key>> {
    Key[] pq;
    int N = 0;

    public MaxPQ() {
        pq = (Key[]) new Comparable[1025];
    }

    public MaxPQ(int max) {
        pq = (Key[]) new Comparable[max + 1];
    }


    public MaxPQ(Key[] a) {
        pq = a;
    }

    public void insert(Key v) {
        if (N == pq.length) throw new UnsupportedOperationException("队列已满");
        pq[++N] = v;
        swim(N);
    }

    public Key max() {
        return pq[1];
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N);
        pq[N--] = null;
        sink(1);
        return max;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void show() {
        for (int i = 1; i <= N; i++) {
            System.out.println(i + "-" + pq[i]);
        }
    }


    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int v, int w) {
        return pq[v].compareTo(pq[w]) < 0;
    }

    private void exch(int v, int w) {
        Key i = pq[v];
        pq[v] = pq[w];
        pq[w] = i;
    }

    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQ<>();
        maxPQ.insert(5);
        maxPQ.insert(2);
        maxPQ.insert(1);
        maxPQ.insert(8);
        maxPQ.insert(7);
        maxPQ.insert(12);
        maxPQ.insert(10);
        maxPQ.show();
    }


}
