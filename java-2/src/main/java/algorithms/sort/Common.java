package algorithms.sort;

public class Common {
    public static void exCh(double[] a ,int in1,int in2){
        double t=a[in1];
        a[in1]=a[in2];
        a[in2]=t;
    }
}
