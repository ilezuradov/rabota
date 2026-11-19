package com.school21.utils;

public class RetryUtils {
    
    private static final int MAX_RETRIES = 3;
    private static final int INITIAL_DELAY_MS = 3000;
    
    @FunctionalInterface
    public interface RetryableTask {
        void execute() throws Exception;
    }
    
    public static void withRetry(RetryableTask task) throws Exception {
        int retries = 0;
        long delay = INITIAL_DELAY_MS;
        
        while (true) {
            try {
                task.execute();
                return;
            } catch (Exception e) {
                retries++;
                if (retries >= MAX_RETRIES) {
                    throw e;
                }
                System.out.println("Retry " + retries + " after " + delay + "ms delay. Error: " + e.getMessage());
                Thread.sleep(delay);
                delay *= 2; // exponential backoff
            }
        }
    }
}
