package ru.otus.l022;

import java.util.Arrays;

class PrimitiveStack implements IStack {
    private int[] arr;
    private int length = 0;

    public void push(int x) {
        if (length == 0) {
            length++;
            arr = new int[]{x};
            return;
        }
        arr = Arrays.copyOf(arr, length + 1);
        arr[length] = x;
        length ++;
    }

    public int pop() {
        if (length == 0) {
            throw new RuntimeException("Stack has no elements");
        }

        int last = arr[length-1];
        arr = Arrays.copyOf(arr, length-1);
        length--;

        return last;
    }
}
