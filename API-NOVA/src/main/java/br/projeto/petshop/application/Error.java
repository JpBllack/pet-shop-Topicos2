package br.projeto.petshop.application;


import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;

public class Error {

    private String code;
    private String message;
    private boolean success;

    public Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Error(String message, boolean success) {
        this.success = success;
        this.message = message;
    }

    public Error(Set<? extends ConstraintViolation<?>> violations) {
        this.success = false;
        this.message = violations.stream()
             .map(cv -> cv.getMessage())
             .collect(Collectors.joining(", "));
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}