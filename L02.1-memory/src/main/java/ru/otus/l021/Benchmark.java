package ru.otus.l021;

import java.util.function.Supplier;

class Benchmark {

    private int size;
    private Object[] arr;

    Benchmark(int size) {
        this.size = size;
    }

    void run(Supplier<Object> supplier) throws InterruptedException {
        String type = supplier.get().getClass().getSimpleName();
        switch (type) {
            case "Integer":
                runInt();
                break;
            case "Short":
                runShort();
                break;
            case "Byte":
                runByte();
                break;
            case "Long":
                runLong();
                break;
            case "Float":
                runFloat();
                break;

            default: runObject(supplier);
        }
    }


    private void runObject(Supplier<Object> supplier) throws InterruptedException {
        arr = new Object[size];
        long memStart = getMem();
        for (int i = 0; i < size; i++) {
            arr[i] = supplier.get();
        }

        measureAndOut(memStart);
    }

    private void measureAndOut(long memStart) throws InterruptedException {
        long elementSize = Math.round((double) (getMem() - memStart) / size);
        System.out.printf("size: %d bytes\n", elementSize);

        Thread.sleep(1000);

        System.out.println("-----------------\n");
    }

    private void runInt() throws InterruptedException {
        long memStart = getMem();
        int[] pArr = new int[size];
        for (int i = 0; i < size; i++) {
            pArr[i] = Integer.MAX_VALUE;
        }
        measureAndOut(memStart);
    }

    private void runShort() throws InterruptedException {
        long memStart = getMem();
        short[] pArr = new short[size];
        for (int i = 0; i < size; i++) {
            pArr[i] = Short.MAX_VALUE;
        }
        measureAndOut(memStart);
    }

    private void runLong() throws InterruptedException {
        long memStart = getMem();
        long[] pArr = new long[size];
        for (int i = 0; i < size; i++) {
            pArr[i] = Long.MAX_VALUE;
        }
        measureAndOut(memStart);
    }

    private void runByte() throws InterruptedException {
        long memStart = getMem();
        byte[] pArr = new byte[size];
        for (int i = 0; i < size; i++) {
            pArr[i] = Byte.MAX_VALUE;
        }
        measureAndOut(memStart);
    }

    private void runFloat() throws InterruptedException {
        long memStart = getMem();
        float[] pArr = new float[size];
        for (int i = 0; i < size; i++) {
            pArr[i] = 1.0f;
        }
        measureAndOut(memStart);
    }

    private long getMem() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
