package algorithm.queue;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * description
 * @author lilei
 * @date 2022/12/22 10:28
 */
public class Test {
    public static void main(String[] args) {
        /*LinkedQueue<String> objects = new LinkedQueue<>();
        objects.enqueue("a");
        objects.enqueue("b");
        objects.enqueue("c");
        System.out.println(objects.dequeue());
        objects.enqueue("d");
        objects.enqueue("e");
        objects.enqueue("f");
        objects.enqueue("g");
        System.out.println(objects.dequeue());
        System.out.println(objects.dequeue());
        System.out.println(objects.size());
        for (String object : objects) {
            System.out.println(object);
        }
        System.out.println(objects.dequeue());
        System.out.println(objects.dequeue());
        System.out.println(objects.dequeue());
        System.out.println(objects.dequeue());
        System.out.println(objects.dequeue());*/
        LinkedQueue<String> s = new LinkedQueue();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                s.enqueue(item);
            else if (!s.isEmpty()) StdOut.print(s.dequeue() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");

    }
}
