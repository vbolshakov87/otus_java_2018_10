package ru.otus.l041;

import ru.otus.l041.framework.Assert;
import ru.otus.l041.framework.annotations.After;
import ru.otus.l041.framework.annotations.Before;
import ru.otus.l041.framework.annotations.Test;

public class MyItemsTest {
    private MyItems<Integer> myItemsInt;

    @Before(message = "runs before my test")
    public void start() {
        System.out.println("Here is my before method");
        myItemsInt = new MyItems<Integer>();
    }

    @Test
    public void testAdd() {
        myItemsInt.add(1).add(2).add(3);
        Assert.assertTrue(myItemsInt.size() == 3);
    }

    @Test
    public void testRemove() {
        myItemsInt.add(1).add(2).add(3).remove(3).remove(2).remove(1);
        Assert.assertTrue(myItemsInt.size() == 0);
    }

    @After(message = "runs after my test")
    public void finish() {
        System.out.println("Here is my after method");
    }
}
