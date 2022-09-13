package test;
class Book{
    String name12;
    int id;
    double price;
    static int num=0;
    public Book(String name,double price){
        this.name12=name;
        this.price=price;
        this.id=++num;
    }
    public int getNum(){
        return this.num;
    }
    public String toString(){
        return("书名："+name12+", 书号："+id+", 书价："+price);
    }
}
public class pta7_1book{
    public static void main(String args[]){
        Book book123[]=new Book[3];
        int sumNum=0;
        book123[0]=new Book("Java程序设计",34.5);
        book123[1]=new Book("数据结构",44.8);
        book123[2]=new Book("C++程序设计",35.0);
        for(int i=0;i<book123.length;i++){
            System.out.println(book123[i].toString());
        }
        System.out.println("图书总册数为："+Book.num);
    }
}
