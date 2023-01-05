package algorithm.stack;

/**
 * description
 * @author lilei
 * @date 2022/12/22 10:28
 */
public class Test {
    public static void main(String[] args) {
        LinkedStack<String> objects = new LinkedStack<>();
        objects.push("a");
        objects.push("b");
        objects.push("c");
        System.out.println(objects.pop());
        objects.push("d");
        objects.push("e");
        objects.push("f");
        objects.push("g");
        System.out.println(objects.pop());
        System.out.println(objects.pop());
        System.out.println(objects.size());
        for (String object : objects) {
            System.out.println(object);
        }
        System.out.println(objects.pop());
        System.out.println(objects.pop());
        System.out.println(objects.pop());
        System.out.println(objects.pop());
        System.out.println(objects.pop());

    }
}
