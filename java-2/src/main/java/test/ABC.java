package test;

import java.io.*;
public class ABC {
    public static void main(String argv[]) throws Exception {
        ABC m=new ABC();
        System.out.println(m.ff());
    }

    public int ff() {
        try {
            FileInputStream dis=new FileInputStream("Hello.txt");
        } catch (FileNotFoundException fne) {
            System.out.print("No such file found, ");
            throw fne;
        } finally {
            System.out.print("Doing finally, ");
        }
        return 0;
    }
}
