/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Personnel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import org.json.JSONArray;
import tools.MyConnection;

/**
 *
 * @author mohamed ben samir
 */
public class PersonnelCRUD {
    MyConnection myc = MyConnection.getInstance();
    Connection cnx = myc.getConnection();
    
    public void ajouterMembre(Personnel m) throws SQLException {
         Statement ste = cnx.createStatement();
       
        String requeteInsert = "INSERT INTO `personnel`(`matricule`, `nom`, `statut`, `societe`, `magasin`, `service`, `applicabilite`, `codequalification`, `qualification`, `assurance`)"
                + "  VALUES ('"+m.getMatricule()+"','"+m.getNom()+"','"+m.getStatut()+"','"+m.getSociete()+"','"+m.getMagasin()+"','"+m.getService()+"','"+m.getApplicabilite()+"','"+m.getCodeQualification()+"','"+m.getQualfication()+"',"+m.getAssurance()+")";
        ste.executeUpdate(requeteInsert);
    }
    
     public ObservableList<Personnel> Affichertout_users() throws SQLException {
        ObservableList<Personnel> listUsers = FXCollections.observableArrayList();
      try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM personnel");
            
            ResultSet rs = preparedStatement.executeQuery();
             Personnel u = new Personnel();
            while (rs.next()) {
                int id=rs.getInt("id");
               String nom=rs.getString("nom");
               String matricule=rs.getString("matricule");
               String statut=rs.getString("statut");
               String societe=rs.getString("societe");
               String magasin =rs.getString("magasin");
               String service =rs.getString("service");
               String applicabilite =rs.getString("applicabilite");
               String codequalification =rs.getString("codequalification");
               String qualification =rs.getString("qualification");
               String assurance =rs.getString("assurance");
                
                listUsers.add(new Personnel(0, matricule, nom, statut, magasin, service, societe, applicabilite, codequalification, qualification, assurance));
            }
        } catch (SQLException e) {
            System.out.println("Erreur d'affichage: " + e.getMessage());
        }
        return listUsers;
    }
    
        public Personnel MembreParId(int id_user) throws SQLException {
    List<Personnel> arr=new ArrayList<>();
    //JSONArray roles = new JSONArray();
        
    Statement ste = cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from personnel where id='"+id_user+"'");
    Personnel u = new Personnel();
     while (rs.next()) {                
              int id=rs.getInt("id");
               String nom=rs.getString("nom");
               String matricule=rs.getString("matricule");
               String statut=rs.getString("statut");
               String societe=rs.getString("societe");
               String magasin =rs.getString("magasin");
               String service =rs.getString("service");
               String applicabilite =rs.getString("applicabilite");
               String codequalification =rs.getString("codequalification");
               String qualification =rs.getString("qualification");
               String assurance =rs.getString("assurance");
                
                u= new Personnel(id, matricule, nom, statut, magasin, service, societe, applicabilite, codequalification, qualification, assurance);
  
               
     }
     return u;
           
    }
        public Personnel MembreParMatricule(String matriculeU) throws SQLException {
    List<Personnel> arr=new ArrayList<>();
    //JSONArray roles = new JSONArray();
        
    Statement ste = cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from personnel where matricule='"+matriculeU+"'");
    Personnel u = new Personnel();
     while (rs.next()) {                
              int id=rs.getInt("id");
               String nom=rs.getString("nom");
               String matricule=rs.getString("matricule");
               String statut=rs.getString("statut");
               String societe=rs.getString("societe");
               String magasin =rs.getString("magasin");
               String service =rs.getString("service");
               String applicabilite =rs.getString("applicabilite");
               String codequalification =rs.getString("codequalification");
               String qualification =rs.getString("qualification");
               String assurance =rs.getString("assurance");
                
                u= new Personnel(id, matricule, nom, statut, magasin, service, societe, applicabilite, codequalification, qualification, assurance);
  
               
     }
     return u;
           
    }
          public void modifierMembre(Personnel m) {
        try {
            
            String req = "UPDATE `personnel` SET `matricule`=?,`nom`=?,`statut`=?,"
                         + "`societe`=?,`magasin`=?,`service`=?,`applicabilite`=?,"
                         + "`codequalification`=?,`qualification`=?,`assurance`=? WHERE `matricule`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            //String nt = String.valueOf(m.getNumtel());
            ps.setString(1, m.getMatricule());
            ps.setString(2, m.getNom());
            ps.setString(3, m.getStatut());
            ps.setString(4, m.getSociete());
            ps.setString(5, m.getMagasin());
            ps.setString(6, m.getService());
            ps.setString(7, m.getApplicabilite());
            ps.setString(8, m.getCodeQualification());
            ps.setString(9, m.getQualfication());
            ps.setString(10, m.getAssurance());
            ps.setString(11, m.getMatricule());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
          

    }
public List Search(String searchText) throws SQLException {
    List<Personnel> arr=new ArrayList<>();
        
    Statement ste = cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from personnel where matricule Like '%"+searchText+"%'");
    Personnel u = new Personnel();
     while (rs.next()) {                
              int id=rs.getInt("id");
               String nom=rs.getString("nom");
               String matricule=rs.getString("matricule");
               String statut=rs.getString("statut");
               String societe=rs.getString("societe");
               String magasin =rs.getString("magasin");
               String service =rs.getString("service");
               String applicabilite =rs.getString("applicabilite");
               String codequalification =rs.getString("codequalification");
               String qualification =rs.getString("qualification");
               String assurance =rs.getString("assurance");
                
               arr.add( new Personnel(id, matricule, nom, statut, magasin, service, societe, applicabilite, codequalification, qualification, assurance));
  
               
     }
     return arr;
           
    }
public void deletePersonnel(Personnel p) {
        try {
           
            
            String req = "Delete from `personnel` WHERE `matricule`='"+p.getMatricule()+"'";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
          

    }

 public List<Personnel> PersonnelParMatr(String matr) throws SQLException {
            List<Personnel> arr=new ArrayList<>();
            //JSONArray roles = new JSONArray();

            Statement ste = cnx.createStatement();
            ResultSet rs=ste.executeQuery("select * from personnel where (matricule LIKE '%"+matr+"%')");
            Personnel u = new Personnel();
            while (rs.next()) {                
              int id=rs.getInt("id");
               String nom=rs.getString("nom");
               String matricule=rs.getString("matricule");
               String statut=rs.getString("statut");
               String societe=rs.getString("societe");
               String magasin =rs.getString("magasin");
               String service =rs.getString("service");
               String applicabilite =rs.getString("applicabilite");
               String codequalification =rs.getString("codequalification");
               String qualification =rs.getString("qualification");
               String assurance =rs.getString("assurance");
                
               arr.add( new Personnel(id, matricule, nom, statut, magasin, service, societe, applicabilite, codequalification, qualification, assurance));
  
               
     }
     return arr;
           
    }
}
