package thread_sync.demo1;

import java.util.concurrent.*;

/*
* 计时任务中断*/
public class TimeCount {
    private static final ScheduledExecutorService cancelExec=new ScheduledThreadPoolExecutor(1);
    public static void main(String[] args) {
        try {
            timedRun(new Runnable() {
                @Override
                public void run() {
                    int i=0;
                    long time1=System.currentTimeMillis();
                    while(true){
                        System.out.println(i++);
                        if(System.currentTimeMillis()-time1>=1000){
                            System.out.printf("| %d |",System.currentTimeMillis()-time1);
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        /*try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }*/
                    }
                }
            },1,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("已超时，结束");
        }
    }
    public static void timedRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        class ReThrowableTask implements Runnable{
            private volatile Throwable t;
            @Override
            public void run() {
                try{
                    r.run();
                }catch (Throwable t){
                    this.t=t;
                }
            }
            void rethrow(){
                if(t!=null){
                    throw new RuntimeException(t);
                }
            }
        }
        ReThrowableTask task=new ReThrowableTask();
        final Thread taskThread=new Thread(task);
        taskThread.start();
        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {
                taskThread.interrupt();
            }
        },timeout,unit);
        taskThread.join(unit.toMillis(timeout));
        System.out.println("join");
        Thread.sleep(1000);
        try{
            task.rethrow();
            //接收超时之后抛出的异常
        }catch (Throwable t){
            System.out.println("已超时，结束2");
        }

        System.out.println("task.rethrow()");
    }
    public static void timedRun1(Runnable r, long timeout, TimeUnit unit){
        Future<?> task=cancelExec.submit(r);
        try{
            task.get(timeout,unit);
        }catch (ExecutionException e) {//包装了正在执行的线程抛出的任何异常
            throw new RuntimeException(e);
        } catch (InterruptedException e) {//线程终止时抛出的异常，便于调用者对此情况进行处理
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            task.cancel(true);
        }finally {
            task.cancel(true);//如果任务正在执行，此时将会被中断
        }
    }
}