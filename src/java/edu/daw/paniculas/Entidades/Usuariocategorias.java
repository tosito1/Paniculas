/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.daw.paniculas.Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anton
 */
@Entity
@Table(name = "USUARIOCATEGORIAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuariocategorias.findAll", query = "SELECT u FROM Usuariocategorias u"),
    @NamedQuery(name = "Usuariocategorias.findById", query = "SELECT u FROM Usuariocategorias u WHERE u.id = :id"),
    @NamedQuery(name = "Usuariocategorias.findByCategoria", query = "SELECT u FROM Usuariocategorias u WHERE u.categoria = :categoria")})
public class Usuariocategorias implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CATEGORIA")
    private String categoria;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "USUARIO", referencedColumnName = "USUARIO")
    @ManyToOne(optional = false)
    private Credenciales usuario;

    public Usuariocategorias() {
    }

    public Usuariocategorias(Integer id) {
        this.id = id;
    }

    public Usuariocategorias(Integer id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Credenciales getUsuario() {
        return usuario;
    }

    public void setUsuario(Credenciales usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuariocategorias)) {
            return false;
        }
        Usuariocategorias other = (Usuariocategorias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.daw.paniculas.Usuariocategorias[ id=" + id + " ]";
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
}
