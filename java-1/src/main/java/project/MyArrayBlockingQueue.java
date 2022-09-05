package project;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyArrayBlockingQueue<E> implements MyBlockingQueue<E> {
    private int len = Integer.MAX_VALUE;
    private int start = 0, end = 0;
    private Object[] meal;
    private Lock lockAdd = new ReentrantLock();
    private Condition notFull = lockAdd.newCondition(), notEmpty = lockAdd.newCondition();


    public MyArrayBlockingQueue() {
        meal = new Object[len];
    }

    public MyArrayBlockingQueue(int n) {
        len = n + 1;
        meal = new Object[len];
    }

    public void put(E e) {
        lockAdd.lock();
        //System.out.println("put");
        try {
            while ((end + 1) % len == start) {
                notFull.await();
            }
        } catch (InterruptedException e1) {
            System.out.println("add wait error" + e1.getMessage());
        }

        meal[end] = e;
        end = (end + 1) % len;
        notEmpty.signalAll();
        lockAdd.unlock();
    }

    public E take() {
        lockAdd.lock();
        //System.out.println("take");
        try {
            while (start == end) {
                notEmpty.await();
            }
            E ans = (E) meal[start];
            meal[start] = null;
            start = (start + 1) % len;
            notFull.signalAll();
            return ans;
        } catch (InterruptedException e1) {
            System.out.print("");
            //System.out.println(" take start = " + start + " end = " + end);
        } finally {
            lockAdd.unlock();
        }
        return null;
    }

    public E poll(long time, TimeUnit unit) {
        time = unit.toNanos(time);
        lockAdd.lock();
        try {
            while (isEmpty()) {
                if (time <= 0) {
                    return null;
                }
                time = notEmpty.awaitNanos(time);
            }
            E ans = (E) meal[start];
            start = (start + 1) % len;
            return ans;
        } catch (InterruptedException e) {
            System.out.println("poll start = " + start + " end = " + end);
        } finally {
            lockAdd.unlock();
        }
        return null;
    }

    public synchronized boolean offer(E e) {
        if ((end + 1) % len == start) {
            return false;
        }
        lockAdd.lock();
        meal[end] = e;
        end = (end + 1) % len;
        lockAdd.unlock();
        return true;
    }

    public int size() {
        return (end - start + len) % len;
    }


    public boolean isEmpty() {
        return start == end;
    }

    public boolean isFull() {
        return (end + 1) % len == start;
    }

}
