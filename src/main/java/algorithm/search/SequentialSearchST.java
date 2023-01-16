package algorithm.search;

/**
 * 无序链表符号表
 * @author lilei
 * @date 2023/1/16 14:29
 * sequential [sɪˈkwenʃl] n.序列
 */
public class SequentialSearchST<Key, Value> {
    private Node first;
    private int N;


    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public void put(Key key, Value value) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals((x.key))) {
                x.val = value;
                return;
            }
        }
        first = new Node(key, value, first);
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals((x.key))) return x.val;
        }
        return null;
    }


    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
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
