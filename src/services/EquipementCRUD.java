/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Equipement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

/**
 *
 * @author mohamed ben samir
 */
public class EquipementCRUD {
    MyConnection myc = MyConnection.getInstance();
    Connection cnx = myc.getConnection();
    
    public void ajouterEquipement(Equipement m) throws SQLException {
        Statement ste = cnx.createStatement();        
        //String uniqueID = UUID.randomUUID().toString();
        String requeteInsert = "INSERT INTO `equipement`(`pn`, `sn`, `situation`, `societe`, `magasin`, `dotat`, `lieu`, `derniermouvement`, `proprietaire`, `position`, `designation`,`stock`)"
                + "  VALUES ('"+m.getPn()+"','"+m.getSn()+"','"+m.getSituation()+"','"+m.getSociete()+"','"+m.getMagasin()+"','"+m.getDotat()+"','"+m.getLieu()+"','"+m.getDerniermouvement()+"','"+m.getProprietaire()+"','"+m.getPosition()+"','"+m.getDesignation()+"','"+m.getStock()+"')";
            ste.executeUpdate(requeteInsert);
            
    }
    
     public ObservableList<Equipement> Affichertout_equipements() throws SQLException {
        ObservableList<Equipement> listEquipement = FXCollections.observableArrayList();
      try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM equipement");
            
            ResultSet rs = preparedStatement.executeQuery();
             Equipement u = new Equipement();
            while (rs.next()) {
               int id=rs.getInt("id");
               String pn=rs.getString("pn");
               String sn=rs.getString("sn");
               String situation=rs.getString("situation");
               String societe=rs.getString("societe");
               String magasin=rs.getString("magasin");
               String dotat=rs.getString("dotat");
               String lieu=rs.getString("lieu");
               String derniermouvement=rs.getString("derniermouvement");
               String proprietaire=rs.getString("proprietaire");
               String position=rs.getString("position");
               String designation=rs.getString("designation");
               int stk=rs.getInt("stock");
               
               
                
                listEquipement.add(new Equipement(pn, sn, situation, societe, magasin, lieu, proprietaire, stk));
            }
        } catch (SQLException e) {
            System.out.println("Erreur d'affichage: " + e.getMessage());
        }
        return listEquipement;
    }
    
//    public Equipement EquipementParId(int ide) throws SQLException {
//        List<Equipement> arr=new ArrayList<>();
//        //JSONArray roles = new JSONArray();
//
//        Statement ste = cnx.createStatement();
//        ResultSet rs=ste.executeQuery("select * from personnel where id='"+ide+"'");
//        Equipement u = new Equipement();
//     while (rs.next()) {                
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
//                u= new Equipement(id, pn, sn, situation, societe, magasin, dotat, lieu, derniermouvement, proprietaire, position, designation);
//  
//               
//     }
//     return u;
//           
//    }
    
        public List<Equipement> EquipementParSN(String esn) throws SQLException {
            List<Equipement> arr=new ArrayList<>();
            //JSONArray roles = new JSONArray();

            Statement ste = cnx.createStatement();
            ResultSet rs=ste.executeQuery("select * from equipement where (sn LIKE '%"+esn+"%')");
            Equipement u = new Equipement();
            while (rs.next()) {                
               int id=rs.getInt("id");
               String pn=rs.getString("pn");
               String sn=rs.getString("sn");
               String situation=rs.getString("situation");
               String societe=rs.getString("societe");
               String magasin=rs.getString("magasin");
               String dotat=rs.getString("dotat");
               String lieu=rs.getString("lieu");
               String derniermouvement=rs.getString("derniermouvement");
               String proprietaire=rs.getString("proprietaire");
               String position=rs.getString("position");
               String designation=rs.getString("designation");
                
               arr.add(new Equipement(id, pn, sn, situation, societe, magasin, dotat, lieu, derniermouvement, proprietaire, position, designation));
               
     }
     return arr;
           
    }
          public void modifierEquipement(Equipement m) {
        try {
           
            
            String req = "UPDATE `equipement` SET `pn`=?,`sn`=?,"
                    + "`situation`=?,`societe`=?,`magasin`=?,`dotat`=?,"
                    + "`lieu`=?,`derniermouvement`=?,`proprietaire`=?],"
                    + "`position`=?],`designation`=? WHERE `id`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            //String nt = String.valueOf(m.getNumtel());
            ps.setString(1, m.getPn());
            ps.setString(2, m.getSn());
            ps.setString(3, m.getSituation());
            ps.setString(4, m.getSociete());
            ps.setString(5, m.getMagasin());
            ps.setString(6, m.getDotat());
            ps.setString(7, m.getLieu());
            ps.setString(8, m.getDerniermouvement());
            ps.setString(9, m.getProprietaire());
            ps.setString(10, m.getPosition());
            ps.setString(11, m.getDesignation());
            ps.setInt(12, m.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
          

    }
     public void deleteEquipement(Equipement m) {
        try {
           
            
            String req = "Delete from `equipement` WHERE `sn`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            //String nt = String.valueOf(m.getNumtel());
            ps.setString(1, m.getSn());
            
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
          

    }
}
