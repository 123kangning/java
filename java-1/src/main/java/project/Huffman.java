/*
 * usage : java PathToHuffman inputText outputText
 * */
package project;

import java.io.*;
import java.util.*;

interface Tree<E> {
    int nodeCount();

    int leafCount();

    Node<E> creatTree(Map<E, Integer> map);

    void prePrint();

    void prePrintWithCirculation();

    void inPrint();

    void inPrintWithCirculation();

    void postPrint();

    void postPrintWithCirculation();

    void seqPrint();

    int getHigh();

    Node<E> searchNodeData(E data);

    int nodeCountInSeq(int seq);

    Node<E> cloneTree();

    Node<E> imageForTree();

    boolean checkNodeInTree(Node<E> data);

    boolean isCompleteTree(Node<E> root);
}

class MyTree<E> implements Tree<E> {
    Node<E> root = new Node<>();

    MyTree(Map<E, Integer> map) {
        root = creatTree(map);
    }

    public boolean isCompleteTree() {
        return isCompleteTree(root);
    }

    public boolean isCompleteTree(Node<E> root) {
        if (root == null) return true;
        if (getHigh(root.left) < getHigh(root.right)) return false;
        return isCompleteTree(root.left) && isCompleteTree(root.right);
    }

    public int nodeCount() {
        return nodeCount(root);
    }

    public int nodeCount(Node<E> root) {
        if (root == null) return 0;
        return nodeCount(root.left) + nodeCount(root.right) + 1;
    }

    public int leafCount() {
        return leafCount(root);
    }

    public int leafCount(Node<E> root) {
        if (root.left == null && root.right == null) {//root is leaf
            return 1;
        }
        //root isn't leaf
        int left = 0, right = 0;
        if (root.left != null) {
            left = leafCount(root.left);
        }
        if (root.right != null) {
            right = leafCount(root.right);
        }
        return left + right;
    }

    public void prePrint() {
        prePrint(root);
    }

    public void prePrint(Node<E> root) {
        if (root == null) {
            return;
        }
        System.out.println(root.getC() + " " + root.getCode());

        prePrint(root.left);
        prePrint(root.right);

    }

    public void prePrintWithCirculation() {
        if (root == null) return;
        Stack<Node<E>> stack = new Stack<>();
        stack.add(root);
        Node<E> current = root;
        while (!stack.isEmpty()) {
            if (current != null) {
                System.out.println(current.getC() + " " + current.getCode());
                stack.add(current.right);
                current = current.left;
            } else {
                current = stack.pop();
            }
        }
    }

    public void inPrint() {
        inPrint(root);
    }

    public void inPrint(Node<E> root) {
        if (root == null) {
            return;
        }
        inPrint(root.left);
        System.out.println(root.getC() + " " + root.getCode());
        inPrint(root.right);
    }

    public void inPrintWithCirculation() {
        if (root == null) return;
        Stack<Node<E>> stack = new Stack<>();
        stack.add(root);
        Node<E> current = root;
        while (!stack.isEmpty()) {
            if (current == null) {
                current = stack.pop();
                System.out.println(current.getC() + " " + current.getCode());
                current = current.right;
            } else {
                stack.add(current);
                current = current.left;
            }
        }
    }

    public void postPrint() {
        postPrint(root);
    }

    public void postPrint(Node<E> root) {
        if (root == null) {
            return;
        }
        postPrint(root.left);
        postPrint(root.right);
        System.out.println(root.getC() + " " + root.getCode());
    }

    public void postPrintWithCirculation() {
        if (root == null) return;
        Stack<Node<E>> stack = new Stack<>();
        stack.add(root);
        stack.add(root);
        Node<E> current = root;
        while (!stack.isEmpty()) {
            current = stack.pop();
            if (!stack.isEmpty() && current == stack.peek()) {
                if (current.right != null) {
                    stack.add(current.right);
                    stack.add(current.right);
                }
                if (current.left != null) {
                    stack.add(current.left);
                    stack.add(current.left);
                }
            } else {
                System.out.println(current.getC() + " " + current.getCode());
            }
        }
    }

    public void seqPrint() {
        seqPrint(root);
    }

    public void seqPrint(Node<E> root) {
        if (root == null) return;
        Deque<Node<E>> queue = new ArrayDeque<>();
        queue.addLast(root);
        int head = 0;
        int tail = 1;
        while (head < tail) {
            if (Objects.requireNonNull(queue.peekFirst()).left != null) {
                queue.addLast(queue.peekFirst().left);
                tail++;
            }
            if (Objects.requireNonNull(queue.peekFirst()).right != null) {
                queue.addLast(queue.peekFirst().right);
                tail++;
            }
            assert queue.peekFirst() != null;
            System.out.println(queue.peekFirst().getC() + " " + queue.removeFirst().getCode());
            head++;
        }
    }

    public int getHigh() {
        return getHigh(root);
    }

    public int getHigh(Node<E> root) {
        if (root == null) return 0;
        int m1 = getHigh(root.left);
        int m2 = getHigh(root.right);
        return 1 + Math.max(m1, m2);
    }

    public Node<E> cloneTree() {
        return cloneTree(root);
    }

    public Node<E> cloneTree(Node<E> root) {
        if (root == null) return null;
        Node<E> ans = new Node<>();
        ans.setWeight(root.getWeight());
        ans.setC(root.getC());
        ans.setCode(root.getCode());
        ans.left = cloneTree(root.left);
        ans.right = cloneTree(root.right);
        return root;
    }

    public Node<E> searchNodeData(E data) {
        return searchNodeData(root, data);
    }

    public Node<E> searchNodeData(Node<E> root, E data) {
        if (root == null) return null;
        if (root.getC() == data) return root;
        Node<E> n1 = searchNodeData(root.left, data);
        Node<E> n2 = searchNodeData(root.right, data);
        if (n1 != null) return n1;
        return n2;
    }

    public int nodeCountInSeq(int seq) {
        //return -1 is meaning not this seq
        //return 0 is meaning root is null
        if (seq > getHigh(root) || seq < 0) {
            return -1;
        }
        if (root == null) return 0;
        Deque<Node<E>> queue = new ArrayDeque<>();
        queue.addLast(root);
        int head = 0;
        int tail = 1;
        int count = 0;
        while (head < tail) {
            count++;
            if (count == seq) {
                return tail - head;
            }
            if (Objects.requireNonNull(queue.peekFirst()).left != null) {
                queue.addLast(queue.peekFirst().left);
                tail++;
            }
            if (Objects.requireNonNull(queue.peekFirst()).right != null) {
                queue.addLast(queue.peekFirst().right);
                tail++;
            }
            queue.removeFirst();
            head++;
        }
        return -1;
    }

    public Node<E> creatTree(Map<E, Integer> map) {
        LinkedList<Node<E>> list = new LinkedList<>();
        for (Map.Entry<E, Integer> e : map.entrySet()) {
            Node<E> node = new Node<>();
            node.setC(e.getKey());
            node.setWeight(e.getValue());
            node.left = null;
            node.right = null;
            list.add(node);
        }
        while (list.size() > 0) {
            Collections.sort(list);
            if (list.size() == 1) {
                return list.get(0);
            } else {
                Node<E> node1 = list.removeFirst();
                Node<E> node2 = list.removeFirst();
                Node<E> node = new Node<>();
                node.setWeight(node1.getWeight() + node2.getWeight());
                node.setC(null);

                if (node1.getWeight() < node2.getWeight()) {
                    node.left = node1;
                    node.right = node2;
                } else {
                    node.left = node2;
                    node.right = node1;
                }
                list.add(node);
            }
        }
        return null;
    }

    public Node<E> imageForTree() {
        return imageForTree(this.root);
    }

    public Node<E> imageForTree(Node<E> root) {
        if (this.root == null) return null;
        Node<E> temp = imageForTree(root.left);
        root.left = imageForTree(root.right);
        root.right = temp;
        return root;
    }

    public boolean checkNodeInTree(Node<E> data) {
        return true;
    }
}

class Node<E> implements Comparable {

    Node<E> left;
    Node<E> right;
    private E c;
    private int weight;
    private String code = "";

    public E getC() {
        return this.c;
    }

    public void setC(E c) {
        this.c = c;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String c) {
        code = code.concat(c);
    }

    public int compareTo(Object a) {
        return this.getWeight() - ((Node<E>) a).getWeight();
    }
}

public class Huffman {
    static String src1 = "../1.souce", src2 = "../1.code";

    public static void main(String... args) throws IOException {
        checkExist(args);
        System.out.println("What do you want to do ,1 for compress ,0 for decompress");
        Scanner input = new Scanner(System.in);
        int sign = input.nextInt();
        input.close();
        long startTime = System.currentTimeMillis(), endTime;
        if (sign == 1) {
            String srcString = readSrc();
            //System.out.println(srcString);
            Map<Character, Integer> map = account(srcString);
            MyTree<Character> tree = new MyTree<>(map);
            tree.root = tree.creatTree(map);
            setCodeForTree(tree.root);
            Map<Character, String> map1 = new HashMap<>();
            preCode(tree.root, map1);
            long time2 = System.currentTimeMillis(), time22;
            printToFile(srcString, map,map1);
            time22 = System.currentTimeMillis();
            System.out.println("time is " + (time22 - time2));
        } else {
            try {
                restoreTree();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("spend time is " + (endTime - startTime));

//        for (Map.Entry<Character, String> e : map1.entrySet()) {
//            System.out.println(e.getKey() + " " + e.getValue());
//        }
        System.out.println("success");

    }

    public static void checkExist(String... args) throws IOException {
        if (args.length == 2) {
            src1 = args[0];
            src2 = args[1];
        } else {
            System.out.println("error : args is error");
            System.exit(1);
        }
        File file = new File(src1);
        if (!file.exists()) {
            System.out.println("src file not exist");
            System.exit(1);
        }
        file = new File(src2);
        if (!file.exists()) {
            boolean fileCreated = file.createNewFile();
            if (!fileCreated) {
                throw new IOException("Unable to create file ");
            }
        }
    }

    public static String readSrc() throws IOException {
        File file = new File(src1);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        String encoding = "UTF-8";
        long fileLength = file.length();
        byte[] fileContent = new byte[(int)fileLength];
        in.read(fileContent);
        in.close();
        return new String(fileContent, encoding);
    }

    public static Map<Character, Integer> account(String input) throws IOException {

        Map<Character, Integer> map = new HashMap<>();
        for (Character e : input.toCharArray()) {
            if (map.get(e) == null) {
                map.put(e, 1);
            } else {
                map.put(e, map.get(e) + 1);
            }
        }
        return map;
    }

    public static void setCodeForTree(Node<Character> root) {
        Node p = root;
        Deque<Node> deque = new LinkedList<>();
        deque.offer(p);
        while (deque.size() > 0) {
            p = deque.poll();
            if (p.left != null) {
                p.left.setCode(p.getCode() + "0");
                deque.offer(p.left);
            }
            if (p.right != null) {
                p.right.setCode(p.getCode() + "1");
                deque.offer(p.right);
            }
        }
    }
    public static void preCode(Node root, Map<Character, String> map) {
        if (root == null) {
            return;
        }
        if (root.getC() != null) {
            map.put((Character) root.getC(), root.getCode());
        }
        preCode(root.left, map);
        preCode(root.right, map);
    }

    public static void printToFile(String input, Map<Character,Integer> map,Map<Character,String> map1) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(src2)));

        long time1 = System.currentTimeMillis(), time11;    //1

        StringBuilder numCode=new StringBuilder();
        for (char e : input.toCharArray()) {
            numCode.append(map1.get(e));
            //System.out.println(e+" "+map1.get(e));
        }
        time11 = System.currentTimeMillis();
        System.out.println("time1 is " + (time11 - time1)); //1

        long time2 = System.currentTimeMillis(), time22;    //2
        output.writeObject(map);

        output.writeInt(numCode.length());
        output.writeObject(ChToBit(numCode.toString(),0));
        //这里可以打印出编码后的01字符串!!!
        //System.out.println("numCode = "+numCode);
        time22 = System.currentTimeMillis();
        System.out.println("time2 is " + (time22 - time2)); //2
        output.close();
    }
    public static int[] ChToBit(String numCode,int start){
        int len=numCode.length(),index=0,d=0,range=0;
        int[] num=new int[len/32+1];

        for(int i=start;i<len;i++){
            range++;
            if(numCode.charAt(i)=='1'){
                d|=1;
            }
            if(range==32){
                num[index++]=d;
                range=d=0;
            }
            d<<=1;
        }
        if(range>0){
            d<<=31-range;
            num[index]=d;
        }
        return num;
    }
    public static String BitToCh(int[] num,int size){
        StringBuilder bud=new StringBuilder();
        for(int n:num){
            StringBuilder t=new StringBuilder();
            for(int i=0;i<32;i++){
                t.append(n&1);
                n>>=1;
            }
            bud.append(t.reverse());
        }
        return bud.substring(0,size);
    }
    public static void restoreTree() throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(src1)));

        Map<Character,Integer> map = (Map<Character,Integer>) input.readObject();
        MyTree<Character> tree = new MyTree<>(map);
        tree.root = tree.creatTree(map);
        setCodeForTree(tree.root);

        long time1 = System.currentTimeMillis(), time11;    //1

        BufferedWriter output = new BufferedWriter(new FileWriter(src2));

        int size=input.readInt();
        int[] num=(int[])input.readObject();
        String s = BitToCh(num,size);
        int len = s.length(),i=0;
        time11 = System.currentTimeMillis();
        System.out.println("time1 is " + (time11 - time1)); //1
        //System.out.println("numCode = "+s); //!!!!!!!!!!!!!!
        long time2 = System.currentTimeMillis(), time22;    //2
        StringBuilder bud=new StringBuilder();
        Node<Character> t=tree.root;
        while(i<size){
            if(s.charAt(i++)=='0'){
                t=t.left;
            }else{
                t=t.right;
            }
            if(t.left==null&&t.right==null){
                bud.append(t.getC());
                t=tree.root;
            }
        }
        output.write(bud.toString());
        time22 = System.currentTimeMillis();
        System.out.println("time2 is " + (time22 - time2)); //2
        output.close();
    }
}