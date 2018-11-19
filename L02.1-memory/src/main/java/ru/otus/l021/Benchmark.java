package ru.otus.l021;

import java.util.List;
import java.util.function.Supplier;

class Benchmark {

    private int size;
    private Object[] arr;

    Benchmark(int size) {
        this.size = size;
    }

    void run(Supplier<Object> supplier) throws InterruptedException {
        measure(supplier, 1);
    }

    void run(Supplier<Object> supplier, int listSize) throws InterruptedException {
        measure(supplier, listSize);
    }

    private void measure(Supplier<Object> supplier, int listSize) throws InterruptedException {
        arr = new Object[size];

        long memStart = getMem();
        arr = populateArray(supplier);
        long arrSize = getMem() - memStart;

        long elementSize = Math.round((double) (getMem() - memStart) / size);
        System.out.printf("size: %d bytes\n", elementSize);

        if (listSize > 1) {
            String name = ((List) arr[0]).get(0).getClass().getSimpleName();
            long size = Math.round((double) arrSize / (arr.length * listSize));
            System.out.printf("%s size: %d bytes\n", name, size);
        }

        arr = null;
        Thread.sleep(1000);

        System.out.println("-----------------");
        System.out.println();
    }

    private Object[] populateArray(Supplier<Object> supplier) {
        String type = supplier.get().getClass().getSimpleName();
        switch (type) {
            case "Integer":
                for (int i = 0; i < size; i++) arr[i] = (int) supplier.get();
                break;
            case "Short":
                for (int i = 0; i < size; i++) arr[i] = (short) supplier.get();
                break;
            case "Byte":
                for (int i = 0; i < size; i++) arr[i] = (byte) supplier.get();
                break;
            case "Long":
                for (int i = 0; i < size; i++) arr[i] = (long) supplier.get();
                break;
            case "Float":
                for (int i = 0; i < size; i++) arr[i] = (float) supplier.get();
                break;

            default: for (int i = 0; i < size; i++) arr[i] = supplier.get();
        }

        return arr;
    }

    private long getMem() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
