package com.bankingapp.service;

import java.util.regex.Pattern;

public class ValidationUtil {
    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE = Pattern.compile("^[0-9]{10}$");
    private static final Pattern NAME = Pattern.compile("^[A-Za-z ]+$"); // only letters + spaces
    private static final Pattern NUMBER = Pattern.compile("^[0-9]+$");   // only digits (integer)

    public static void require(boolean cond, String message) {
        if (!cond) throw new IllegalArgumentException(message);
    }

    public static void validateEmail(String email) {
        require(email != null && EMAIL.matcher(email).matches(), "Invalid email");
    }

    public static void validatePhone(String phone) {
        require(phone == null || phone.isEmpty() || PHONE.matcher(phone).matches(), "Invalid phone");
    }

    public static void validateNotBlank(String v, String field) {
        require(v != null && !v.trim().isEmpty(), field + " is required");
    }

    // ✅ New: Only alphabets and spaces allowed
    public static void validateName(String name) {
        validateNotBlank(name, "Name");
        require(NAME.matcher(name).matches(), "Name must contain only letters and spaces");
    }

    // ✅ New: Only integer values allowed
    public static void validateInteger(String number, String field) {
        validateNotBlank(number, field);
        require(NUMBER.matcher(number).matches(), field + " must be an integer");
    }

    // ✅ New: Positive amount check
    public static void validateAmount(double amount, String field) {
        require(amount > 0, field + " must be greater than 0");
    }
}

