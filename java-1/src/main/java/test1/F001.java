package test1;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import static project.Huffman.*;

public class F001 {
    public static void main(String... args){
        String numCode="1010011011001001010100110011001101010010010100100101010100101101010";
        int[] num=ChToBit(numCode,0);
        String ans=BitToCh(num,numCode.length());
        System.out.println(numCode);
        System.out.println(ans);
        System.out.println(numCode.equals(ans));
    }

}
