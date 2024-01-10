package implementations;

import org.junit.Test;

public class ArrayDequeTest {

    @Test
    public void add() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();
        arrayDeque.add(1);
        arrayDeque.add(2);
        arrayDeque.add(3);
        arrayDeque.add(4);
        arrayDeque.add(5);
        arrayDeque.add(6);
        arrayDeque.add(7);
        System.out.println(arrayDeque.get(Integer.valueOf(6)));
    }

    @Test
    public void offer() {

    }

    @Test
    public void addFirst() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();
        arrayDeque.addFirst(1);
        arrayDeque.addFirst(2);
        arrayDeque.addFirst(3);
        arrayDeque.addFirst(4);
        arrayDeque.addFirst(5);
        arrayDeque.addFirst(6);
        arrayDeque.addFirst(7);
    }

    @Test
    public void addLast() {
    }

    @Test
    public void push() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void set() {
    }

    @Test
    public void peek() {
    }

    @Test
    public void poll() {
    }

    @Test
    public void pop() {
    }

    @Test
    public void get() {
    }

    @Test
    public void testGet() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void testRemove() {
    }

    @Test
    public void removeFirst() {
    }

    @Test
    public void removeLast() {
    }

    @Test
    public void size() {
    }

    @Test
    public void capacity() {
    }

    @Test
    public void trimToSize() {
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void iterator() {
    }
}