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
                    Common.exCh(a,j,j-h);
                }
            }
            h/=3;
        }
    }

}
