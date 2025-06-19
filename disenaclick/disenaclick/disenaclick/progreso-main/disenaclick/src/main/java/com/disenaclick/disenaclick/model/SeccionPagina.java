package com.disenaclick.disenaclick.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seccionpagina")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SeccionPagina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 500, nullable = false)
    private String parrafo;

    /*
     * @Column(nullable = false)
     * private aqui va el de img que se habilitara en el futuro
     */

}