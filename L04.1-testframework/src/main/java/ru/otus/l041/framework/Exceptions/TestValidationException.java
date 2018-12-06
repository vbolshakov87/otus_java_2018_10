package ru.otus.l041.framework.Exceptions;

public class TestValidationException extends TestException {
    private static final long serialVersionUID = 6700697376100628474L;

    /**
     * Constructs an <code>InterruptedException</code> with no detail  message.
     */
    public TestValidationException() {
        super();
    }

    /**
     * Constructs an <code>InterruptedException</code> with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public TestValidationException(String s) {
        super(s);
    }
}
