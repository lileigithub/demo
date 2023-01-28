package algorithm.digraph;

import algorithm.queue.LinkedQueue;
import edu.princeton.cs.algs4.In;

public class Digraph {
    private int V;//顶点数
    private int E;//边数
    private LinkedQueue<Integer>[] adj;

    public Digraph(int V) {
        adj = (LinkedQueue<Integer>[]) new LinkedQueue[V];//这里只是创建了这样对象类型的数组，并没有设置每个单元格的值
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedQueue<>();
        }
        this.V = V;
    }

    public Digraph(In in) {
        this(in.readInt());
        int edge = in.readInt();
        for (int i = 0; i < edge; i++) {
            addEdge(in.readInt(), in.readInt());
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].enqueue(w);
        E++;
    }

    /**
     * 和v相邻的所有顶点
     *
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("vertices: " + V + "\n");
        out.append("edges: " + E + "\n");
        for (int i = 0; i < adj.length; i++) {
            for (Object o : adj[i]) {
                out.append(i + " : " + o + "\n");
            }
        }
        return out.toString();
    }

    public static int degree(Digraph G, int v) {
        int degree = 0;
        for (Integer integer : G.adj(v)) degree++;
        return degree;
    }

    /**
     * 该图的反向图
     * 它返回该有向图的一个副本，但将其中所有边的方向反转。
     * 这样就可以找出“指向”每个顶点的所有边
     *
     * @return
     */
    public Digraph reverse() {
        Digraph digraph = new Digraph(V);
        for (int i = 0; i < V; i++) {
            for (int w : adj(i)) {
                digraph.addEdge(i, w);
            }
        }
        return digraph;
    }

}
