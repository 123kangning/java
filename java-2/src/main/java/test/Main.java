package test;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] num=new int[n];
        for(int i=0;i<n;i++){
            num[i]=scanner.nextInt();
        }
        for(int i=0;i<n;i++){
            int min=num[i],minIndex=i;
            for(int j=i+1;j<n;j++){
                if(num[j]<min){
                    min=num[j];
                    minIndex=j;
                }
            }
            if(i!=minIndex){
                num[minIndex]=num[i];
                num[i]=min;
            }
        }
        System.out.print(num[n-1]);
        for(int index=n-2;index>=0;index--){
            System.out.print(" "+num[index]);
        }
    }
}
