package algorithm.digraph;

import algorithm.stack.LinkedBag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class KosarajuSCC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int i : order.reversePost()) {
            if (!marked[i]) {
                dfs(G, i);
                count++;
            }

        }
    }

    public void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (Integer w : G.adj(v))
            if (!marked[w]) dfs(G, w);
    }

    /**
     * v 和 w 连通吗
     * @param v
     * @param w
     * @return
     */
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    /**
     * 连通分量数
     * @return
     */
    public int count() {
        return count;
    }

    /**
     * v 所在的连通分量的标识符（0 ～ count()-1）
     * @param v
     * @return
     */
    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(new In(args[0]));
        KosarajuSCC cc = new KosarajuSCC(G);
        int M = cc.count();
        StdOut.println(M + " components");
        LinkedBag<Integer>[] components = (LinkedBag<Integer>[]) new LinkedBag[M];
        for (int i = 0; i < M; i++)
            components[i] = new LinkedBag<>();
        for (int v = 0; v < G.V(); v++)
            components[cc.id(v)].add(v);
        for (int i = 0; i < M; i++) {
            for (int v : components[i])
                StdOut.print(v + " ");
            StdOut.println();
        }
    }
}
