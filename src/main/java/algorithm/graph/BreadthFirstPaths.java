package algorithm.graph;

import algorithm.queue.LinkedQueue;
import algorithm.stack.LinkedStack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;//起点

    public BreadthFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        queue.enqueue(s);
        marked[s] = true;
        while (!queue.isEmpty()){
            Integer v = queue.dequeue();
            for (int w : G.adj(v)){
                if(!marked[w]){
                    edgeTo[w] = v;
                    queue.enqueue(w);
                    marked[w] = true;
                }
            }
        }

    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        LinkedStack<Integer> stack = new LinkedStack<>();
        while (v != s) {
            stack.push(v);
            v = edgeTo[v];
        }
        stack.push(s);
        return stack;
    }

    public static void main(String[] args)
    {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        BreadthFirstPaths search = new BreadthFirstPaths(G, s);
        for (int v = 0; v < G.V(); v++)
        {
            StdOut.print(s + " to " + v + ": ");
            if (search.hasPathTo(v))
                for (int x : search.pathTo(v))
                    if (x == s) StdOut.print(x);
                    else StdOut.print("-" + x);
            StdOut.println();
        }
    }
}
