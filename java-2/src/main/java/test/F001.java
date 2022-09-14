package test;

import java.util.Scanner;

public class F001 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n= scanner.nextInt(),m= scanner.nextInt()%n,len=n;
        int[] num=new int[n];

        for(int i=0;i<n;i++){
            num[i]= i;
        }
        int index=-1;
        while(n>1){//n=size
            //System.out.println("m = "+m+" n = "+n);
            for(int i=0;i<m;i++){
                if(++index>=len){
                    index%=len;
                }
                if(num[index]==0){
                    i--;
                }
//                System.out.println("index = "+index);
            }
            System.out.printf("%d ",index+1);
            num[index]=0;
            n--;

        }
        for(int i=0;i<len;i++){
            if(num[i]>0){
                System.out.printf("%d ",i+1);
            }
        }
    }
}
