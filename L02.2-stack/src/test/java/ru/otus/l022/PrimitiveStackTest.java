package ru.otus.l022;

import org.junit.Assert;
import org.junit.Test;

public class PrimitiveStackTest {

    @Test
    public void testStackWithData() {
        IStack stack = new PrimitiveStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Assert.assertEquals(stack.pop(), 3);
        Assert.assertEquals(stack.pop(), 2);
        Assert.assertEquals(stack.pop(), 1);
    }

    @Test
    public void testStackWithDataOperationsMix() {
        IStack stack = new PrimitiveStack();
        stack.push(1);
        stack.push(2);
        Assert.assertEquals(stack.pop(), 2);
        stack.push(3);
        Assert.assertEquals(stack.pop(), 3);
        stack.push(4);
        stack.push(5);
        Assert.assertEquals(stack.pop(), 5);
        Assert.assertEquals(stack.pop(), 4);
        Assert.assertEquals(stack.pop(), 1);
    }

    @Test (expected = RuntimeException.class)
    public void testPopNoPush() {
        IStack stack = new PrimitiveStack();
        stack.pop();
    }
}
