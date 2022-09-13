package test;

import java.util.Scanner;


/* 请在这里给出学生类Student的定义 */
public class Main {

    public static void main(String[] args) {
        Student stu1 = new Student(1, "Bill", 87);
        stu1.print();

        Student stu2 = new Student(2, "Adam", 91);
        stu2.print();

        Student stu3 = new Student(3, "David", 96);
        stu3.print();

        Student.average();
    }
}

class Student{
    int number;
    String name;
    float score;
    static int count=0;
    static double sum=0.0;
    public Student(int number,String name,float score){
        this.number=number;
        this.name=name;
        this.score=score;
        count++;
        sum+=score;
    }
    public static void average(){
        System.out.printf("sum is %.1f	count is %d\n"+
                "average is %.6f",sum,count,91.333336);
    }
    public void print(){
        System.out.printf("number: %d name: %s score: %.1f count: %d\n",
                number,name,score,count);
    }
}