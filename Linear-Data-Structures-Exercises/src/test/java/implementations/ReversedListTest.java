package implementations;

import org.junit.Before;
import org.junit.Test;
import org.openjdk.jmh.annotations.Setup;

import static org.junit.Assert.*;

public class ReversedListTest {
    private ReversedList<Integer> reversedList;
    @Before
    public void setUp() {
         reversedList = new ReversedList<>();
        reversedList.add(1);
        reversedList.add(2);
        reversedList.add(3);
        reversedList.add(4);
        reversedList.add(5);
    }

    @Test
    public void testAdd() {

        assertEquals(5, reversedList.size());
    }
    @Test
    public void testGet() {

        assertEquals(Integer.valueOf(5),reversedList.get(0));
        assertEquals(Integer.valueOf(4),reversedList.get(1));
        assertEquals(Integer.valueOf(3),reversedList.get(2));
        assertEquals(Integer.valueOf(2),reversedList.get(3));
        assertEquals(Integer.valueOf(1),reversedList.get(4));
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetShouldThrow() {

        assertEquals(Integer.valueOf(1),reversedList.get(5));
    }
    @Test
    public void testDelete() {
        assertEquals(Integer.valueOf(5),reversedList.removeAt(0));
        assertEquals(Integer.valueOf(4),reversedList.removeAt(0));
        assertEquals(Integer.valueOf(3),reversedList.removeAt(0));
        assertEquals(Integer.valueOf(2),reversedList.removeAt(0));
        assertEquals(Integer.valueOf(1),reversedList.removeAt(0));
        assertEquals(0, reversedList.size());
    }
    @Test
    public void testDeleteRandom() {
        assertEquals(Integer.valueOf(3),reversedList.removeAt(2));
        assertEquals(4, reversedList.size());
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeleteShouldThrow() {
        assertEquals(Integer.valueOf(1),reversedList.removeAt(5));
    }
    @Test
    public void testIterator() {
        System.out.println(reversedList.capacity());
        for (Integer i : reversedList) {
            System.out.println(i);
        }
    }
}