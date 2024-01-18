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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anton
 */
@Entity
@Table(name = "USUARIOROLES", catalog = "", schema = "TOSITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarioroles.findAll", query = "SELECT u FROM Usuarioroles u"),
    @NamedQuery(name = "Usuarioroles.findById", query = "SELECT u FROM Usuarioroles u WHERE u.id = :id")})
public class Usuarioroles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "USUARIO", referencedColumnName = "USUARIO")
    @ManyToOne(optional = false)
    private Credenciales usuario;
    @JoinColumn(name = "ROL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Roles rolId;

    public Usuarioroles() {
    }

    public Usuarioroles(Integer id) {
        this.id = id;
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

    public Roles getRolId() {
        return rolId;
    }

    public void setRolId(Roles rolId) {
        this.rolId = rolId;
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
        if (!(object instanceof Usuarioroles)) {
            return false;
        }
        Usuarioroles other = (Usuarioroles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.daw.paniculas.Usuarioroles[ id=" + id + " ]";
    }
    
}
