/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;


/**
 *
 * @author mohamed ben samir
 */

public class Equipement implements Serializable {

   
   
    private Integer id;
    private String pn;
    private String sn;
    
    private String situation;
    
    private String societe;
  
    private String magasin;
   
    private String dotat;
   
    private String lieu;
   
    private String derniermouvement;
   
    private String proprietaire;
    
    private String position;
   
    private String designation;
    
    private int stock;

    public Equipement() {
    }

    public Equipement(Integer id, String pn, String sn, String situation, String societe, String magasin, String dotat, String lieu, String derniermouvement, String proprietaire, String position, String designation) {
        this.id = id;
        this.pn = pn;
        this.sn = sn;
        this.situation = situation;
        this.societe = societe;
        this.magasin = magasin;
        this.dotat = dotat;
        this.lieu = lieu;
        this.derniermouvement = derniermouvement;
        this.proprietaire = proprietaire;
        this.position = position;
        this.designation = designation;
    }

    public Equipement(String pn, String sn, String situation, String societe, String magasin, String lieu, String proprietaire, int stock) {
        this.pn = pn;
        this.sn = sn;
        this.situation = situation;
        this.societe = societe;
        this.magasin = magasin;
        this.lieu = lieu;
        this.proprietaire = proprietaire;
        this.stock = stock;
    }
    
    
    
    public Equipement(Integer id) {
        this.id = id;
    }

    public Equipement(Integer id, String pn, String sn) {
        this.id = id;
        this.pn = pn;
        this.sn = sn;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getMagasin() {
        return magasin;
    }

    public void setMagasin(String magasin) {
        this.magasin = magasin;
    }

    public String getDotat() {
        return dotat;
    }

    public void setDotat(String dotat) {
        this.dotat = dotat;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDerniermouvement() {
        return derniermouvement;
    }

    public void setDerniermouvement(String derniermouvement) {
        this.derniermouvement = derniermouvement;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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
        if (!(object instanceof Equipement)) {
            return false;
        }
        Equipement other = (Equipement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Equipement[ id=" + id + " ]";
    }
    
}
