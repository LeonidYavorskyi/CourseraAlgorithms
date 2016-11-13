package part1.queues;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(JUnit4ClassRunner.class)
public class DequeTest {

    private Deque<Integer> dequeArray;

    @Before
    public  void init() {
        dequeArray = new Deque<>();
    }

    private void fillDeque(){
        dequeArray.addFirst(11);
        dequeArray.addFirst(12);
        dequeArray.addLast(20);
        dequeArray.addFirst(13);
        dequeArray.addLast(25);
        // 13 12 11 20 25
    }

    @Test
    public void testSizeAndEmpty() {
        fillDeque();
        assertEquals(5, dequeArray.size());
        assertFalse(dequeArray.isEmpty());
    }

    @Test
    public void testRemoveFirstAndLast(){
        fillDeque();
        assertEquals(Integer.valueOf(13), dequeArray.removeFirst());
        assertEquals(dequeArray.size(), 4);

        assertEquals(Integer.valueOf(25), dequeArray.removeLast());
        assertEquals(Integer.valueOf(20), dequeArray.removeLast());
        assertEquals(dequeArray.size(), 2);
    }

    @Test
    public void testIterator(){

    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveLastFromEmptyDeque() {
        dequeArray.removeLast();
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFirstFromEmptyDeque() {
        dequeArray.removeFirst();
    }

    @Test(expected = NullPointerException.class)
    public void testAddFirstNull() {
        dequeArray.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddLastNull() {
        dequeArray.addLast(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveFromEmptyDeque() throws Exception {
        Iterator<Integer> iterator = dequeArray.iterator();
        iterator.remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetElementFromEmptyDeque() throws Exception{
        Iterator<Integer> iterator = dequeArray.iterator();
        iterator.next();
    }
}
