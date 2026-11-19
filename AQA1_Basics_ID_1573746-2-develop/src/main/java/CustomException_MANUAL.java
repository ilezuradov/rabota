package com.school21;

/**
 * manual-solution - ручная реализация исключения для задания 5
 */
public class CustomException_MANUAL extends RuntimeException {

    public CustomException_MANUAL(String message) {
        super(message);
    }

    public CustomException_MANUAL(String message, Throwable cause) {
        super(message, cause);
    }
}
