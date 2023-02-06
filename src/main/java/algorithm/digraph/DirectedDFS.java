package algorithm.digraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * directed Graph accessibility
 * 判断从给定的一个或者一组顶点能到达哪些其他顶点。
 * @author lilei
 * @date 2023/1/28 9:28
 */
public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int s : sources) {
            if (!marked[s]) dfs(G, s);
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(new In(args[0]));
        Bag<Integer> sources = new Bag<>();
        for (int i = 1; i < args.length; i++) {
            sources.add(Integer.parseInt(args[i]));
        }
        DirectedDFS dfs = new DirectedDFS(G, sources);
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v)) System.out.print(v + " ");
        }
        System.out.println();

    }
}
