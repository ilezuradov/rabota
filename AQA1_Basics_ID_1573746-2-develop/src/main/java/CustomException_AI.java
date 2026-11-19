package com.school21;

/**
 * ai-assisted-solution - реализация исключения с помощью ИИ
 */
public class CustomException_AI extends RuntimeException {

    private final String errorCode;

    public CustomException_AI(String message) {
        super(message);
        this.errorCode = "UNKNOWN";
    }

    public CustomException_AI(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "UNKNOWN";
    }

    public CustomException_AI(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return String.format("CustomException_AI [errorCode=%s, message=%s]",
            errorCode, getMessage());
    }
}
