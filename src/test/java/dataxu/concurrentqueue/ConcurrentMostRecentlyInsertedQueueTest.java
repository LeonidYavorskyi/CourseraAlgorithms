package dataxu.concurrentqueue;

import com.jayway.awaitility.Duration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.Queue;

import static com.jayway.awaitility.Awaitility.await;

@RunWith(BlockJUnit4ClassRunner.class)
public class ConcurrentMostRecentlyInsertedQueueTest {

  @Test
  public void test_offer_poll_concurrently() {
    final Queue<Integer> queue = new ConcurrentMostRecentlyInsertedQueue<>(50);

    Runnable producer = () -> {
      while (true) {
        for (int i = 0; i < 100; ++i) {
          queue.offer(i);
          System.out.println("offer element: " + i + " --- Size : " + queue.size());
        }

        System.out.println("Thread-" + Thread.currentThread().getName());
        await().atMost(Duration.TWO_SECONDS);
      }
    };

    Runnable consumer = () -> {
      while (true) {
        System.out.println("Thread-" + Thread.currentThread().getName());
        System.out.println("Size before : " + queue.size() + " --- poll element: " + queue.poll()
            + " --- Size after : " + queue.size());
      }
    };

    Thread producerThread = new Thread(producer, "producer");
    Thread consumerThread = new Thread(consumer, "consumer");

    producerThread.start();
    consumerThread.start();
  }

}