package bucket.novice;

public class Container {
    private Container[] g;//一组相连的容器
    private int n;//容器大小
    private double x;//该容器的水量

    public Container() {
        g = new Container[1000];
        g[0] = this;
        n = 1;
        x = 0;
    }

    public double getAmount() {
        return x;
    }

    public void addWater(double x) {
        double y = x / n;
        for (Container c : g) {
            c.x += y;
        }
    }

    public void connectTo(Container c) {
        double z = (x * n + c.x * c.n) / (n + c.n);  // 合并后，每个容器的水量

        for (int i = 0; i < n; i++)       // 遍历第一组中的每个容器
            for (int j = 0; j < c.n; j++) {   // 遍历第二组中的每个容器
                g[i].g[n + j] = c.g[j];     // 将c.g[j]添加到g[i]组中
                c.g[j].g[c.n + i] = g[i];   // 将g[i]添加到c.g[j]组中
            }

        n += c.n;

        for (int i = 0; i < n; i++) {  // 更新大小和水量
            g[i].n = n;
            g[i].x = z;
        }
    }


}
