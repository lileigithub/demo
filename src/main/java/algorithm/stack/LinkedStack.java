package algorithm.stack;

import java.util.Iterator;

/**
 * 算法1.2 栈 链表实现
 * @author lilei
 * @date 2022/12/22 15:33
 */
public class LinkedStack<Item> implements Iterable<Item> {
    private int N = 0;//栈的大小

    private Node first;//栈顶

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }


    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Item pop() {
        if (isEmpty()) throw new UnsupportedOperationException("栈为空");
        Item pop = first.item;
        first = first.next;
        N--;
        return pop;
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
