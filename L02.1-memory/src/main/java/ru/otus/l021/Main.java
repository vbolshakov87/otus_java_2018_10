package ru.otus.l021;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        try {
            Benchmark benchmark = new Benchmark(20_000_000);

            System.out.println("Empty String");
            benchmark.run(()->new String(""));

            System.out.println("Empty String from chars");
            benchmark.run(()->new String(new char[0]));

            System.out.println("Empty String from bytes");
            benchmark.run(()->new String(new byte[0]));

            System.out.println("Integer");
            benchmark.run(() -> new Integer(0));

            System.out.println("MyClass");
            benchmark.run(()->new MyClass());


            Benchmark benchmark2 = new Benchmark(1_000_000);
            System.out.println("Integer List with 10 elements");
            benchmark2.run(() -> getIntegerList(1), 1);

            System.out.println("Integer List with 10 elements");
            benchmark2.run(() -> getIntegerList(10), 10);

            System.out.println("Integer List with 100 elements");
            benchmark2.run(() -> getIntegerList(100), 100);
        } catch (OutOfMemoryError e) {
            System.out.println("Not Enough memory");
        } catch (InterruptedException e) {
            System.out.printf("Interrupted exception happened, message: %s\n", e.getMessage());
        }

    }

    private static Object getIntegerList(int size) {
        List<Integer> integerList = new ArrayList();
        for (int i = 0; i < size; i++) {
            integerList.add(new Integer(0));
        }

        return integerList;
    }
}
