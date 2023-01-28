package algorithm.stack;

import java.util.Iterator;

/**
 * 算法1.4 包 链表实现
 * 这种实现方式其实是个只能进不能出的 特殊的栈
 * @author lilei
 * @date 2022/12/22 15:33
 */
public class LinkedBag<Item> implements Iterable<Item> {
    private int N = 0;

    private Node first;//链表首节点

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }


    public void add(Item item) {
        //和 Stack 的 push() 方法完全相同
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Item> {
        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private class Node {
        Item item;
        Node next;
    }
}
