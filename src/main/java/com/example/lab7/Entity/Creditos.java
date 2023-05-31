package com.example.lab7.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "creditos")
public class Creditos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "monto")
    private String monton;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "interes")
    private Double interes;

    @ManyToOne
    @JoinColumn(name = "usuarios_id",nullable = false)
    private Usuarios usuariosId;

}
