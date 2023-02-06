package algorithm.digraph;

/**
 * description
 * @author lilei
 * @date 2023/1/28 10:16
 */
public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph G) {
        DirectedCycle cycle = new DirectedCycle(G);
        if (!cycle.hasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);
            order = depthFirstOrder.reversePost();
        }
    }

    /**
     * G是有向无环图吗
     * @return
     */
    public boolean isDAG() {
        return order != null;
    }

    /**
     * 拓扑排序的所有顶点
     * @return
     */
    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String separator = args[1];
        SymbolDigraph sd = new SymbolDigraph(filename, separator);
        Topological topological = new Topological(sd.G());
        for (int v : topological.order)
            System.out.println(sd.name(v));
    }
}
