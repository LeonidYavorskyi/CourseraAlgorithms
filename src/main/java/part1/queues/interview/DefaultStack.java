package part1.queues.interview;

import java.util.Arrays;

public class DefaultStack<E> {

    private E[] elements = null;
    private int size = 0;
    private int top = -1;
    private final static int DEFAULT_INTIAL_CAPACITY = 10;

    public DefaultStack() {
        this(DEFAULT_INTIAL_CAPACITY);
    }

    public DefaultStack(int intialCapacity) {
        if (intialCapacity <= 0) {
            throw new IllegalArgumentException("initial capacity can't be negative or zero");
        }
        // Can't create generic type array
        elements = (E[]) new Object[intialCapacity];
    }

    public void push(E element) {
        ensureCapacity();
        elements[++top] = element;
        ++size;
    }

    public E pop() {
        E element = null;
        if (!empty()) {
            element = elements[top];
            // Nullify the reference
            elements[top] = null;
            --top;
            --size;
        }
        return element;
    }

    public E peek(){
        E element = null;
        if(!empty()) {
            element=elements[top];
        }
        return element;
    }

    public int size(){
        return size;
    }

    public boolean empty(){
        return size == 0;
    }

    private void ensureCapacity() {
        if(size != elements.length) {
            // Don't do anything. Stack has space.
        } else{
            elements = Arrays.copyOf(elements, size *2);
        }
    }

}