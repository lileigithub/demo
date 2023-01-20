package algorithm.graph;

import algorithm.search.SeparateChainingHashST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SymbolGraph {
    private boolean[] marked;
    private SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
    private String[] indexUn;//反向索引

    private Graph G;

    /**
     * 根据 filename 指定的文件构造图，使用 delim 来分隔顶点名
     *
     * @param filename
     * @param delim
     */
    public SymbolGraph(String filename, String delim) {
        In in = new In(filename);
        while (in.hasNextLine()){
            String[] split = in.readLine().split(delim);
            for (String s : split) {//构建散列表
                if(!st.contains(s)){
                    st.put(s, st.size());
                }
            }
        }
        indexUn = new String[st.size()];
        for (String key : st.keys()) {//构建反向索引
            indexUn[st.get(key)] = key;
        }
        //构建图
        in = new In(filename);
        G = new Graph(st.size());
        while(in.hasNextLine()){
            String[] split = in.readLine().split(delim);
            String movie = split[0];
            for (int i = 1; i < split.length ; i++) {
                G.addEdge(st.get(movie), st.get(split[i]));
            }
        }
    }

    /**
     * key是一个顶点吗
     *
     * @param key
     * @return
     */
    public boolean contains(String key) {
        return st.contains(key);
    }

    /**
     * key的索引
     *
     * @param key
     * @return
     */
    public int index(String key) {
        return st.get(key);
    }

    /**
     * 索引 v 的顶点名
     * @param v
     * @return
     */
    public String name(int v) {
        return indexUn[v];
    }

    /**
     * 隐藏的 Graph 对象
     * @return
     */
    public Graph G() {
        return G;
    }

    public static void main(String[] args)
    {
        String filename = args[0];
        String delim = args[1];
        SymbolGraph sg = new SymbolGraph(filename, delim);
        Graph G = sg.G();
        while (StdIn.hasNextLine())
        {
            String source = StdIn.readLine();
            for (int w : G.adj(sg.index(source)))
                StdOut.println(" " + sg.name(w));
        }
    }

}
