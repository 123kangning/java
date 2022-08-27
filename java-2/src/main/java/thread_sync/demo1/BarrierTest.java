package thread_sync.demo1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarrierTest {
    public static int threadCount=10;
    private static CyclicBarrier barrier=new CyclicBarrier(threadCount);
    public static void main(String[] args) {
        for(int i=0;i<threadCount;i++){
            new Thread(new Hello(barrier)).start();
        }
    }

}
class Hello implements Runnable{
    public CyclicBarrier barrier;
    public Hello(CyclicBarrier barrier){
        this.barrier=barrier;
    }

    @Override
    public void run() {
        System.out.println(" 1 + "+Thread.currentThread().getName());
        try {
            barrier.await();
            System.out.println(" 2 + "+Thread.currentThread().getName());
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}