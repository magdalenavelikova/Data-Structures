package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class DoublyLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        private E value;
        private Node<E> next;
        private Node<E> previous;

        public Node(E value) {
            this.value = value;
        }
    }

    public DoublyLinkedList() {
    }

    @Override
    public void addFirst(E element) {
        Node<E> toInsert = new Node<>(element);
        if (this.isEmpty()) {
            this.head = this.tail = toInsert;
        } else {
           this.head.previous = toInsert;
            toInsert.next = head;
            this.head = toInsert;
        }
        this.size++;

    }

    @Override
    public void addLast(E element) {
        Node<E> toInsert = new Node<>(element);
        if (this.isEmpty()) {
            this.addFirst(element);
        } else {
            this.tail.next = toInsert;
            toInsert.previous = tail;
            this.tail = toInsert;
            this.size++;
        }


    }

    @Override
    public E removeFirst() {
        ensureNotEmpty();

        E value = this.head.value;
        if (this.size == 1) {
            this.head = this.tail = null;
        } else {
            this.head = head.next;
            head.previous = null;
        }
        this.size--;
        return value;
    }


    @Override
    public E removeLast() {
        ensureNotEmpty();
        E value = this.tail.value;
        if (this.size == 1) {
            this.removeFirst();
        } else {
            this.tail = tail.previous;
            tail.next = null;
            this.size--;
        }

        return value;
    }

    @Override
    public E getFirst() {
        ensureNotEmpty();
        return this.head.value;
    }

    @Override
    public E getLast() {
        ensureNotEmpty();
        return this.tail.value;
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
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return head.next != null;
            }

            @Override
            public E next() {
                E current = head.next.value;
                head = head.next;
                return current;
            }
        };
    }

    private void ensureNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
    }
}
