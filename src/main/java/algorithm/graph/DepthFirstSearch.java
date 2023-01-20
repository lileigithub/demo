package algorithm.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstSearch {
    private final boolean[] marked;
    private int count;

    /**
     * 找到和起点 s 连通的所有顶点
     *
     * @param G 图
     * @param s
     */
    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int s) {
        marked[s] = true;
        count++;
        for (Integer v : G.adj(s))
            if (!marked[v]) dfs(G, v);
    }

    /**
     * v 和 s 是连通的吗
     *
     * @param v
     * @return
     */
    public boolean marked(int v) {
        return marked[v];
    }

    /**
     * 与 s 连通的顶点总数(包含自身)
     *
     * @return
     */
    public int count() {
        return count;
    }

    public static void main(String[] args)
    {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch search = new DepthFirstSearch(G, s);
        for (int v = 0; v < G.V(); v++)
            if (search.marked(v))
                StdOut.print(v + " ");
        StdOut.println();
        if (search.count() != G.V())
            StdOut.print("NOT ");
        StdOut.println("connected");
    }
}
