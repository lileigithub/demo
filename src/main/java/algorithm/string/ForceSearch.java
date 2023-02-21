package algorithm.string;

/**
 * violence string lookup
 * @author lilei
 * @date 2023/2/21 16:56
 */
public class ForceSearch {
    /**
     * t=8
     * a b c d a b c c
     * a b c c
     * p=4
     * 8-pl=4
     * <p>
     * <p>
     * i 文本 j 模式
     * @param pat
     * @param txt
     * @return
     */
    public static int search(String pat, String txt) {
        int p, pl = pat.length();
        int t, tl = txt.length();
        for (t = 0, p = 0; t < tl && p < pl; t++) {
            if (txt.charAt(t) == pat.charAt(p)) {
                p++;
            } else {
                t -= p;
                p = 0;
            }
        }
        if (p == pl) {
            return t - pl;
        } else {
            return tl;
        }
    }
}
