package ru.otus.l022;

import java.util.Arrays;

class PrimitiveStack implements IStack {
    private int[] arr;

    PrimitiveStack() {
        arr = new int[0];
    }

    @Override
    public void push(int x) {
        int length = arr.length;
        arr = Arrays.copyOf(arr, length + 1);
        arr[length] = x;
    }

    @Override
    public int pop() {
        int length = arr.length;
        if (length == 0) {
            throw new RuntimeException("Stack has no elements");
        }

        int last = arr[length-1];
        arr = Arrays.copyOf(arr, length-1);

        return last;
    }
}
