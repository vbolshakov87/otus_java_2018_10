package ru.otus.l021;

public class Main {
    public static void main(String[] args)  {
        try {
            Benchmark benchmark = new Benchmark(20_000_000);

            System.out.println("Empty String from chars");
            benchmark.run(()->new String(new char[0]));

            System.out.println("Empty String from bytes");
            benchmark.run(()->new String(new byte[0]));

            System.out.println("Integer");
            benchmark.run(()->new Integer("1"));

            System.out.println("Short");
            benchmark.run(()->new Short("1"));

            System.out.println("Byte");
            benchmark.run(()->new Byte("1"));

            System.out.println("Float");
            benchmark.run(()->new Float("1.0"));

            System.out.println("MyClass");
            benchmark.run(()-> new MyClass());
        } catch (OutOfMemoryError e) {
            System.out.println("Not Enough memory");
        } catch (InterruptedException e) {
            System.out.printf("Interrupted exception happened, message: %s\n", e.getMessage());
        }

    }
}
