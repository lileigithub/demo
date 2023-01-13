package algorithm.search;

import algorithm.queue.LinkedQueue;

/**
 * 红黑树
 * @author lilei
 * @date 2023/1/11 10:18
 */
public class RBST<Key extends Comparable<Key>, Value> {

    private Node root;

    private static boolean RED = true;
    private static boolean BLACK = false;

    private class Node {//内部结构，不暴露给外部。
        private Key key;//Key用不可变类型
        private Value val;
        private Node left, right;//左子 右子
        private int N; //以当前节点为根节点的树的节点总数
        private boolean color;//由其父节点指向它的链接的颜色

        public Node(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            N = n;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        else return x.color == true;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;

        boolean hColor = h.color;
        h.color = x.color;
        x.color = hColor;

        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;

        boolean hColor = h.color;
        h.color = x.color;
        x.color = hColor;

        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColor(Node x) {
        x.left.color = BLACK;
        x.right.color = BLACK;
        x.color = RED;
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
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1, RED);//空链接，则新增
        int compareTo = key.compareTo(x.key);
        if (compareTo < 0) x.left = put(x.left, key, val);
        else if (compareTo > 0) x.right = put(x.right, key, val);
        else x.val = val;

        /**
         * 在沿着插入点到根结点的路径向上移动时在所经过的每个结点中顺序完成以下操作，我们就能完成插入操作：
         * 1.如果右子结点是红色的而左子结点是黑色的，进行左旋转；
         * 2.如果左子结点是红色的且它的左子结点也是红色的，进行右旋转；
         * 3.如果左右子结点均为红色，进行颜色转换。
         */
        if (!isRed(x.left) && isRed(x.right)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColor(x);

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

    //TODO 删除好难，还没理解

    private Node moveRedLeft(Node h) {
        // 假设结点 h 为红色，h.left 和 h.left.left 都是黑色，
        // 将 h.left 或者 h.left 的子结点之一变红
        flipColorsReverse(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        flipColorsReverse(h);
        if (isRed(h.left.right)) {
            h.left = rotateLeft(h.left);
            h = rotateRight(h);
        }
        return h;
    }

    private void flipColorsReverse(Node h) {
        h.color = BLACK;
        h.left.color = RED;
        h.right.color = RED;
    }

    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;
        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return balance(h);
    }

    private Node balance(Node x) {
        if (isRed(x.right)) x = rotateLeft(x);
        if (!isRed(x.left) && isRed(x.right)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColor(x);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node delete(Node h, Key key) {
        if (h == null) return null;
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && h.right != null && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                h.val = get(h.right, min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            } else h.right = delete(h.right, key);
        }
        return balance(h);
    }

    private boolean isEmpty() {
        return root.N == 0;
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
        RBST<String, Integer> rbst = new RBST<>();
        rbst.put("s", 13);
        rbst.put("b", 11);
        rbst.put("a", 10);
        rbst.put("c", 12);
        rbst.delete("s");
        rbst.delete("a");
        rbst.printKeys();
    }

}
