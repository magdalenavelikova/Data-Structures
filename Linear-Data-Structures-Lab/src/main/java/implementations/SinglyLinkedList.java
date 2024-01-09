package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        private E value;
        private Node<E> next;
        private Node<E> previous;

        public Node(E value) {
            this.value = value;
            this.next = null;
            this.previous=null;
        }
    }

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void addFirst(E element) {
        Node<E> toInsert = new Node<>(element);
        if (this.isEmpty()) {
            this.head = toInsert;
            this.tail = toInsert;
        } else {
            head.previous=toInsert;
            toInsert.next = head;
            this.head = toInsert;
        }
        this.size++;

    }

    @Override
    public void addLast(E element) {
        Node<E> toInsert = new Node<>(element);
        if (this.isEmpty()) {
            this.head = toInsert;
            this.tail = toInsert;
        } else {
            tail.next=toInsert;
            toInsert.previous=tail;
            this.tail = toInsert;
        }
        this.size++;

    }

    @Override
    public E removeFirst() {
        ensureIsEmpty();
        E value = this.head.value;
        this.head = head.next;
        head.previous=null;
        size--;
        return value;
    }


    @Override
    public E removeLast() {
        ensureIsEmpty();
        E value = this.tail.value;
        this.tail = tail.previous;
        tail.next=null;
        size--;
        return value;
    }

    @Override
    public E getFirst() {
        ensureIsEmpty();
        return this.head.value;
    }

    @Override
    public E getLast() {
        ensureIsEmpty();
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

    private void ensureIsEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
    }

}
