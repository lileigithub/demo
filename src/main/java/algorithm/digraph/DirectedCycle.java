package algorithm.digraph;

import algorithm.queue.LinkedQueue;
import algorithm.stack.LinkedStack;

public class DirectedCycle {
    private boolean[] marked;
    private boolean[] onStack;
    private int[] edgeTo;
    private LinkedStack<Integer> cycle;

    public DirectedCycle(Digraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            for (int w : G.adj(i)) {
                dfs(w, G);
            }
        }
    }

    private void dfs(int v, Digraph G) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : G.adj(v)) {
            if(hasCycle()) return;
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(w, G);
            } else if (onStack[w]) {
                cycle = new LinkedStack<>();
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

}
