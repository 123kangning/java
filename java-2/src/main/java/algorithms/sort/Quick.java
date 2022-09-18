package algorithms.sort;

public class Quick {
    public static void sort(double[] a){
        sort(a,0, a.length-1);
    }
    private static void sort(double[] a,int low,int high){
        if(low>=high)return;
        int j=partition(a,low,high);
        sort(a,low,j-1);
        sort(a,j+1,high);
    }
    public static int partition(double[] a,int low,int high){
        int i=low,j=high+1;
        double v=a[low];
        while(true){
            while(a[++i]<v)if(i==high)break;
            while(a[--j]>v)if(j==low)break;
            if(i>=j)break;
            Common.exCh(a,i,j);
        }
        Common.exCh(a,low,j);//将v=a[j]放入合适的位置
        return j;
    }
}
