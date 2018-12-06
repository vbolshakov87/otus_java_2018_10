package ru.otus.l041;

import ru.otus.l041.framework.Exceptions.TestException;
import ru.otus.l041.framework.TestClassRunner;

public class Main {
    public static void main(String[] args)  {
        try {
            TestClassRunner testRunner = new TestClassRunner(MyItemsTest.class);
            testRunner.run();
        } catch (TestException e) {
            System.err.printf("Test failed with error %s\n", e.getMessage());
        }
    }
}
