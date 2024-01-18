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
@Table(name = "DATOSADICIONALES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datosadicionales.findAll", query = "SELECT d FROM Datosadicionales d"),
    @NamedQuery(name = "Datosadicionales.findById", query = "SELECT d FROM Datosadicionales d WHERE d.id = :id"),
    @NamedQuery(name = "Datosadicionales.findByEmail", query = "SELECT d FROM Datosadicionales d WHERE d.email = :email"),
    @NamedQuery(name = "Datosadicionales.findByDireccion", query = "SELECT d FROM Datosadicionales d WHERE d.direccion = :direccion"),
    @NamedQuery(name = "Datosadicionales.findByCodigoPostal", query = "SELECT d FROM Datosadicionales d WHERE d.codigoPostal = :codigoPostal"),
    @NamedQuery(name = "Datosadicionales.findByTelefono", query = "SELECT d FROM Datosadicionales d WHERE d.telefono = :telefono")})
public class Datosadicionales implements Serializable {

    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DIRECCION")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODIGO_POSTAL")
    private String codigoPostal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TELEFONO")
    private String telefono;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "USUARIO", referencedColumnName = "USUARIO")
    @ManyToOne(optional = false)
    private Credenciales usuario;

    public Datosadicionales() {
    }

    public Datosadicionales(Integer id) {
        this.id = id;
    }

    public Datosadicionales(Integer id, String email, String direccion, String codigoPostal, String telefono) {
        this.id = id;
        this.email = email;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
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
        if (!(object instanceof Datosadicionales)) {
            return false;
        }
        Datosadicionales other = (Datosadicionales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.daw.paniculas.Datosadicionales[ id=" + id + " ]";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
