package algorithm.search;

import algorithm.queue.LinkedQueue;

/**
 * 二叉查找树
 * @author lilei
 * @date 2023/1/11 10:18
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {//内部结构，不暴露给外部。
        private Key key;//Key用不可变类型
        private Value val;
        private Node left, right;//左子 右子
        private int N; //以当前节点为根节点的树的节点总数

        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            N = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        else return node.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        //对比key，小于->get左子树，大于->get右子树
        if (x == null) return null;
        Key curKey = x.key;
        int compareTo = key.compareTo(curKey);
        if (compareTo < 0) return get(x.left, key);
        else if (compareTo > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);//空链接，则新增
        int compareTo = key.compareTo(x.key);
        if (compareTo < 0) x.left = put(x.left, key, val);
        else if (compareTo > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return min(x.right);
    }

    /**
     * 小于等于 key 的最大键
     * @param key
     * @return
     */
    public Key floor(Key key) {
        Node floor = floor(root, key);
        if (floor == null) return null;
        return floor.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int compareTo = key.compareTo(x.key);
        if (compareTo == 0) return x;
        else if (compareTo < 0) return floor(x.left, key);
        Node floor = floor(x.right, key);
        if (floor == null) return x;
        return floor;
    }

    /**
     * 排名:找出小于指定键的键的数量
     * @param key
     * @return
     */
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int compareTo = key.compareTo(x.key);
        if (compareTo == 0) return size(x.left);
        else if (compareTo < 0) return rank(x.left, key);
        else return rank(x.right, key) + size(x.left) + 1;
    }

    /**
     * 找出排名为k的键
     */
    public Key select(int k) {
        return select(root, k);
    }

    private Key select(Node x, int k) {
        if (x == null) return null;
        int leftSize = size(x.left);
        if (k == leftSize) return x.key;
        else if (k < leftSize) return select(x.left, k);
        else return select(x.right, k - leftSize - 1);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;//如果x.left==null, 用右子节点代替x，且x的N就是x.right的N了，所以不用再相加
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    /**
     * 删除x里的键为key的节点，返回处理后的x
     * @param x
     * @param key
     * @return
     */
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int compareTo = key.compareTo(x.key);
        if (compareTo < 0) x.left = delete(x.left, key);
        else if (compareTo > 0) x.right = delete(x.right, key);
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;
            Node t = x;
            x = min(t.right);
            x.left = t.left;
            x.right = deleteMin(t.right);
        }
        x.N = size(x.right) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable keys(Key lo, Key hi) {
        LinkedQueue<Key> queue = new LinkedQueue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    public void keys(Node x, LinkedQueue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int loCmp = lo.compareTo(x.key);
        int hiCmp = hi.compareTo(x.key);
        if (loCmp < 0) keys(x.left, queue, lo, hi);
        if (loCmp <= 0 && hiCmp >= 0) queue.enqueue(x.key);
        if (hiCmp > 0) keys(x.right, queue, lo, hi);
    }

    public void printKeys() {//中序遍历
        printKeys(root);
    }

    private void printKeys(Node x) {
        if (x == null) return;
        printKeys(x.left);
        System.out.println(x.key);
        printKeys(x.right);
    }

    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>();
        bst.put("s", 13);
        bst.put("b", 11);
        bst.put("a", 10);
        bst.put("c", 12);
        System.out.println(bst.get("c"));
        System.out.println(bst.size());
        bst.printKeys();
        System.out.println("min: " + bst.min());
        System.out.println(bst.floor("y"));
        System.out.println(bst.rank("h"));
        System.out.println(bst.select(5));
        /*bst.deleteMin();
        System.out.println("min: " + bst.min());
        System.out.println(bst.size());
        bst.deleteMin();
        System.out.println("min: " + bst.min());
        System.out.println(bst.size());*/
        /*bst.delete("s");
        System.out.println(bst.get("a"));
        bst.printKeys();*/
        Iterable<String> keys = bst.keys("s", "a");
        System.out.println("keys:");
        for (String key : keys) {
            System.out.println(key);
        }
    }

}
