package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;


public class Queue<E> implements AbstractQueue<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E value) {
            this.value = value;
            this.next = null;
        }
    }

    public Queue() {
        this.tail = null;
        this.head = null;
        this.size = 0;
    }

    @Override
    public void offer(E element) {
        Node<E> toInsert = new Node<>(element);

        if (this.isEmpty()) {
            this.head = this.tail = toInsert;
        } else {
            this.tail.next = toInsert;
            this.tail = tail.next;
        }
        this.size++;

    }

    @Override
    public E poll() {
        ensureNotEmpty();
        E element = this.head.value;
        if (this.size == 1) {
            this.head = this.tail = null;
        } else {
            this.head = this.head.next;
        }
        this.size--;
        return element;
    }


    @Override
    public E peek() {
        ensureNotEmpty();
        return this.head.value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current.next != null;
            }

            @Override
            public E next() {
                E value = this.current.value;
                this.current = this.current.next;
                return value;
            }
        };
    }

    private void ensureNotEmpty() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
    }
}
