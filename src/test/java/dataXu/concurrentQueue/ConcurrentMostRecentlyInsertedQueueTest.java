package dataXu.concurrentQueue;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConcurrentMostRecentlyInsertedQueueTest {

    @Test
    public void test_offer_concurently() throws InterruptedException {
        final Queue<Integer> queue = new ConcurrentMostRecentlyInsertedQueue<>(150);
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(500);
        final CountDownLatch cdl = new CountDownLatch(1);
        for (int i = 0; i < 120; ++i) {
            final int finalI = i;
            Runnable runnable = () -> {
                try {
                    cdl.await();
                    queue.offer(finalI);
                } catch (InterruptedException ie) {
                }
            };
            executor.execute(new Thread(runnable, "Thread-" + i));
        }
        cdl.countDown();
        executor.shutdown();
        executor.awaitTermination(4500, TimeUnit.MILLISECONDS);

        assertThat(queue.size(), is(120));
    }

}