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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anton
 */
@Entity
@Table(name = "COMENTARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comentarios.findAll", query = "SELECT c FROM Comentarios c"),
    @NamedQuery(name = "Comentarios.findById", query = "SELECT c FROM Comentarios c WHERE c.id = :id"),
    @NamedQuery(name = "Comentarios.findByValoracion", query = "SELECT c FROM Comentarios c WHERE c.valoracion = :valoracion")})
public class Comentarios implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "VALORACION")
    private int valoracion;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Lob
    @Column(name = "MENSAJE")
    private String mensaje;
    @JoinColumn(name = "USUARIO", referencedColumnName = "USUARIO")
    @ManyToOne(optional = false)
    private Credenciales usuario;
    @JoinColumn(name = "PELICULA_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Peliculas peliculaId;

    public Comentarios() {
    }

    public Comentarios(Integer id) {
        this.id = id;
    }

    public Comentarios(Integer id, int valoracion) {
        this.id = id;
        this.valoracion = valoracion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


    public Credenciales getUsuario() {
        return usuario;
    }

    public void setUsuario(Credenciales usuario) {
        this.usuario = usuario;
    }

    public Peliculas getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(Peliculas peliculaId) {
        this.peliculaId = peliculaId;
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
        if (!(object instanceof Comentarios)) {
            return false;
        }
        Comentarios other = (Comentarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.daw.paniculas.Comentarios[ id=" + id + " ]";
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }
    
}
