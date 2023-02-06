package algorithm.digraph;

import algorithm.queue.LinkedQueue;
import algorithm.stack.LinkedStack;

/**
 * description
 * @author lilei
 * @date 2023/1/28 10:22
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private LinkedQueue<Integer> pre;
    private LinkedQueue<Integer> post;
    private LinkedStack<Integer> reversePost;

    public DepthFirstOrder(Digraph G) {
        marked = new boolean[G.V()];
        pre = new LinkedQueue<>();
        post = new LinkedQueue<>();
        reversePost = new LinkedStack<>();
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) dfs(G, i);
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        pre.enqueue(v);
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
        post.enqueue(v);
        reversePost.push(v);//逆后序
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
