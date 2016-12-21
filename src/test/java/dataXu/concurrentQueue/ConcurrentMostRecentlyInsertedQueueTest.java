package dataXu.concurrentQueue;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.Queue;

@RunWith(JUnit4ClassRunner.class)
public class ConcurrentMostRecentlyInsertedQueueTest {

    @Test
    public void test_offer_poll_concurrently() throws InterruptedException {
        final Queue<Integer> queue = new ConcurrentMostRecentlyInsertedQueue<>(50);

        Runnable producer = () -> {
            try {
                while (true) {
                    for (int i = 0; i < 100; ++i) {
                        queue.offer(i);
                        System.out.println("offer element: " + i + " --- Size : " + queue.size());
                    }

                    System.out.println("Thread-" + Thread.currentThread().getName());
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {

            }
        };

        Runnable consumer = () -> {
            while (true) {
                System.out.println("Thread-" + Thread.currentThread().getName());
                System.out.println("Size before : " + queue.size() + " --- poll element: " + queue.poll() + " --- Size after : " + queue.size());
            }
        };

        Thread producerThread = new Thread(producer, "producer");
        Thread consumerThread = new Thread(consumer, "consumer");

        producerThread.start();
        consumerThread.start();
    }

}