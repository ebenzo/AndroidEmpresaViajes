package com.example.androidempresaviajes.model;

import androidx.annotation.NonNull;

public class Empresa {

    private Integer idEmpresa;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String cuit;
    private String password;

    /**
     * No args constructor for use in serialization
     *
     */
    public Empresa() {
    }

    public Empresa(Integer idEmpresa, String nombre, String direccion, String telefono, String email, String cuit, String password) {
        super();
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.cuit = cuit;
        this.password = password;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}