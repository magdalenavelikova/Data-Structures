package implementations;

import java.util.Iterator;

public class ReversedList<E> implements interfaces.ReversedList<E> {
    private final int INITIAL_CAPACITY = 2;

    private Object[] elements;
    private int size;

    private int head;

    private int tail;

    public ReversedList() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.size = 0;
        tail = head = 0;
    }

    @Override
    public void add(E element) {
        if (this.isEmpty()) {
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
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.elements.length;
    }

    @Override
    public E get(int index) {

        int realIndex = this.tail - index;
       this.ensureIndex(realIndex);
        return this.getAt(realIndex);
    }


    @Override
    public E removeAt(int index) {
        int begin = tail - index;
        E element = getAt(begin);
        this.ensureIndex(begin);
        for (int i = begin; i < tail; i++) {
            this.elements[i] = this.elements[i + 1];
        }
        this.elements[this.tail] = null;
        this.tail--;
        this.size--;
        return element;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int index = tail;

            @Override
            public boolean hasNext() {
                return index >= head;
            }

            @Override
            public E next() {
                return getAt(index--);
            }
        };
    }

    @SuppressWarnings("unchecked")
    private E getAt(int index) {
        return (E) this.elements[index];
    }

    private void ensureIndex(int realIndex) {
        if (realIndex < this.head || realIndex > this.tail) {
            throw new IndexOutOfBoundsException("Index out of bounds for index " + (realIndex));
        }
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    private Object[] grow() {
        int newCapacity = this.elements.length * 2;
        Object[] tmp = new Object[newCapacity];
        System.arraycopy(this.elements, 0, tmp, 0, this.elements.length);
        return tmp;

    }
}
