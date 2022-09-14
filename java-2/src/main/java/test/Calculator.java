package test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.nextLine();

        s = getRealNumber(s);
        List<List<Integer>> list1=init(Integer.parseInt(s),scanner.nextLine());
        dao(list1);
        show(list1);
    }
    public static void dao(List<List<Integer>> list){//求导
        for(List<Integer> l1:list){
            l1.set(0,l1.get(0)*l1.get(1));
            l1.set(1,l1.get(1)-1);
        }
    }
    public static int exc(List<List<Integer>> list,double x){
        int ans=0;
        for(List<Integer> l1:list){
            ans+=l1.get(0)*Math.pow(x,l1.get(1));
        }
        return ans;
    }
    public static List<List<Integer>> mul(List<List<Integer>> list1,List<List<Integer>> list2){
        //将list1乘到list2上
        List<List<Integer>> ans=new ArrayList<>();
        for(List<Integer> l1:list1){
            for(List<Integer> l2:list2){
                int ans0=l2.get(0)*l1.get(0),ans1=l2.get(1)+l1.get(1);
                List<Integer> list=new ArrayList<>();
                list.add(ans0);
                list.add(ans1);
                ans.add(list);
            }
        }
        return ans;
    }
    private static String getRealNumber(String s) {
        if(s.charAt(0)==' '){
            s = s.substring(1);
        }
        if(s.charAt(s.length()-1)==' '){
            s = s.substring(0, s.length()-1);
        }
        return s;
    }
    public static void sub(List<List<Integer>> list1,List<List<Integer>> list2){
        //list1减去list2
        for(List<Integer> l2:list2){
            l2.set(0,-l2.get(0));
        }
        add(list2,list1);

    }
    public static void add(List<List<Integer>> list1,List<List<Integer>> list2){
        //将list1加到list2中
        for(List<Integer> l1:list1){
            boolean add=false;
            List<Integer> t=null;
            for(List<Integer> l2:list2){

                if(l1.get(1).equals(l2.get(1))){
                    add=true;
                    int ans=l2.get(0)+l1.get(0);
                    if(ans==0){
                        t=l2;
                    }else{
                        l2.set(0,ans);
                    }
                }

            }
            if(t!=null){
                list2.remove(t);
            }
            if(!add){
                list2.add(l1);
            }
        }
    }
    public static void show(List<List<Integer>> list){//显示多项式
        merge(list);
        int n=list.size();
        for(int i=0;i<n;i++){
            if(i>0&&list.get(i).get(0)>0)
                System.out.print("+");
            System.out.print(list.get(i).get(0));
            int b=list.get(i).get(1);
            if(b>0){
                System.out.print("X");
                if(b>1){
                    System.out.printf("^%d",b);
                }
            }
        }
    }

    private static void merge(List<List<Integer>> list) {//合并化简同类项
        list.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(1)- o2.get(1);
            }
        });
        List<List<Integer>> remove=new ArrayList<>();
        int size= list.size();
        for(int i=0;i<size-1;i++){
            if(list.get(i).get(0)==0){
                remove.add(list.get(i));
            } else if(list.get(i).get(1)== list.get(i+1).get(1)){
                list.get(i+1).set(0, list.get(i).get(0)+ list.get(i+1).get(0));
                remove.add(list.get(i));
            }
        }
        while(remove.size()>0){
            list.remove(remove.remove(0));
        }
    }

    public static List<List<Integer>> init(int n,String s){
        int index=0;
        List<List<Integer>> list=new ArrayList<>();
        for(int i=0;i<n;i++){
            List list1=new ArrayList();
            index++;
            boolean abs=false;
            //System.out.println("index = "+index);
            if(s.charAt(index)=='-'){
                abs=true;
                index++;
            }
            int num=0;
            while(s.charAt(index)<='9'&&s.charAt(index)>='0'){
                num=num*10+s.charAt(index++)-'0';
            }
            if(abs){
                num*=-1;
            }
            list1.add(num);
            index++;
            num=0;
            while(s.charAt(index)<='9'&&s.charAt(index)>='0'){
                num=num*10+s.charAt(index++)-'0';
            }
            index++;
            list1.add(num);
            list.add(list1);
        }
        return list;
    }
}
