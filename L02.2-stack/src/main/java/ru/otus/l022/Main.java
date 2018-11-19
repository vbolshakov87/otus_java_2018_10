package ru.otus.l022;

public class Main {
    public static void main(String[] args)  {
        try {
            int size = 100_000;
            benchmarkStack(size, new PrimitiveStack());
            benchmarkStack(size, new WrapperStack());
            benchmarkStack(size, new OptimizedStack(size));
        } catch (InterruptedException e) {
            System.out.printf("Interrupted exception happened, message: %s\n", e.getMessage());
        }
    }

    private static void benchmarkStack(int size, IStack stack) throws InterruptedException {
        System.out.printf("Check %s", stack.getClass().getSimpleName());

        long startTime = System.currentTimeMillis();
        long memStart = getMem();

        // push
        for (int i = 0; i < size; i++) {
            stack.push(size);
        }
        long memStackFull = getMem();
        System.out.println("push finished");

        // pop
        for (int i = 0; i < size; i++) {
            stack.pop();
        }
        System.out.println("pop finished");

        stack = null;
        long elapsedTime = System.currentTimeMillis() - startTime;
        long memoryUsed = memStackFull - memStart;
        Thread.sleep(1000); //wait for 1 sec

        System.out.printf("Ref size: %d bytes\nExecution time: %d ms\n--------------\n", memoryUsed, elapsedTime);
    }

    private static long getMem() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
