package implementations;

import interfaces.Deque;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {

    private final int INITIAL_CAPACITY = 7;
    private int size;
    private int head;
    private int tail;
    private Object[] elements;

    public ArrayDeque() {
        this.elements = new Object[INITIAL_CAPACITY];
        int middle = INITIAL_CAPACITY / 2;
        head = tail = middle;
        this.size = 0;
    }

    @Override
    public void add(E element) {
        addLast(element);
    }


    @Override
    public void offer(E element) {
        addLast(element);
    }

    @Override
    public void addFirst(E element) {
        if (isEmpty()) {
            addLast(element);
        } else {
            if (this.head == 0) {
                this.elements = grow();
            }
            this.elements[--this.head] = element;
            this.size++;
        }

    }

    @Override
    public void addLast(E element) {
        if (isEmpty()) {
            this.elements[this.tail] = element;
        } else {
            if (this.tail == this.elements.length - 1) {
                this.elements = grow();
            }
            this.elements[++this.tail] = element;
        }
        this.size++;

    }

    @Override
    public void push(E element) {
        addFirst(element);
    }

    @Override
    public void insert(int index, E element) {
        int realIndex = this.head + index;
        this.ensureIndex(realIndex);
        if (index < this.tail - realIndex) {
            insertAndShiftLeft(realIndex - 1, element);
        } else {
            insertAndShiftRight(realIndex, element);
        }

    }

    private void insertAndShiftLeft(int index, E element) {
        E firstElement = this.getAt(this.head);
        for (int i = head; i < index; i++) {
            this.elements[i] = this.elements[i + 1];
        }
        this.elements[index] = element;
        this.addFirst(firstElement);
    }

    private void insertAndShiftRight(int index, E element) {
        E lastElement = this.getAt(this.tail);
        for (int i = tail; i > index; i--) {
            this.elements[i] = this.elements[i - 1];
        }
        this.elements[index] = element;
        this.addLast(lastElement);
    }

    @Override
    public void set(int index, E element) {
        int realIndex = this.head + index;
        this.ensureIndex(realIndex);
        this.elements[realIndex] = element;
    }

    @Override
    public E peek() {
        if (!isEmpty()) {
            return getAt(this.head);
        }
        return null;
    }


    @Override
    public E poll() {
        return removeFirst();
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public E get(int index) {
        int realIndex = this.head + index;
        ensureIndex(realIndex);
        return this.getAt(realIndex);
    }

    @Override
    public E get(Object object) {
        if (isEmpty()) {
            return null;
        }
        for (int i = this.head; i <= this.tail; i++) {
            if (this.elements[i].equals(object)) {
                return this.getAt(i);
            }
        }

        return null;
    }

    @Override
    public E remove(int index) {

        E forRemove = get(index);
        elements[this.head + index] = null;
        for (int j = this.head + index; j < this.tail; j++) {
            this.elements[j] = this.elements[j + 1];
        }
        this.removeLast();
        return forRemove;
    }


    @Override
    public E remove(Object object) {
        if (isEmpty()) {
            return null;
        }
        for (int i = this.head; i <= this.tail; i++) {
            if (this.elements[i].equals(object)) {
                E forRemove = this.getAt(i);
                this.elements[i] = null;
                for (int j = i; j < this.tail; j++) {
                    this.elements[j] = this.elements[j + 1];
                }
                this.removeLast();
                return forRemove;
            }
        }
        return null;
    }

    @Override
    public E removeFirst() {
        if (!isEmpty()) {
            E element = this.getAt(this.head);
            this.elements[this.head] = null;
            this.head++;
            this.size--;
            return element;
        }
        return null;
    }

    @Override
    public E removeLast() {
        if (!isEmpty()) {
            E element = this.getAt(this.tail);
            this.elements[this.tail] = null;
            this.tail--;
            this.size--;
            return element;
        }
        return null;

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.elements.length;
    }

    @Override
    public void trimToSize() {
        Object[] tmp = new Object[this.size];
        int index = 0;
        for (int i = this.head; i <= this.tail; i++) {
            tmp[index++] = this.elements[i];
        }
        this.elements = tmp;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = head;

            @Override
            public boolean hasNext() {
                return index <= tail;
            }

            @Override
            public E next() {
                return getAt(index++);
            }
        };
    }

    private Object[] grow() {
        int newCapacity = this.elements.length * 2 + 1;
        Object[] tmp = new Object[newCapacity];

        int middle = newCapacity / 2;
        int begin = middle - this.size / 2;
        int index = this.head;
        for (int i = begin; index <= this.tail; i++) {
            tmp[i] = this.elements[index++];

        }
        this.head = begin;
        this.tail = this.head + this.size - 1;
        return tmp;
    }

    @SuppressWarnings("unchecked")
    private E getAt(int index) {
        return (E) this.elements[index];
    }

    private void ensureIndex(int realIndex) {
        if (realIndex < this.head || realIndex > this.tail) {
            throw new IndexOutOfBoundsException("Index out of bounds for index " + (realIndex - this.head));
        }
    }
}
