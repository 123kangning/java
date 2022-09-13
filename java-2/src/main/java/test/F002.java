package test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class F002 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n= scanner.nextInt(),limit=100001;
        Integer[] a=new Integer[n];
        for(int i=0;i<n;i++){
            a[i]= scanner.nextInt();
        }
        List<Integer> list= Arrays.stream(a).distinct().collect(Collectors.toList());
        System.out.print(list.get(0));
        int size=list.size();
        for(int i=1;i<size;i++){
            System.out.print(" "+list.get(i));
        }
    }
}
