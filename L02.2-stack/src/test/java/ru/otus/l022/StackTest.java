package ru.otus.l022;

import org.junit.Assert;
import org.junit.Test;

public class StackTest {

    @Test
    public void testWithData() {
        testStackWithData(new PrimitiveStack());
        testStackWithData(new WrapperStack());
        testStackWithData(new OptimizedStack(10));
        testStackWithData(new OptimizedStack(100));
        testStackWithData(new OptimizedStack(1000));
    }

    private void testStackWithData(IStack stack) {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Assert.assertEquals(stack.pop(), 3);
        Assert.assertEquals(stack.pop(), 2);
        Assert.assertEquals(stack.pop(), 1);
    }

    @Test
    public void testWithDataOperationsMix() {
        testStackWithDataOperationsMix(new PrimitiveStack());
        testStackWithDataOperationsMix(new WrapperStack());
        testStackWithDataOperationsMix(new OptimizedStack(10));
    }

    private void testStackWithDataOperationsMix(IStack stack) {
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
    public void testStackPopNoPush() {
        testPopNoPush(new PrimitiveStack());
        testPopNoPush(new WrapperStack());
        testPopNoPush(new OptimizedStack(10));
    }

    private void testPopNoPush(IStack stack ) {
        stack.pop();
    }
}
