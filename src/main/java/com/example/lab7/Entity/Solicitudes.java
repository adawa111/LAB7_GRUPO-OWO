package com.example.lab7.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "solicitudes")
public class Solicitudes {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "solicitud_producto")
    private String solicitudProducto;

    @Column(name = "solicitud_monto")
    private Double solicitudMonto;

    @Column(name = "solicitud_fecha")
    private String solicitudFecha;

    @Column(name = "solicitud_estado")
    private String solicitudEstado;

    @ManyToOne
    @JoinColumn(name = "usuarios_id",nullable = false)
    private Usuarios usuariosId;


}
