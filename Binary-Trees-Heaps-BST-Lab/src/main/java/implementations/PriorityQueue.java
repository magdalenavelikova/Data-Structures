package implementations;

import interfaces.AbstractQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {
    private List<E> elements;

    public PriorityQueue() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.heapifyUp(this.elements.size() - 1);
    }

    private void heapifyUp(int index) {

        while (index > 0 && isLess(getParentIndex(index), index)) {
            Collections.swap(this.elements, getParentIndex(index), index);
            index = getParentIndex(index);

        }
    }

    private boolean isLess(int firstIndex, int secondIndex) {
        return this.elements.get(firstIndex).compareTo(this.elements.get(secondIndex)) < 0;
    }

    private int getParentIndex(int index) {


        return (index - 1) / 2;
    }

    @Override
    public E peek() {
        ensureNonEmpty();
        return this.elements.get(0);
    }

    private void ensureNonEmpty() {
        if (this.size() == 0) {
            throw new IllegalStateException("Heep is empty!");
        }
    }

    @Override
    public E poll() {

        ensureNonEmpty();
        E toReturn = this.elements.get(0);
        Collections.swap(this.elements, 0, size() - 1);
        this.elements.remove(size() - 1);
        this.heapifyDown(0);


        return toReturn;
    }

    private void heapifyDown(int index) {
        while (getLeftChildIndex(index) < this.elements.size() && isLess(index, getLeftChildIndex(index))) {
            int childIndex = getLeftChildIndex(index);
            if (getRightChildIndex(index) < this.elements.size() && isLess(getLeftChildIndex(index), getRightChildIndex(index))) {
                childIndex = getRightChildIndex(index);
            }
            Collections.swap(this.elements,childIndex,index);
            index=childIndex;
        }
    }

    private E getLeftChild(int index) {
        return this.elements.get(getLeftChildIndex(index));
    }

    private static int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private E getRightChild(int index) {
        return this.elements.get(getRightChildIndex(index));
    }

    private static int getRightChildIndex(int index) {
        return 2 * index + 2;
    }
}
