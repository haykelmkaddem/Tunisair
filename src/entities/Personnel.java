/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;

/**
 *
 * @author mohamed ben samir
 */
public class Personnel {
    private int  id;
    private String matricule;
    private String nom;
    private String statut;
    private String magasin;
    private String service;
    private String societe;
    private String applicabilite;
    private String codequalification;
    private String qualification;
    private String assurance;

    public Personnel() {
    }

   

    public Personnel(int id, String matricule, String nom, String statut, String magasin, String service, String societe, String applicabilite, String codeQualification, String qualfication, String assurance) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.statut = statut;
        this.magasin = magasin;
        this.service = service;
        this.societe = societe;
        this.applicabilite = applicabilite;
        this.codequalification = codeQualification;
        this.qualification = qualfication;
        this.assurance = assurance;
    }

 public Personnel( String matricule, String nom, String statut, String magasin, String service, String societe, String applicabilite, String codeQualification, String qualfication ) {
        
        this.matricule = matricule;
        this.nom = nom;
        this.statut = statut;
        this.magasin = magasin;
        this.service = service;
        this.societe = societe;
        this.applicabilite = applicabilite;
        this.codequalification = codeQualification;
        this.qualification = qualfication;
       
    }
    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getMagasin() {
        return magasin;
    }

    public void setMagasin(String magasin) {
        this.magasin = magasin;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getApplicabilite() {
        return applicabilite;
    }

    public void setApplicabilite(String applicabilite) {
        this.applicabilite = applicabilite;
    }

    public String getCodeQualification() {
        return codequalification;
    }

    public void setCodeQualification(String codeQualification) {
        this.codequalification = codeQualification;
    }

    public String getQualfication() {
        return qualification;
    }

    public void setQualfication(String qualfication) {
        this.qualification = qualfication;
    }

    public String getAssurance() {
        return assurance;
    }

    public void setAssurance(String assurance) {
        this.assurance = assurance;
    }
  

}
