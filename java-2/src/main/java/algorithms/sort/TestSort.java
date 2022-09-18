package algorithms.sort;

import java.util.Random;

public class TestSort {
    public static void main(String[] args) {
        int N=10000;
        double[] a=new double[N];
        Random random=new Random();
        for (int i=0;i<N;i++){
            a[i]= random.nextDouble();
        }
        for(double e:a)System.out.printf(" %.2f",e);
        System.out.println();
        long t1=System.currentTimeMillis();
        Quick.sort(a);
        long t2=System.currentTimeMillis();
        for(double e:a)System.out.printf(" %.2f",e);
        System.out.println("\n耗费时间："+(t2-t1));
    }
}
