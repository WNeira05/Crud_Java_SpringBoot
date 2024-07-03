package com.example.CRUD.model;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String apellido;
    private String correo;



}
