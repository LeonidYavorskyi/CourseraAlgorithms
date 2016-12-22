package dataxu.blockingqueue;

import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import dataxu.concurrentqueue.ConcurrentMostRecentlyInsertedQueue;

public class MostRecentlyInsertedBlockingQueue<E> extends ConcurrentMostRecentlyInsertedQueue<E>
    implements BlockingQueue<E> {

  private final int capacity;
  private final ReentrantLock lock;
  private final Condition isFull;

  public MostRecentlyInsertedBlockingQueue(int capacity) {
    super(capacity);
    this.capacity = capacity;
    lock = new ReentrantLock();
    isFull = lock.newCondition();
  }

  @Override
  public synchronized void put(E e) throws InterruptedException {
    if (e == null) {
      throw new NullPointerException();
    }

    try {
      lock.lockInterruptibly();
      super.offer(e);
    } finally {
      lock.unlock();
    }

  }

  @Override
  public boolean offer(E e) {
    lock.lock();

    boolean isAdded = super.offer(e);

    try {
      if (isAdded) {
        isFull.signalAll();
      }
    } finally {
      lock.unlock();
    }

    return isAdded;
  }

  @Override
  public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
    if (e == null) {
      throw new NullPointerException();
    }

    long nanos = unit.toNanos(timeout);
    boolean result;
    try {
      lock.lockInterruptibly();
      if (size() >= capacity && nanos > 0) {
        isFull.awaitNanos(nanos);
      }
      result = offer(e);

    } finally {
      lock.unlock();
    }

    return result;
  }

  @Override
  public E take() throws InterruptedException {
    try {
      lock.lockInterruptibly();

      while (size() == 0) {
        isFull.await();
      }

      return poll();

    } finally {
      lock.unlock();
    }
  }

  @Override
  public E poll(long timeout, TimeUnit unit) throws InterruptedException {
    long nanos = unit.toNanos(timeout);

    try {
      lock.lockInterruptibly();

      if (nanos <= 0) {
        return null;
      }

      while (size() == 0) {
        nanos = isFull.awaitNanos(nanos);
      }

      return poll();

    } finally {
      lock.unlock();
    }
  }

  @Override
  public int remainingCapacity() {
    return capacity - size();
  }

  @Override
  public int drainTo(Collection<? super E> c) {
    return 0;
  }

  @Override
  public int drainTo(Collection<? super E> c, int maxElements) {
    return 0;
  }
}
