package ru.otus.l041.framework.Exceptions;

public class TestException extends InterruptedException {
    private static final long serialVersionUID = 6700697376100628474L;

    /**
     * Constructs an <code>InterruptedException</code> with no detail  message.
     */
    public TestException() {
        super();
    }

    /**
     * Constructs an <code>InterruptedException</code> with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public TestException(String s) {
        super(s);
    }
}
