package algorithm.queue;

import java.util.Iterator;

/**
 * 算法1.3 队列（链表实现）
 * @author lilei
 * @date 2022/12/22 15:32
 */
public class LinkedQueue<Item> implements Iterable<Item> {

    private int N = 0;

    private Node first;//队头

    private Node last;//队尾

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        //队尾进
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
        N++;
    }

    public Item dequeue() {
        //队头出
        if (isEmpty()) throw new UnsupportedOperationException("队列为空");
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return item;
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
