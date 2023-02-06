package algorithm.digraph;

/**
 * 顶点对的可达性
 * @author lilei
 * @date 2023/1/28 14:28
 */
public class TransitiveClosure {
    private DirectedDFS[] all;

    TransitiveClosure(Digraph G) {
        all = new DirectedDFS[G.V()];
        for (int v = 0; v < G.V(); v++)
            all[v] = new DirectedDFS(G, v);
    }

    /**
     * w 是从 v 可达的吗
     * 或者说 v -> w是否存在
     * @param v
     * @param w
     * @return
     */
    public boolean reachable(int v, int w) {
        return all[v].marked(w);
    }
}
