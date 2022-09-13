package test;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class F001 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int len=scanner.nextInt();
        Integer[] a=new Integer[len];
        for(int i=0;i<len;i++){
            a[i]=scanner.nextInt();
        }
        List<Integer> list=Arrays.stream(a).sorted().collect(Collectors.toList());
        int size=list.size();
        System.out.print(list.get(size-1));
        if(size>1){
            for(int i=size-2;i>=0;i--)
                System.out.print(" "+list.get(i));
        }
    }
}
