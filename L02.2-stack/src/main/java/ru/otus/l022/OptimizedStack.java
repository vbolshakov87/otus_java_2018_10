package ru.otus.l022;

class OptimizedStack implements IStack {
    private int[] arr;
    private int length = 0;

    OptimizedStack(int size) {
        arr = new int[size];
    }

    public void push(int x) {
        arr[length] = x;
        length ++;
    }

    public int pop() {
        if (length == 0) {
            throw new RuntimeException("Stack has no elements");
        }

        length--;

        return arr[length];
    }
}
