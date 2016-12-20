package dataXu.queue;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnit4ClassRunner.class)
public class MostRecentlyInsertedQueueTest {

    private MostRecentlyInsertedQueue mostRecentlyInsertedQueue;

    @Before
    public void init() {
        mostRecentlyInsertedQueue = new MostRecentlyInsertedQueue(5);
    }

    private void fill() {
        mostRecentlyInsertedQueue.offer(10);
        mostRecentlyInsertedQueue.offer(20);
        mostRecentlyInsertedQueue.offer(30);
        mostRecentlyInsertedQueue.offer(40);
    }

    @Test
    public void offer() {
        fill();

        assertEquals(4, mostRecentlyInsertedQueue.size());

        mostRecentlyInsertedQueue.offer(55);
        assertEquals(5, mostRecentlyInsertedQueue.size());

        mostRecentlyInsertedQueue.offer(9);
        mostRecentlyInsertedQueue.offer(124);
        assertEquals(5, mostRecentlyInsertedQueue.size());
    }

    @Test
    public void poll() {
        fill();

        assertEquals(10, mostRecentlyInsertedQueue.poll());
        assertEquals(3, mostRecentlyInsertedQueue.size());

        assertEquals(20, mostRecentlyInsertedQueue.poll());
        assertEquals(30, mostRecentlyInsertedQueue.poll());
        assertEquals(1, mostRecentlyInsertedQueue.size());

        assertEquals(40, mostRecentlyInsertedQueue.poll());
        assertNull(mostRecentlyInsertedQueue.poll());
    }

    @Test
    public void peek() {
        fill();

        assertEquals(10, mostRecentlyInsertedQueue.peek());
        assertEquals(4, mostRecentlyInsertedQueue.size());

        mostRecentlyInsertedQueue.offer(55);
        assertEquals(10, mostRecentlyInsertedQueue.peek());
        assertEquals(5, mostRecentlyInsertedQueue.size());

        mostRecentlyInsertedQueue.offer(65);
        assertEquals(20, mostRecentlyInsertedQueue.peek());
        assertEquals(5, mostRecentlyInsertedQueue.size());

        mostRecentlyInsertedQueue.clear();
        assertNull(mostRecentlyInsertedQueue.peek());
    }

    @Test
    public void clear_and_isEmpty() {
        fill();

        assertFalse(mostRecentlyInsertedQueue.isEmpty());

        mostRecentlyInsertedQueue.clear();
        assertTrue(mostRecentlyInsertedQueue.isEmpty());
        assertEquals(0, mostRecentlyInsertedQueue.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_with_illegal_capacity() {
        new MostRecentlyInsertedQueue(0);
    }

    @Test(expected = NullPointerException.class)
    public void offer_null() {
        mostRecentlyInsertedQueue.offer(null);
    }


}
