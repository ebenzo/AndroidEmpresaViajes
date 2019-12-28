package com.example.androidempresaviajes.model;

public class ApiJsonResult {
    private Integer Errors;
    private String Message;

    public ApiJsonResult(Integer errors, String message) {
        Errors = errors;
        Message = message;
    }

    public Integer getErrors() {
        return Errors;
    }

    public void setErrors(Integer errors) {
        Errors = errors;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
