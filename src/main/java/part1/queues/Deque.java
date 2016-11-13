package part1.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("You can't add a null into the deque");
        }
        Node oldLast = last;
        last = new Node(item);
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
            last.prev = oldLast;
        }
        size++;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("You can't add a null into the deque");
        }
        Node oldFirst = first;
        first = new Node(item);
        if (isEmpty()) {
            last = first;
        } else {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Node oldFirst = first;
        first = oldFirst.next;
        if (first != null) {
            first.prev = null;
        }
        size--;
        return oldFirst.value;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Node oldLast = last;
        last = oldLast.prev;
        if (last != null) {
            last.next = null;
        }
        size--;
        return oldLast.value;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class Node {
        private Item value;
        private Node next;
        private Node prev;

        public Node(Item value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {

    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item value = current.value;
            current = current.next;
            return value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("You can't remove elements during iterating.");
        }
    }
}
