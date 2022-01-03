/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author mohamed ben samir
 */

public class Dotation implements Serializable {

    private Integer id;
    private String sn;
    private String matricule;
    private String datesortie;
    private String dateretour;
    private String codedotation;
    private String livree;
    private String retour;

    public Dotation() {
    }

    public Dotation(Integer id) {
        this.id = id;
    }

    public Dotation(Integer id, String sn, String matricule, String datesortie, String codedotation) {
        this.id = id;
        this.sn = sn;
        this.matricule = matricule;
        this.datesortie = datesortie;
        this.codedotation = codedotation;
    }

    public Dotation(Integer id, String sn, String matricule, String datesortie, String dateretour, String codedotation, String livree, String retour) {
        this.id = id;
        this.sn = sn;
        this.matricule = matricule;
        this.datesortie = datesortie;
        this.dateretour = dateretour;
        this.codedotation = codedotation;
        this.livree = livree;
        this.retour = retour;
    }

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getDatesortie() {
        return datesortie;
    }

    public void setDatesortie(String datesortie) {
        this.datesortie = datesortie;
    }

    public String getDateretour() {
        return dateretour;
    }

    public void setDateretour(String dateretour) {
        this.dateretour = dateretour;
    }

    public String getCodedotation() {
        return codedotation;
    }

    public void setCodedotation(String codedotation) {
        this.codedotation = codedotation;
    }

    public String getLivree() {
        return livree;
    }

    public void setLivree(String livree) {
        this.livree = livree;
    }

    public String getRetour() {
        return retour;
    }

    public void setRetour(String retour) {
        this.retour = retour;
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
        if (!(object instanceof Dotation)) {
            return false;
        }
        Dotation other = (Dotation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Dotation[ id=" + id + " ]";
    }
    
}
