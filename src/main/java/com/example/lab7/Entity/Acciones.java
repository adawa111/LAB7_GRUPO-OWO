package com.example.lab7.Entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "acciones", schema = "mydb", catalog = "")
public class Acciones {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "fecha")
    private Timestamp fecha;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuarios_id", nullable = false)
    private Usuarios usuarios;



}
