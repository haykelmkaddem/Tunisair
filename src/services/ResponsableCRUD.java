/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Personnel;
import entities.Responsable;
import entities.Responsable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

/**
 *
 * @author mohamed ben samir
 */
public class ResponsableCRUD {
    
     MyConnection myc = MyConnection.getInstance();
    Connection cnx = myc.getConnection();
    
     public void ajouterResponsable(Responsable r) throws SQLException {
        Statement ste = cnx.createStatement();
        String requeteInsert = "INSERT INTO `user`(`nom`, `login`, `password`, `role`) "
                + "VALUES ('"+r.getNom()+"','"+r.getLogin()+"','"+r.getPassword()+"','"+r.getRole()+"')";
        ste.executeUpdate(requeteInsert);
    }
     
    public Responsable Login(String login, String mdp)  throws SQLException{
            Statement ste = cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from user where login='"+login+"' and password ='"+mdp+"' ");
    Responsable u = new Responsable();
     while (rs.next()) {                
              int id=rs.getInt("id");
               String nom=rs.getString("nom");
               String role =rs.getString("role");
               u.setNom(nom);
               u.setRole(role);
     }
     return u;
        
       
        
    }
     
    public Boolean exist(String login)  throws SQLException{
            Statement ste = cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from user where login='"+login+"'");
    Boolean ok = false;
     
    if (rs.next()) {                
              ok = true;
     }
     return ok;
        
       
        
    }
       public ObservableList<Responsable> Affichertout_responsables() throws SQLException {
        ObservableList<Responsable> listUsers = FXCollections.observableArrayList();
      try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM user where role = 'responsable'");
            
            ResultSet rs = preparedStatement.executeQuery();
             Responsable u = new Responsable();
            while (rs.next()) {
               int id=rs.getInt("id");
               String nom=rs.getString("nom");
               String login =rs.getString("login");
               String role =rs.getString("role");
                
                listUsers.add(new Responsable(id, nom, login, null, role));
            }
        } catch (SQLException e) {
            System.out.println("Erreur d'affichage: " + e.getMessage());
        }
        return listUsers;
    }
    public ObservableList<Responsable> Affichertout_admins() throws SQLException {
        ObservableList<Responsable> listUsers = FXCollections.observableArrayList();
      try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM user where role = 'admin'");
            
            ResultSet rs = preparedStatement.executeQuery();
             Responsable u = new Responsable();
            while (rs.next()) {
               int id=rs.getInt("id");
               String nom=rs.getString("nom");
               String login =rs.getString("login");
               String role =rs.getString("role");
                
                listUsers.add(new Responsable(id, nom, login, null, role));
            }
        } catch (SQLException e) {
            System.out.println("Erreur d'affichage: " + e.getMessage());
        }
        return listUsers;
    }
    
     public void modifierResponsableAdmin(Responsable m) {
        try {
            
            String req = "UPDATE `user` SET `nom`=?,`login`=?,"
                         + "`role`=? WHERE `id`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            //String nt = String.valueOf(m.getNumtel());
            ps.setString(1, m.getNom());
            ps.setString(2, m.getLogin());
            ps.setString(3, m.getRole());
            ps.setInt(4, m.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
          

    }
     public void modifierMdp(Responsable m) {
        try {
            
            String req = "UPDATE `user` SET `password`='"+m.getPassword()+"' WHERE `login`='"+m.getLogin()+"'";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();
            System.out.println("update password works-->"+m.getLogin()+"-->"+m.getPassword());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
          

    }
     public void rendreResponsable(Responsable m) {
        try {
            
            String req = "UPDATE `user` SET `role`='responsable' WHERE `id`="+String.valueOf(m.getId());
            PreparedStatement ps = cnx.prepareStatement(req);
//            //String nt = String.valueOf(m.getNumtel());
//            ps.setString(1, "responsable");
//            ps.setInt(2, m.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
          

    }
     
     public void rendreAdmin(Responsable m) {
        try {
            
            String req = "UPDATE `user` SET `role`='admin' WHERE `id`="+String.valueOf(m.getId());
            PreparedStatement ps = cnx.prepareStatement(req);
            //String nt = String.valueOf(m.getNumtel());
//            ps.setString(1, "admin");
//            ps.setInt(2, m.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
          

    }
     public void delete(Responsable m) {
        try {
            
            String req = "delete from `user` WHERE `id`="+String.valueOf(m.getId());
            PreparedStatement ps = cnx.prepareStatement(req);
            //String nt = String.valueOf(m.getNumtel());
//            ps.setString(1, "admin");
//            ps.setInt(2, m.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
          

    }
}
