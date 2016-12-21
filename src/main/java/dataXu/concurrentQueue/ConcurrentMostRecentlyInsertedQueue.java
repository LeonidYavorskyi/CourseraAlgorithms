package dataXu.concurrentQueue;

import dataXu.queue.MostRecentlyInsertedQueue;

import java.util.AbstractQueue;
import java.util.Iterator;

public class ConcurrentMostRecentlyInsertedQueue<E> extends AbstractQueue<E> {

    private MostRecentlyInsertedQueue<E> mostRecentlyInsertedQueue;

    private final Object lock;

    public ConcurrentMostRecentlyInsertedQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        mostRecentlyInsertedQueue = new MostRecentlyInsertedQueue<>(capacity);
        lock = this;
    }

    @Override
    public Iterator<E> iterator() {
        return mostRecentlyInsertedQueue.iterator();
    }

    @Override
    public int size() {
        synchronized (lock){
            return mostRecentlyInsertedQueue.size();
        }
    }

    @Override
    public boolean offer(E e) {
        synchronized (lock){
            return mostRecentlyInsertedQueue.offer(e);
        }
    }

    @Override
    public E poll() {
        synchronized (lock){
            return mostRecentlyInsertedQueue.poll();
        }
    }

    @Override
    public E peek() {
        synchronized (lock){
            return mostRecentlyInsertedQueue.peek();
        }
    }


}
