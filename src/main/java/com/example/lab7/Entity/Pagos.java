package com.example.lab7.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pagos")
public class Pagos {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "tipo_pago")
    private String tipoPago;

    @Column(name = "fecha")
    private String fecha;

    @ManyToOne
    @JoinColumn(name = "usuarios_id",nullable = false)
    private Usuarios usuariosId;

    @ManyToOne
    @JoinColumn(name = "creditos_id")
    private Creditos creditosId;
}
