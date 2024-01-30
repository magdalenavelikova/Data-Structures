package implementations;

import interfaces.AbstractTree;

import java.util.*;
import java.util.stream.Collectors;

public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E key, Tree<E>... children) {
        this.key = key;
        this.children = new ArrayList<>();
        Collections.addAll(this.children, children);
        this.children.forEach(child -> child.setParent(this));
    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public String getAsString() {
        StringBuilder builder = new StringBuilder();

        traverseTreeWithRecurrence(builder, 0, this);
        return builder.toString().trim();
    }

    public String traverseWithBFS() {
        StringBuilder builder = new StringBuilder();
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);
        int ident = 0;
        while (!queue.isEmpty()) {
            Tree<E> tree = queue.poll();
            if (tree.getParent() != null && tree.getParent().getKey().equals(this.getKey())) {
                ident = 2;
            } else if (tree.children.isEmpty()) {
                ident = 4;
            }
            builder.append(getPadding(ident))
                    .append(tree.getKey())
                    .append(System.lineSeparator());
            for (Tree<E> child : tree.children) {
                queue.offer(child);
            }
        }
        return builder.toString().trim();

    }

    private void traverseTreeWithRecurrence(StringBuilder builder, int indent, Tree<E> tree) {
        builder
                .append(this.getPadding(indent))
                .append(tree.getKey())
                .append(System.lineSeparator());

        tree.children.forEach(child -> traverseTreeWithRecurrence(builder, indent + 2, child));

    }

    private String getPadding(int indent) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    @Override
    public List<E> getLeafKeys() {

        return getAllTree()
                .stream()
                .filter(tree -> tree.children.isEmpty())
                .map(Tree::getKey)
                .collect(Collectors.toList());

    }

    public List<Tree<E>> getAllTree() {

        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);

        List<Tree<E>> allNodes = new ArrayList<>();
        while (!queue.isEmpty()) {
            Tree<E> tree = queue.poll();
            allNodes.add(tree);
            for (Tree<E> child : tree.children) {

                    queue.offer(child);

            }
        }
        return allNodes;

    }

    @Override
    public List<E> getMiddleKeys() {
        return getAllTree()
                .stream()
                .filter(tree -> !tree.children.isEmpty() && tree.parent != null)
                .map(Tree::getKey)
                .collect(Collectors.toList());

    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
        List<Tree<E>> trees = getAllTree();
        int maxPath = 0;
        Tree<E> leaf = null;
        for (Tree<E> tree : trees) {

            if (tree.isLeaf()) {
                int stepsFromLeafToRoot = getStepsFromLeafToRoot(tree);
                if (stepsFromLeafToRoot > maxPath) {
                    maxPath = stepsFromLeafToRoot;
                    leaf = tree;
                }

            }
        }
//        int finalMaxPath = maxPath;
//       return trees.stream()
//                .filter(tree -> tree.isLeaf() && getStepsFromLeafToRoot(tree)== finalMaxPath)
//                .findFirst().orElse(null);
        return leaf;

    }

    private int getStepsFromLeafToRoot(Tree<E> tree) {
        int steps = 0;
        Tree<E> current = tree;
        while (current.parent != null) {
            steps++;
            current = current.parent;

        }
        return steps;
    }

    private boolean isLeaf() {
        return this.parent != null && this.children.isEmpty();
    }

    @Override
    public List<E> getLongestPath() {
        Tree<E> deepestLeftmostNode = getDeepestLeftmostNode();


        return reversedList(deepestLeftmostNode);
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {

        List<Tree<E>> trees = getAllTree();
        List<List<E>>lists=new ArrayList<>();
        for (Tree<E> tree : trees) {

            if (tree.isLeaf()) {
                int sumFromLeafToRoot = getSumFromLeafToRoot(tree);
                if (sumFromLeafToRoot == sum) {
                    lists.add(reversedList(tree));
                }

            }
        }
        return lists;
    }

    private List<E>  reversedList(Tree<E> tree) {
        List<E> list =new ArrayList<>();
        while (tree.parent!=null){
            list.add(tree.key);
            tree = tree.parent;
        }
        list.add(tree.key);
        Collections.reverse(list);
        return list;
    }

    private int getSumFromLeafToRoot(Tree<E> tree) {
        int sum = 0;
        Tree<E> current = tree;
        while (current.parent != null) {
            sum+=(Integer) current.key;
            current = current.parent;

        }
        sum+=(Integer) current.key;
        return sum;
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {

        List<Tree<E>>subtrees=getAllTree()
                .stream()
                .filter(tree -> !tree.children.isEmpty() && tree.parent != null)
                .collect(Collectors.toList());

        List<Tree<E>>subtreesWithGivenSum=new ArrayList<>();
        for (Tree<E> subtree : subtrees) {
            List<E> result=new ArrayList<>();
            dfs(subtree, result);
            int currentSum = result.stream().mapToInt(num -> (int) num).sum();
            if (currentSum==sum){
                subtreesWithGivenSum.add(subtree);
            }

            }

        return subtreesWithGivenSum;
    }




    private void dfs(Tree<E> node, List<E> result) {
        for (Tree<E> child : node.children) {
            this.dfs(child, result);
        }
        result.add(node.key);

    }

}
