package test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s1,s2;
        String[] s=new String[2];
        do{
            s1=scanner.nextLine();
            if(s1.equals(""))break;
            s=s1.split(" ");
            s1=s[0];
            s2=s[1];
            int index=0;
            while(s1.charAt(index)==s2.charAt(index)){
                index++;
            }
            if(index>0){
                System.out.println("The common prefix is "+s1.substring(0,index));
            }else{
                System.out.println("No common prefix");
            }
        }while(scanner.hasNext());
    }
}
