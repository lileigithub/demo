package algorithm.search;

/**
 * 拉链法散列表
 * @author lilei
 * @date 2023/1/16 14:27
 * separate [sepəreɪt] v. 分开
 */
public class SeparateChainingHashST<Key, Value> {
    private int N;//键值对总数
    private int M;//散列表的大小
    private SequentialSearchST<Key, Value>[] st;

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
        st[hash(key)].put(key, val);
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
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
    }

}
