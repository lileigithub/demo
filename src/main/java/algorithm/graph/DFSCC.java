package algorithm.graph;

import algorithm.stack.LinkedBag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DFSCC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public DFSCC(Graph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if(!marked[i]){
                dfs(G, i);
                count++;
            }

        }
    }

    public void dfs(Graph G, int v){
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
    public boolean Connected(int v, int w){
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
    public int id(int v){
        return id[v];
    }

    public static void main(String[] args)
    {
        Graph G = new Graph(new In(args[0]));
        DFSCC cc = new DFSCC(G);
        int M = cc.count();
        StdOut.println(M + " components");
        LinkedBag<Integer>[] components= (LinkedBag<Integer>[]) new LinkedBag[M];
        for (int i = 0; i < M; i++)
            components[i] = new LinkedBag<Integer>();
        for (int v = 0; v < G.V(); v++)
            components[cc.id(v)].add(v);
        for (int i = 0; i < M; i++)
        {
            for (int v: components[i])
                StdOut.print(v + " ");
            StdOut.println();
        }
    }
}
