package com.cursojava.curso.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter @Getter @Column(name = "id")
    private int id;
    @Setter @Getter @Column(name = "nombre")
    private String nombre;
    @Setter @Getter @Column(name = "apellido")
    private String apellido;
    @Setter @Getter @Column(name = "email")
    private String email;
    @Setter @Getter @Column(name = "telefono")
    private String telefono;
    @Setter @Getter @Column(name = "password")
    private String password;


    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellido, String email, String telefono, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
    }

}
