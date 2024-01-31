import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {


    private BinarySearchTree<Integer> bst;
    private BinarySearchTree<Integer> bstLeft;
    private BinarySearchTree<Integer> bstMoreLeft;
    private BinarySearchTree<Integer> bstRight;

    @Before
    public void setUp() {
        bst = new BinarySearchTree<>(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(6);
        bst.insert(1);
        bst.insert(17);
    }

    @Test
    public void testLeftSideBST() {

        BinarySearchTree.Node<Integer> root = bst.getRoot();

        assertEquals(Integer.valueOf(5), root.getValue());

        BinarySearchTree.Node<Integer> left = root.getLeft();

        assertEquals(Integer.valueOf(3), left.getValue());

        BinarySearchTree.Node<Integer> left_left = left.getLeft();


        assertEquals(Integer.valueOf(1), left_left.getValue());

    }

    @Test
    public void testEachInOrder() {
        List<Integer> elements = new ArrayList<>();

        bst.eachInOrder(e -> elements.add(e));

        List<Integer> expected = new ArrayList<>(
                Arrays.asList(1, 3, 5, 6, 7, 17)
        );

        assertEquals(expected.size(), elements.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), elements.get(i));
        }
    }

    @Test
    public void testContains() {
        assertTrue(bst.contains(5));
        assertTrue(bst.contains(1));
        assertFalse(bst.contains(12));

    }

    @Test
    public void search() {
        BinarySearchTree<Integer> search = bst.search(7);
        bst.insert(8);
        assertEquals(bst.search(7).getRoot().getValue(), Integer.valueOf(7));
        assertEquals(bst.search(6).getRoot().getValue(), Integer.valueOf(6));

        assertFalse(search.contains(8));
        assertTrue(bst.contains(8));

    }

    @Test
    public void testRange() {
        List<Integer> range = bst.range(3, 7);
        List<Integer> expected = Arrays.asList(3, 5, 6, 7);

        assertEquals(4, range.size());
        for (int i = 0; i < range.size(); i++) {
            assertTrue(range.contains(expected.get(i)));
        }

    }

    @Test
    public void testDeleteMin() {
        assertTrue(bst.contains(1));
        bst.deleteMin();
        assertFalse(bst.contains(1));


    }

    @Test
    public void testDeleteMax() {
        assertTrue(bst.contains(17));
        bst.deleteMax();
        assertFalse(bst.contains(17));
    }

    @Test
    public void testCount() {
        assertEquals(6, bst.count());
        bst.deleteMin();
        assertEquals(5, bst.count());
        bst.insert(11);
        assertEquals(6, bst.count());
        bst.deleteMax();
        assertEquals(5, bst.count());
    }

    @Test
    public void testRank() {
        assertEquals(4, bst.rank(7));
    }

    @Test
    public void testFloor() {
        assertEquals(Integer.valueOf(6), bst.floor(7));
    }

    @Test
    public void testEmptyFloor() {
        assertNull(bst.floor(-1));
    }

    @Test
    public void testCeil() {
        assertEquals(Integer.valueOf(7), bst.ceil(6));
    }
    @Test
    public void testEmptyCeil() {
        assertNull(bst.ceil(20));
    }

}
