package part1.queues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int DEFAULT_CAPACITY = 5;
    private int tail;
    private int size;
    private Item[] items;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = ((Item[]) new Object[DEFAULT_CAPACITY]);
        tail = 0;
        size = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("You can't add a null into the collection");
        }

        if (size == items.length) {
            resize(items.length * 2);
        }

        items[tail++] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        int index = StdRandom.uniform(size);
        Item item = items[index];

        if (index == tail - 1) {
            items[--tail] = null;
        } else {
            items[index] = items[tail - 1];
            items[--tail] = null;
        }

        size--;

        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }

        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        int index = StdRandom.uniform(size);
        return items[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = items[(i) % items.length];
        }
        items = copy;
        tail = size;
    }

    // unit testing
    public static void main(String[] args) {

    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int cursor = 0;
        private int fence = tail;

        @Override
        public boolean hasNext() {
            return cursor != fence;
        }

        @Override
        public Item next() {
            if (cursor == fence) {
                throw new NoSuchElementException();
            }
            Item result = items[cursor++];
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
