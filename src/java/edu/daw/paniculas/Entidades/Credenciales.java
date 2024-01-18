/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.daw.paniculas.Entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author anton
 */
@Entity
@Table(name = "CREDENCIALES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Credenciales.findAll", query = "SELECT c FROM Credenciales c"),
    @NamedQuery(name = "Credenciales.findByUsuario", query = "SELECT c FROM Credenciales c WHERE c.usuario = :usuario"),
    @NamedQuery(name = "Credenciales.findByPassword", query = "SELECT c FROM Credenciales c WHERE c.password = :password")})
public class Credenciales implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PASSWORD")
    private String password;
    @JoinColumn(name = "ROL_ID", referencedColumnName = "ID")
    @ManyToOne
    private Roles rolId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Usuarioroles> usuariorolesCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USUARIO")
    private String usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Set<Comentarios> comentariosSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Set<Datosadicionales> datosadicionalesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Set<Usuariocategorias> usuariocategoriasSet;

    public Credenciales() {
    }

    public Credenciales(String usuario) {
        this.usuario = usuario;
    }

    public Credenciales(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    @XmlTransient
    public Set<Comentarios> getComentariosSet() {
        return comentariosSet;
    }

    public void setComentariosSet(Set<Comentarios> comentariosSet) {
        this.comentariosSet = comentariosSet;
    }

    @XmlTransient
    public Set<Datosadicionales> getDatosadicionalesSet() {
        return datosadicionalesSet;
    }

    public void setDatosadicionalesSet(Set<Datosadicionales> datosadicionalesSet) {
        this.datosadicionalesSet = datosadicionalesSet;
    }

    @XmlTransient
    public Set<Usuariocategorias> getUsuariocategoriasSet() {
        return usuariocategoriasSet;
    }

    public void setUsuariocategoriasSet(Set<Usuariocategorias> usuariocategoriasSet) {
        this.usuariocategoriasSet = usuariocategoriasSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuario != null ? usuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credenciales)) {
            return false;
        }
        Credenciales other = (Credenciales) object;
        if ((this.usuario == null && other.usuario != null) || (this.usuario != null && !this.usuario.equals(other.usuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.daw.paniculas.Credenciales[ usuario=" + usuario + " ]";
    }


    @XmlTransient
    public Collection<Usuarioroles> getUsuariorolesCollection() {
        return usuariorolesCollection;
    }

    public void setUsuariorolesCollection(Collection<Usuarioroles> usuariorolesCollection) {
        this.usuariorolesCollection = usuariorolesCollection;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRolId() {
        return rolId;
    }

    public void setRolId(Roles rolId) {
        this.rolId = rolId;
    }
    
}
