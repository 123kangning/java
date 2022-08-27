package test1;

public class SynchronizedTest {
    public static void main(String[] args) throws InterruptedException {
        final TestChild t = new TestChild();
        new Thread(new Runnable() {
            @Override
            public void run() {
                t.doSomething();
            }
        }).start();
        Thread.sleep(1);
        t.doSomethingElse();
    }

    public synchronized void doSomething() {
        System.out.println("something sleepy!");
        try {
            Thread.sleep(1000);
            System.out.println("woke up!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class TestChild extends SynchronizedTest {
        public synchronized void doSomething() {
            System.out.println("children start");
            super.doSomething();
        }

        public  void doSomethingElse() {
            System.out.println("something else");
        }
    }
}
