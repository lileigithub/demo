package algorithm.stack;

import java.util.Iterator;

/**
 * 《算法 第4版》 算法1.1 容量可变的栈
 * @author lilei
 * @date 2022/12/22 9:53
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {

    private int N = 0;//栈的大小
    private Item[] a = (Item[]) new Object[1];

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean isFull() {
        return N == a.length;
    }

    public int size() {
        return N;
    }

    public void resize(int max) {
        Item[] newArray = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            newArray[i] = a[i];
        }
        a = newArray;
    }

    public void push(Item item) {
        if (N == a.length) {
            resize(a.length * 2);
        }
        a[N++] = item;
    }

    public Item pop() {
        if (isEmpty()) throw new UnsupportedOperationException("栈已空");
        Item pop = a[--N];
        a[N] = null;
        if (N > 0 && N <= a.length / 4) {
            resize(a.length / 2);
        }
        return pop;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseIterator();
    }

    private class ReverseIterator implements Iterator<Item> {
        //后进先出的迭代
        int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }
    }
}
