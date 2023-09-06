package org.zan.app.exception;

/**
 * This class is used to represent an unexpected or unauthorized error
 * that has occurred and cannot be corrected without modifying the code.
 *
 * @author Muhammad Fauzan
 */
public class SampleAppException extends RuntimeException {
    public SampleAppException(String message) {
        super("Sample App Exception: " + message);
    }
}
