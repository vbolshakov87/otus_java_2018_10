package ru.otus.l022;

import java.util.Arrays;

class OptimizedStack implements IStack {
    private int[] arr;
    private int size;
    private int length;

    OptimizedStack() {
        size = 2;
        arr = new int[size];
    }

    @Override
    public void push(int x) {
        if (length >= size) {
            size = length * 2;
            arr = Arrays.copyOf(arr, size);
        }
        arr[length++] = x;
    }

    @Override
    public int pop() {
        if (length == 0) {
            throw new RuntimeException("Stack has no elements");
        }
        // при желании можно уменьшать размер arr в pop и освобождать память.
        // Но в моем юзкейсе (сначала только push, затем только pop) только увелечит время выполения

        return arr[--length];
    }
}
