package com.school21;

public class Validator {
    
    /**
     * Валидирует строку формата "{name} {age} years"
     * @param input строка для валидации
     * @return true если строка валидна, иначе false
     */
    public static boolean validatePersonString(String input) {
        if (input == null || input.isEmpty()) return false;
        
        String[] parts = input.split(" ");
        if (parts.length != 3) return false;
        
        String name = parts[0];
        String ageStr = parts[1];
        String years = parts[2];
        
        // Имя должно содержать более 3 букв
        if (name.length() <= 3) return false;
        
        // Возраст должен быть > 18
        try {
            int age = Integer.parseInt(ageStr);
            if (age <= 18) return false;
        } catch (NumberFormatException e) {
            return false;
        }
        
        return "years".equals(years);
    }
    
    /**
     * Проверяет, является ли строка палиндромом
     * @param str строка для проверки
     * @return true если строка палиндром, иначе false
     */
    public static boolean isPalindrome(String str) {
        if (str == null) return false;
        String cleaned = str.replaceAll("\\s+", "").toLowerCase();
        if (cleaned.isEmpty()) return true;
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed);
    }
}
