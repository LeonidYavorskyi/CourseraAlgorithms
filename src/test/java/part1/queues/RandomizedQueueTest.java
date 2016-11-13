package part1.queues;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4ClassRunner.class)
public class RandomizedQueueTest {

    private RandomizedQueue<Integer> randomizedQueue;

    @Before
    public void init() {
        randomizedQueue = new RandomizedQueue<>();
    }

    private void fill() {
        randomizedQueue.enqueue(10);
        randomizedQueue.enqueue(20);
        randomizedQueue.enqueue(30);
        randomizedQueue.enqueue(40);
        randomizedQueue.enqueue(50);
        randomizedQueue.enqueue(60);
    }

    @Test
    public void testEnque() {
        fill();
        assertFalse(randomizedQueue.isEmpty());
        assertEquals(6, randomizedQueue.size());

        randomizedQueue.enqueue(70);
        assertEquals(7, randomizedQueue.size());

        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        assertEquals(4, randomizedQueue.size());
    }

    @Test
    public void testIterator(){
        fill();
        Iterator<Integer> iterator = randomizedQueue.iterator();
        assertTrue(iterator.hasNext());

        assertEquals(Integer.valueOf(10), iterator.next());
        assertEquals(Integer.valueOf(20), iterator.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeFromEmptyDeque() {
        randomizedQueue.dequeue();
    }

    @Test(expected = NullPointerException.class)
    public void testEnqueNull() {
        randomizedQueue.enqueue(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveFromEmptyDeque() throws Exception {
        Iterator<Integer> iterator = randomizedQueue.iterator();
        iterator.remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetElementFromEmptyDeque() throws Exception {
        Iterator<Integer> iterator = randomizedQueue.iterator();
        iterator.next();
    }
}
