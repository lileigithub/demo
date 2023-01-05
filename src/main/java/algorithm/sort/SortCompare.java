package algorithm.sort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * description
 * @author lilei
 * @date 2022/12/26 18:01
 */
public class SortCompare {
    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        if ("Insertion".equals(alg)) Insertion.sort(a);
        if ("Selection".equals(alg)) Selection.sort(a);
        if ("Shell".equals(alg)) Shell.sort(a);
        if ("Merge".equals(alg)) Merge.sort(a);
        if ("MergeBU".equals(alg)) MergeBU.sort(a);
        if ("Quick".equals(alg)) Quick.sort(a);
        if ("Quick3way".equals(alg)) Quick3way.sort(a);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniformDouble();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        int N = 4000;
        int T = 1000;
        String alg1 = "Quick";
        String alg2 = "Quick3way";
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        System.out.println(alg1 + " " + t1);
        System.out.println(alg2 + " " + t2);
        StdOut.printf("For %d random Doubles\n %s is", 1000, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
    }

}
