package com.trendrr.beanstalk;

/**
 * @author dustin
 */
public class BeanstalkException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 5427161060543990905L;

    public BeanstalkException() {
        this(null, null);
    }

    BeanstalkException(String message) {
        this(message, null);
    }

    BeanstalkException(String message, Exception cause) {
        super(message, cause);
    }

    BeanstalkException(Exception cause) {
        this(null, cause);
    }
}
