package ru.otus.l041.framework.exceptions;

public class TestRunException extends TestException {
    private static final long serialVersionUID = 6700697376100628474L;

    /**
     * Constructs an <code>InterruptedException</code> with no detail  message.
     */
    public TestRunException() {
        super();
    }

    /**
     * Constructs an <code>InterruptedException</code> with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public TestRunException(String s) {
        super(s);
    }
}
