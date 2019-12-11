package com.example.androidempresaviajes.model;

public class LoginView {
    private String Email;
    private String Password;

    public LoginView(String email, String password) {
        Email = email;
        Password = password;
    }

    public LoginView() {
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
