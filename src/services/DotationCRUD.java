/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Dotation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

/**
 *
 * @author mohamed ben samir
 */
public class DotationCRUD {
    MyConnection myc = MyConnection.getInstance();
    Connection cnx = myc.getConnection();
    
    public void ajouterDotation(Dotation d) throws SQLException {
        Statement ste = cnx.createStatement();
        int lastid =0;
        String code = "OT ";
        String requeteInsert1 = "INSERT INTO `iddot`(`iddotation`) VALUES (null)";
        ste.executeUpdate(requeteInsert1);
        PreparedStatement preparedStatement = cnx.prepareStatement("SELECT id FROM `iddot`");
            
            ResultSet rs = preparedStatement.executeQuery();
        while(rs.next())
          {
              lastid= rs.getInt("id");
             
          }
         code = code + String.valueOf(lastid);
        
        String requeteInsert = "INSERT INTO `dotation`(`sn`, `matricule`, `datesortie`, `codedotation`)"
                + " VALUES ('"+d.getSn()+"', '"+d.getMatricule()+"', '"+d.getDatesortie()+"','"+code+"')";
        System.out.println("reuete-> "+requeteInsert);
        ste.executeUpdate(requeteInsert);
       
    }
    
     public ObservableList<Dotation> Affichertout_Dotations() throws SQLException {
        ObservableList<Dotation> listDotation = FXCollections.observableArrayList();
      try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM dotation");
            
            ResultSet rs = preparedStatement.executeQuery();
             Dotation u = new Dotation();
            while (rs.next()) {
               int id=rs.getInt("id");
               String sn=rs.getString("sn");
               String matricule=rs.getString("matricule");
               String datesortie=rs.getString("datesortie");
               String dateretour=rs.getString("dateretour");
               String codedotation=rs.getString("codedotation");
               String livree=rs.getString("livree");
               String retour=rs.getString("retour");
              
               
               
                
               listDotation.add(new Dotation(id, sn, matricule, datesortie, dateretour, codedotation, livree, retour));
            }
        } catch (SQLException e) {
            System.out.println("Erreur d'affichage: " + e.getMessage());
        }
        return listDotation;
    }
//    
////    public Dotation DotationParId(int ide) throws SQLException {
////        List<Dotation> arr=new ArrayList<>();
////        //JSONArray roles = new JSONArray();
////
////        Statement ste = cnx.createStatement();
////        ResultSet rs=ste.executeQuery("select * from personnel where id='"+ide+"'");
////        Dotation u = new Dotation();
////     while (rs.next()) {                
////               int id=rs.getInt("id");
////               String pn=rs.getString("pn");
////               String sn=rs.getString("sn");
////               String situation=rs.getString("situation");
////               String societe=rs.getString("societe");
////               String magasin=rs.getString("magasin");
////               String dotat=rs.getString("dotat");
////               String lieu=rs.getString("lieu");
////               String derniermouvement=rs.getString("derniermouvement");
////               String proprietaire=rs.getString("proprietaire");
////               String position=rs.getString("position");
////               String designation=rs.getString("designation");
////                
////                u= new Dotation(id, pn, sn, situation, societe, magasin, dotat, lieu, derniermouvement, proprietaire, position, designation);
////  
////               
////     }
////     return u;
////           
////    }
//    
//        public List<Dotation> DotationParSN(String esn) throws SQLException {
//            List<Dotation> arr=new ArrayList<>();
//            //JSONArray roles = new JSONArray();
//
//            Statement ste = cnx.createStatement();
//            ResultSet rs=ste.executeQuery("select * from equipement where (sn LIKE '%"+esn+"%')");
//            Dotation u = new Dotation();
//            while (rs.next()) {                
//               int id=rs.getInt("id");
//               String pn=rs.getString("pn");
//               String sn=rs.getString("sn");
//               String situation=rs.getString("situation");
//               String societe=rs.getString("societe");
//               String magasin=rs.getString("magasin");
//               String dotat=rs.getString("dotat");
//               String lieu=rs.getString("lieu");
//               String derniermouvement=rs.getString("derniermouvement");
//               String proprietaire=rs.getString("proprietaire");
//               String position=rs.getString("position");
//               String designation=rs.getString("designation");
//                
//               arr.add(new Dotation(id, pn, sn, situation, societe, magasin, dotat, lieu, derniermouvement, proprietaire, position, designation));
//               
//     }
//     return arr;
//           
//    }
          public void modifierDotation(String m) {
        try {
           
            
            String req = "UPDATE `dotation` SET `livree`='Oui' WHERE `codedotation`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            //String nt = String.valueOf(m.getNumtel());
            ps.setString(1, m);
            
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
          

    }
      public void modifierDotationRetour(String m) {
          LocalDate now = LocalDate.now();  
            String datenow = now.toString();
                      
          String req = "UPDATE `dotation` SET `retour`='Oui',`dateretour`='"+datenow+"' WHERE `codedotation`='"+m+"'";

        try {
            
            PreparedStatement ps = cnx.prepareStatement(req);
            
            System.out.println(datenow);
            System.out.println("requ ->"+req);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("requ ->"+req);

            System.out.println(ex);
        }
          

    }
     
     public ObservableList<Dotation> Affichertout_DotationsLivree() throws SQLException {
        ObservableList<Dotation> listDotation = FXCollections.observableArrayList();
      try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM dotation where livree = 1");
            
            ResultSet rs = preparedStatement.executeQuery();
             Dotation u = new Dotation();
            while (rs.next()) {
               int id=rs.getInt("id");
               String sn=rs.getString("sn");
               String matricule=rs.getString("matricule");
               String datesortie=rs.getString("datesortie");
               String dateretour=rs.getString("dateretour");
               String codedotation=rs.getString("codedotation");
               String livree=rs.getString("livree");
               String retour=rs.getString("retour");
              
               
               
                
               listDotation.add(new Dotation(id, sn, matricule, datesortie, dateretour, codedotation, livree, retour));
            }
        } catch (SQLException e) {
            System.out.println("Erreur d'affichage: " + e.getMessage());
        }
        return listDotation;
    }
      public ObservableList<Dotation> Affichertout_DotationsRetour() throws SQLException {
        ObservableList<Dotation> listDotation = FXCollections.observableArrayList();
      try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM dotation where retour = 'Non'");
            
            ResultSet rs = preparedStatement.executeQuery();
             Dotation u = new Dotation();
            while (rs.next()) {
               int id=rs.getInt("id");
               String sn=rs.getString("sn");
               String matricule=rs.getString("matricule");
               String datesortie=rs.getString("datesortie");
               String dateretour=rs.getString("dateretour");
               String codedotation=rs.getString("codedotation");
               String livree=rs.getString("livree");
               String retour=rs.getString("retour");
              
               
               
                
               listDotation.add(new Dotation(id, sn, matricule, datesortie, dateretour, codedotation, livree, retour));
            }
        } catch (SQLException e) {
            System.out.println("Erreur d'affichage: " + e.getMessage());
        }
        return listDotation;
    }
       public ObservableList<Dotation> Afficher_DotationsparCode(String code) throws SQLException {
        ObservableList<Dotation> listDotation = FXCollections.observableArrayList();
      try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM dotation where codedotation ='"+code+"'");
            
            ResultSet rs = preparedStatement.executeQuery();
             Dotation u = new Dotation();
            while (rs.next()) {
               int id=rs.getInt("id");
               String sn=rs.getString("sn");
               String matricule=rs.getString("matricule");
               String datesortie=rs.getString("datesortie");
               String dateretour=rs.getString("dateretour");
               String codedotation=rs.getString("codedotation");
               String livree=rs.getString("livree");
               String retour=rs.getString("retour");
              
               
               
                
               listDotation.add(new Dotation(id, sn, matricule, datesortie, dateretour, codedotation, livree, retour));
            }
        } catch (SQLException e) {
            System.out.println("Erreur d'affichage: " + e.getMessage());
        }
        return listDotation;
    }
       public List<Dotation> DotationParCode(String esn) throws SQLException {
            List<Dotation> arr=new ArrayList<>();
            //JSONArray roles = new JSONArray();

            Statement ste = cnx.createStatement();
            ResultSet rs=ste.executeQuery("select * from dotation where (codedotation LIKE '%"+esn+"%')");
            Dotation u = new Dotation();
                while (rs.next()) {
               int id=rs.getInt("id");
               String sn=rs.getString("sn");
               String matricule=rs.getString("matricule");
               String datesortie=rs.getString("datesortie");
               String dateretour=rs.getString("dateretour");
               String codedotation=rs.getString("codedotation");
               String livree=rs.getString("livree");
               String retour=rs.getString("retour");
                
               arr.add(new Dotation(id, sn, matricule, datesortie, dateretour, codedotation, livree, retour));
               
     }
     return arr;
           
    }
}
