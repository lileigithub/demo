package algorithm.search;

/**
 * 符号表 二分法查找
 * @author lilei
 * @date 2023/1/4 18:11
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 小于指定键key的键的数量
     * 二分法查找
     * @param key
     * @return
     */
    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int i = keys[mid].compareTo(key);
            if (i > 0) hi = mid - 1;
            else if (i < 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) //有键
            return vals[i];
        return null;
    }

    public void put(Key key, Value val) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {//有键
            vals[i] = val;
            return;
        }
        for (int j = N; i < j; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public static void main(String[] args) {
        BinarySearchST<String, Integer> binarySearchST = new BinarySearchST<>(100);
        binarySearchST.put("s", 13);
        binarySearchST.put("b", 11);
        binarySearchST.put("a", 10);
        binarySearchST.put("c", 12);
        System.out.println(binarySearchST.get("s"));
    }
}
