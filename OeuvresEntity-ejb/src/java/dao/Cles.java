/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Epulapp
 */
@Entity
@Table(name = "cles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cles.findAll", query = "SELECT c FROM Cles c"),
    @NamedQuery(name = "Cles.findByIdCle", query = "SELECT c FROM Cles c WHERE c.idCle = :idCle"),
    @NamedQuery(name = "Cles.findByValCle", query = "SELECT c FROM Cles c WHERE c.valCle = :valCle"),
    @NamedQuery(name = "Cles.findByLibCle", query = "SELECT c FROM Cles c WHERE c.libCle = :libCle")})
public class Cles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "id_cle")
    private String idCle;
    @Column(name = "val_cle")
    private Integer valCle;
    @Size(max = 80)
    @Column(name = "lib_cle")
    private String libCle;

    public Cles() {
    }

    public Cles(String idCle) {
        this.idCle = idCle;
    }

    public String getIdCle() {
        return idCle;
    }

    public void setIdCle(String idCle) {
        this.idCle = idCle;
    }

    public Integer getValCle() {
        return valCle;
    }

    public void setValCle(Integer valCle) {
        this.valCle = valCle;
    }

    public String getLibCle() {
        return libCle;
    }

    public void setLibCle(String libCle) {
        this.libCle = libCle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCle != null ? idCle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cles)) {
            return false;
        }
        Cles other = (Cles) object;
        if ((this.idCle == null && other.idCle != null) || (this.idCle != null && !this.idCle.equals(other.idCle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Cles[ idCle=" + idCle + " ]";
    }
    
}
