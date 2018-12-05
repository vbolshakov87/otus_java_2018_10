package ru.otus.l031;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyArrayListTest {
    private final int maxSize = 1_000;
    private Integer[] input;
    private MyArrayList<Integer> list;

    public MyArrayListTest() {
        input = new Integer[maxSize];
        for (int i = 0; i < maxSize; i++) {
            input[i] = i + 23;
        }

        list = new MyArrayList<>();
    }

    @Test
    public void testAddAll() {
        list.addAll(Arrays.asList(input).subList(0, maxSize));
        // contains
        for (int i = 0; i < input.length; i++) {
            Assert.assertTrue(list.contains(input[i]));
            Assert.assertEquals(input[i], list.get(i));
        }

        // check to array
        Object[] arr = list.toArray();
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(input[i], arr[i]);
        }

        MyArrayList<Integer> listCloned = (MyArrayList<Integer>) list.clone();
        Assert.assertEquals(listCloned.size(), list.size());
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(listCloned.get(i), list.get(i));
        }

        int addToOriginal = 123;
        for (int i = 0; i < list.size(); i++) {
            list.set(i, addToOriginal + list.get(i));
            Assert.assertNotEquals(listCloned.get(i), list.get(i));

            int diff = list.get(i) - listCloned.get(i);
            Assert.assertEquals(addToOriginal, diff);
        }
    }

    @Test
    public void testaddAllIndex() {
        list.addAll(Arrays.asList(input).subList(0, maxSize));
        List listToAdd = new MyArrayList<Integer>();
        listToAdd.add(-1);
        listToAdd.add(-2);
        listToAdd.add(-3);

        list.addAll(5, listToAdd);
        Assert.assertEquals(5, list.indexOf(-1));
        Assert.assertEquals(6, list.indexOf(-2));
        Assert.assertEquals(7, list.indexOf(-3));
        Assert.assertEquals(-1, (int) list.get(5));
        Assert.assertEquals(-2, (int) list.get(6));
        Assert.assertEquals(-3, (int) list.get(7));
    }

    @Test
    public void testRemove() {
        list.addAll(Arrays.asList(input).subList(0, maxSize));

        // remove not exist
        Integer removed = -1;
        Assert.assertFalse(list.remove(removed));

        // remove exists
        removed = input[2];
        Assert.assertTrue(list.remove(removed));
        Assert.assertEquals(input.length - 1, list.size());
        Assert.assertFalse(list.contains(removed));
    }

    @Test
    public void testReplace() {
        list.addAll(Arrays.asList(input).subList(0, maxSize));

        list.set(3, 1111);
        Assert.assertEquals(1111, (int) list.get(3));
    }

    @Test
    public void testRemoveAll() {
        list.addAll(Arrays.asList(input).subList(0, maxSize));
        Assert.assertFalse(list.isEmpty());
        list.clear();
        Assert.assertTrue(list.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAdd() {
        list.add(1);
        Assert.assertFalse(list.isEmpty());
        Assert.assertEquals(1, list.size());

        list.add(0, 2);
        Assert.assertEquals(2, list.size());
        Assert.assertEquals(1, (int) list.get(1));
        Assert.assertEquals(2, (int) list.get(0));

        list.add(3, 3);
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void testSort() {
        list.addAll(Arrays.asList(input).subList(0, maxSize));
        for (Integer anInput : input) {
            list.add(anInput * (-1));
        }

        list.sort(Integer::compareTo);
        Assert.assertEquals(input.length * 2, list.size());
        for (int i = 0; i < list.size(); i++) {
            Integer expected;
            if (i < input.length) {
                expected = input[input.length - 1 - i] * (-1);
            } else {
                expected = input[i - input.length];
            }
            Assert.assertEquals(expected, list.get(i));
        }
    }

    @Test
    public void testAddAllCollection() {
        List arrlist = new MyArrayList();

        for (int i = 0; i < maxSize/3; i++) {
            arrlist.add("A");
            arrlist.add("B");
            arrlist.add("C");
            boolean b = Collections.addAll(arrlist, "1","2","3");
            Assert.assertTrue(b);
        }

        int expectedSize = (maxSize - maxSize%3) * 2;
        Assert.assertEquals(expectedSize, arrlist.size());
        Assert.assertEquals("A", arrlist.get(0));
        Assert.assertEquals("3", arrlist.get(arrlist.size()-1));
    }

    @Test
    public void testCopyCollection() {
        List<String> srclst = new MyArrayList<String>();
        List<String> destlst = new MyArrayList<String>();

        for (int i = 0; i < maxSize/3; i++) {
            srclst.add("Java");
            srclst.add("is");
            srclst.add("best");

            destlst.add("C++");
            destlst.add("is");
            destlst.add("not");
        }

        Collections.copy(destlst, srclst);
        Assert.assertEquals(srclst.toString(), destlst.toString());
    }

    @Test
    public void testCollectionSort() {
        list.addAll(Arrays.asList(input).subList(0, maxSize));
        for (Integer anInput : input) {
            list.add(anInput * (-1));
        }

        // copy into dest list
        Collections.sort(list);

        Assert.assertEquals(input.length * 2, list.size());
        for (int i = 0; i < list.size(); i++) {
            Integer expected;
            if (i < input.length) {
                expected = input[input.length - 1 - i] * (-1);
            } else {
                expected = input[i - input.length];
            }
            Assert.assertEquals(expected, list.get(i));
        }
    }
}
