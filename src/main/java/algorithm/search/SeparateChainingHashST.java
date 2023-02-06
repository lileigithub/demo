package algorithm.search;

import algorithm.queue.LinkedQueue;

/**
 * 拉链法散列表
 * @author lilei
 * @date 2023/1/16 14:27
 * separate [sepəreɪt] v. 分开
 */
public class SeparateChainingHashST<Key, Value> {
    private int N;//键值对总数
    private final int M;//散列表的大小
    private final SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];//在创建 st[] 时需要进行类型转换，因为 Java 不允许泛型的数组
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;//这段代码会将符号位屏蔽（将一个 32 位整数变为一个 31 位非负整数），然后用除留余数法计算它除以 M 的余数。
    }

    public void put(Key key, Value val) {
        if (!contains(key)) N++;
        st[hash(key)].put(key, val);
    }

    public Value get(Key key) {
        if (st[hash(key)] == null) return null;
        return st[hash(key)].get(key);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Iterable<Key> keys() {
        LinkedQueue<Key> queue = new LinkedQueue<>();
        for (SequentialSearchST<Key, Value> kv : st) {
            Iterable<Key> keys = kv.keys();
            for (Key key : keys) {
                queue.enqueue(key);
            }
        }
        return queue;
    }

    public int size() {
        return N;
    }

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
        st.put("Brown, Bryan (I)", 1);
        st.put("Henderson, Dick (II)", 2);
        st.put("Gray, Ian (I)", 3);
        st.put("Woodward, Edward", 4);

        for (String key : st.keys()) {
            System.out.println(key);
        }
    }

}
