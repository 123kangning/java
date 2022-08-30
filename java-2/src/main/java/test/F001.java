package test;

import java.util.Scanner;

public class F001 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt(),count=1;
        for(;n>0;n--){
            for(int i=0;i<n;i++){
                System.out.printf("%4d",count++);
            }System.out.println();
        }
    }
}
