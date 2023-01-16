package algorithm.search;

import algorithm.queue.LinkedQueue;

/**
 * 线性探测的符号表
 * @author lilei
 * @date 2023/1/16 16:41
 */
public class LinearProbingHashST<Key, Value> {
    private int N;//符号表中键值对的总数
    private int M = 16;//线性探测表的大小
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public LinearProbingHashST(int x) {
        keys = (Key[]) new Object[x];
        vals = (Value[]) new Object[x];
        M = x;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;//这段代码会将符号位屏蔽（将一个 32 位整数变为一个 31 位非负整数），然后用除留余数法计算它除以 M 的余数。
    }

    private void resize(int x) {
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<>(x);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    public void put(Key key, Value val) {
        if (N >= M / 2) resize(M * 2);
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (key.equals(keys[i])) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (key.equals(keys[i])) return vals[i];
        return null;
    }

    public void delete(Key key) {
        if (!contains(key)) return;
        int i = hash(key);
        while (!keys[i].equals(key)) i = (i + 1) % M;//包含key，所以必找到i
        keys[i] = null;
        vals[i] = null;
        N--;
        for (i = (i + 1) % M; keys[i] != null; i = (i + 1) % M) {
            Key putKey = keys[i];
            Value putValue = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;//删除后重新put,注意N也减
            put(putKey, putValue);//如果直接设置i前一位的值，由于是循环的，处理太麻烦，这里put更简单。
        }
        if (N > 0 && N == M / 8) resize(M / 2);
    }

    public boolean contains(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key)) return true;
        return false;
    }

    public Iterable<Key> keys() {
        LinkedQueue<Key> queue = new LinkedQueue();
        for (int i = 0; i < M; i++)
            if (keys[i] != null) queue.enqueue(keys[i]);
        return queue;
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
        st.put("a", 1);
        st.put("b", 2);
        st.put("c", 3);
        st.put("d", 4);
        st.put("b", 5);

        System.out.println(st.get("a"));
        System.out.println(st.get("b"));
        System.out.println(st.get("c"));
        System.out.println(st.get("d"));
        System.out.println(st.get("e"));
        System.out.println(st.contains("a"));
        st.delete("c");
        for (String key : st.keys()) {
            System.out.println(key);
        }
    }

}
