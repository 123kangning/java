package test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String[] s1=scanner.nextLine().split("-"),s2=scanner.nextLine().split("-");



        long day1= LocalDate.of(Integer.parseInt(s1[0]),Integer.parseInt(s1[1]),Integer.parseInt(s1[2])).toEpochDay(),
                day2=LocalDate.of(Integer.parseInt(s2[0]),Integer.parseInt(s2[1]),Integer.parseInt(s2[2])).toEpochDay();

        long distance=day2-day1;
        System.out.println("第一个日期比第二个日期更"+(distance<0?"晚":"早"));
        distance=Math.abs(distance);
        System.out.println("两个日期间隔"+distance+"天");
        System.out.println("两个日期间隔"+distance/7+"周");
    }
}
