package implementations;

import interfaces.AbstractTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {

    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;


    @SafeVarargs
    public Tree(E key, Tree<E>... subtrees) {
        this.key = key;
        this.children = new ArrayList<>();

        for (Tree<E> subtree : subtrees) {
            this.children.add(subtree);
            subtree.parent = this;
        }


    }

    @Override
    public List<E> orderBfs() {
        List<E> result = new ArrayList<>();
        ArrayDeque<Tree<E>> queue = new ArrayDeque<>();
        if (this.key != null) {
            queue.offer(this);
        }
        while (!queue.isEmpty()) {
            Tree<E> current = queue.poll();
            result.add(current.key);
            current.children.forEach(queue::offer);
        }

        return result;
    }

    @Override
    public List<E> orderDfs() {
        List<E> result = new ArrayList<>();
        dfs(this, result);
        return result;
    }

    private void dfs(Tree<E> node, List<E> result) {
        for (Tree<E> child : node.children) {
            this.dfs(child, result);
        }
        result.add(node.key);

    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {
        Tree<E> search = findBfs(parentKey);
        if (search == null) {
            throw new IllegalArgumentException();
        }
        search.children.add(child);
        child.parent = search;
    }

    private Tree<E> findBfs(E parentKey) {

        ArrayDeque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            Tree<E> current = queue.poll();
            current.children.forEach(queue::offer);
            if (current.key.equals(parentKey)) {
                return current;
            }

        }
        return null;
    }

    private Tree<E> findDfs(Tree<E> current, E parentKey) {
        if (current.key.equals(parentKey)) {
            return current;
        }

        for (Tree<E> child : current.children) {
            Tree<E> found = this.findDfs(child, parentKey);
            if (found != null) {
                return found;
            }
        }

        return null;
    }

    @Override
    public void removeNode(E nodeKey) {
        Tree<E> treeForRemove = findDfs(this, nodeKey);
        if (treeForRemove == null) {
            throw new IllegalArgumentException();
        }
        treeForRemove.children.forEach(ch -> ch.parent = null);
        treeForRemove.children.clear();

        Tree<E> parent = treeForRemove.parent;
        if (parent != null) {
            parent.children.remove(treeForRemove);
        } else {
            treeForRemove.key = null;
        }


    }

    @Override
    public void swap(E firstKey, E secondKey) {
        Tree<E> firstSwap = findDfs(this, firstKey);
        Tree<E> secondSwap = findDfs(this, secondKey);
        if (firstSwap == null || secondSwap == null) {
            throw new IllegalArgumentException();
        }

        Tree<E> firstParent = firstSwap.parent;
        Tree<E> secondParent = secondSwap.parent;
        if (firstParent == null ) {
            swapRoot(secondSwap);
            return;
        }
        if (secondParent == null ) {
            swapRoot(firstSwap);
            return;
        }
            firstSwap.parent = secondParent;
            secondSwap.parent = firstParent;


            int firstIndex = firstParent.children.indexOf(firstSwap);
            int secondIndex = secondParent.children.indexOf(secondSwap);

            firstParent.children.set(firstIndex, secondSwap);
            secondParent.children.set(secondIndex, firstSwap);

    }

    private void swapRoot(Tree<E> swap) {
        this.key= swap.key;
        this.parent=null;
        this.children= swap.children;

    }
}



