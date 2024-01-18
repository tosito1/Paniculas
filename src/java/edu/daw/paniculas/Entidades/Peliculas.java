/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.daw.paniculas.Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author anton
 */
@Entity
@Table(name = "PELICULAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peliculas.findAll", query = "SELECT p FROM Peliculas p"),
    @NamedQuery(name = "Peliculas.findById", query = "SELECT p FROM Peliculas p WHERE p.id = :id"),
    @NamedQuery(name = "Peliculas.findByNombre", query = "SELECT p FROM Peliculas p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Peliculas.findByCategoria", query = "SELECT p FROM Peliculas p WHERE p.categoria = :categoria"),
    @NamedQuery(name = "Peliculas.findByAnio", query = "SELECT p FROM Peliculas p WHERE p.anio = :anio"),
    @NamedQuery(name = "Peliculas.findByValoracion", query = "SELECT p FROM Peliculas p WHERE p.valoracion = :valoracion"),
    @NamedQuery(name = "Peliculas.findByDirector", query = "SELECT p FROM Peliculas p WHERE p.director = :director"),
    @NamedQuery(name = "Peliculas.findByImagen", query = "SELECT p FROM Peliculas p WHERE p.imagen = :imagen")})
public class Peliculas implements Serializable {

    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 50)
    @Column(name = "CATEGORIA")
    private String categoria;
    @Size(max = 100)
    @Column(name = "DIRECTOR")
    private String director;
    @Size(max = 255)
    @Column(name = "IMAGEN")
    private String imagen;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ANIO")
    private Integer anio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALORACION")
    private BigDecimal valoracion;
    @Lob
    @Column(name = "SINOPSIS")
    private String sinopsis;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peliculaId")
    private Set<Comentarios> comentariosSet;

    public Peliculas() {
    }

    public Peliculas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public BigDecimal getValoracion() {
        return valoracion;
    }

    public void setValoracion(BigDecimal valoracion) {
        this.valoracion = valoracion;
    }


    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }


    @XmlTransient
    public Set<Comentarios> getComentariosSet() {
        return comentariosSet;
    }

    public void setComentariosSet(Set<Comentarios> comentariosSet) {
        this.comentariosSet = comentariosSet;
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
        if (!(object instanceof Peliculas)) {
            return false;
        }
        Peliculas other = (Peliculas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.daw.paniculas.Peliculas[ id=" + id + " ]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
}
