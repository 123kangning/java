package test;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner scanner=new Scanner(System.in);
        double a=scanner.nextDouble(),b=scanner.nextDouble(),c=scanner.nextDouble(),s1,s2;
        if(a==0&&b==0){
            if(c==0){
                System.out.print("Zero Equation");
            }else{
                System.out.print("Not An Equation");
            }
            return;
        }
        if(a==0){
            System.out.printf("%.2f",-1*c/b);
            return;
        }
        double d=Math.pow(b,2)-4*a*c;
        if(d==0){//单根
            s1=-1*b/(2*a);
            System.out.printf("%.2f\n",s1);
        }else if(d>0){//双根
            double d1=Math.sqrt(d);
            s1=(-1*b+d1)/(2*a);
            s2=(-1*b-d1)/(2*a);
            System.out.printf("%.2f\n",s1);
            System.out.printf("%.2f\n",s2);
        }else{
            s1=-1*b/(2*a);
            s2=Math.sqrt(-1*d)/(2*a);
            if(s1==0){
                System.out.printf("%.2f+%.2fi\n",0.0,s2);
                System.out.printf("%.2f-%.2fi\n",0.0,s2);
                return ;
            }
            System.out.printf("%.2f+%.2fi\n",s1,s2);
            System.out.printf("%.2f-%.2fi\n",s1,s2);
        }
    }
}
