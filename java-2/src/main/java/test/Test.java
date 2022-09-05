package test;

class Test {
    public static void main(String[] args) {
        Student student = new Student("aaaaaa");
        System.out.println("name? " + student.name);
        System.out.println("age? " + student.age);
        System.out.println("isScienceMajor? " + student.isScienceMajor);
        System.out.println("gender? " + student.gender);
    }
}
class Student {
    static String name; // name has default value null
    int age; // age has default value 0
    boolean isScienceMajor; // isScienceMajor has default value false
    char gender; // c has default value '\u0000'
    public Student(String name){
        this.name=name;
    }
}
