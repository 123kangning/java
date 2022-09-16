package test;

public class F001 {
    public static void main(String[] args) {
        String[] s=new String[5];
        for(int i=0;i<5;i++){
            s[i]="";
        }
        for(String a:s){
            System.out.println(a);
        }
    }
}
