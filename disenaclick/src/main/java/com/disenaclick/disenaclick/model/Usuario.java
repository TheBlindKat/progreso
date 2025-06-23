package com.disenaclick.disenaclick.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 50, nullable = false)
    private String correo;

    @JsonIgnore
    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String nombres;

    @Column(length = 30, nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private String paginaUsuario;

    @ManyToOne
    @JoinColumn(name = "rol", nullable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "rolUsuario", nullable = false)
    private RolUsuario rolUsuario;

    @ManyToOne
    @JoinColumn(name = "plantilla_id", nullable = false)
    private Plantilla plantilla;

    /*
     * @OneToMany
     * 
     * @JoinColumn(name = "nombrePagina", nullable = false)
     * private List<Pagina> pagina;
     */

    @OneToMany(mappedBy = "usuario")
    private List<Pagina> paginas;

}
