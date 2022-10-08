package com.example.registration.model;

public class Empleado {
    private Long id;
    private String email;
    private String nombre;
    private String password;

    public Empleado(Long id, String email, String nombre, String password) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.password = password;
    }
    public Empleado() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword(){return  password;}

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
