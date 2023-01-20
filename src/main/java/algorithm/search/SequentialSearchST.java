package algorithm.search;

import algorithm.queue.LinkedQueue;
import algorithm.stack.LinkedBag;
import sun.plugin.javascript.navig.Link;

/**
 * 不排序链表符号表
 * @author lilei
 * @date 2023/1/16 14:29
 * sequential [sɪˈkwenʃl] n.序列
 */
public class SequentialSearchST<Key, Value> {
    private Node first;
    private int N;


    private class Node {
        private final Key key;
        private Value val;
        private final Node next;

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
        N++;
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals((x.key))) return x.val;
        }
        return null;
    }

    public Iterable<Key> keys(){
        LinkedBag<Key> bag = new LinkedBag<>();
        for (Node x = first; x != null; x = x.next) {
            bag.add(x.key);
        }
        return bag;
    }

    public int size() {
        return N;
    }


    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        st.put("a", 1);
        st.put("b", 2);
        st.put("c", 3);
        st.put("d", 4);

        for (String key : st.keys()) {
            System.out.println(key);
        }
        System.out.println("size: " + st.size());

    }
}
