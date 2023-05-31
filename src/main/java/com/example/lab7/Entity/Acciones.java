package com.example.lab7.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "acciones")
public class Acciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "fecha")
    private String fecha;

    @ManyToOne
    @JoinColumn(name = "usuarios_id",nullable = false)
    private Usuarios usuariosId;


}
