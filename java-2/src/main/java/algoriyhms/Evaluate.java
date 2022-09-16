package algoriyhms;

import java.util.Scanner;
import java.util.Stack;

public class Evaluate {
    public static void main(String[] args) {
        Stack<Character> ops=new Stack<>();
        Stack<Double> val=new Stack<>();
        String exc=new Scanner(System.in).nextLine();
        int index=0,len=exc.length();
        while(index<len){
            Character s=exc.charAt(index++);
            if(s.equals('(')){
                //不操作
            } else if (s.equals('+')||s.equals('-')||s.equals('*')||s.equals('/')) {
                ops.push(s);
            } else if (s.equals(')')||index==len-1) {
                //弹出运算符和操作数，计算结果并压入栈
                Character op=ops.pop();
                double v=val.pop();
                if(op.equals('+')){
                    v+=val.pop();
                } else if (op.equals('-')) {
                    v-=val.pop();
                } else if (op.equals('*')) {
                    v*=val.pop();
                } else if (op.equals('/')) {
                    v/=val.pop();
                }
            }else{
                double v=s-'0';
                while(++index<len&&exc.charAt(index)<='9'&&exc.charAt(index)>='0'){
                    v=v*10+exc.charAt(index)-'0';
                }
                val.push(v);
            }
        }
        System.out.println(val.pop());
    }
}
