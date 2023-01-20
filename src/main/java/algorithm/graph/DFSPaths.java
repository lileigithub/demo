package algorithm.graph;

import algorithm.stack.LinkedStack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DFSPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;//起点

    /**
     * 找到和起点 s 连通的所有顶点
     *
     * @param G 图
     * @param s
     */
    public DFSPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (Integer w : G.adj(v))
            if (!marked[w]) {
                /**为什么是w指向v呢，是因为edgeTo存储的是从s出发的一条路径，那么要找w和s是否连通，并且展示路径
                 就可以以w指向v，marked[v]== false不连通，如果w有值，那么最终会指向s。*/
                edgeTo[w] = v;
                dfs(G, w);
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
        DFSPaths search = new DFSPaths(G, s);
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
