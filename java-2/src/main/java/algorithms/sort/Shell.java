package algorithms.sort;

import java.util.Random;

public class Shell {
    public static void sort(double[] a){
        int N=a.length;
        int h=1;
        while(h<N/3){
            h=3*h+1;//1,4,13,40...
        }
        while(h>=1){
            for(int i=h;i<N;i++){
                for(int j=i;j>=h&&a[j]<a[j-h];j-=h){
                    exCh(a,j,j-h);
                }
            }
            h/=3;
        }
    }
    public static void exCh(double[] a ,int in1,int in2){
        double t=a[in1];
        a[in1]=a[in2];
        a[in2]=t;
    }

    public static void main(String[] args) {
        int N=1000;
        double[] a=new double[N];
        Random random=new Random();
        for (int i=0;i<N;i++){
            a[i]= random.nextDouble();
        }
        for(double e:a)System.out.printf(" %.2f",e);
        System.out.println();
        sort(a);
        for(double e:a)System.out.printf(" %.2f",e);
    }

}
