/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Stock;
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
public class StockCRUD {
    MyConnection myc = MyConnection.getInstance();
    Connection cnx = myc.getConnection();
    
    public void ajouterStock(Stock m) throws SQLException {
        Statement ste = cnx.createStatement();
        String requeteInsert = "INSERT INTO `stock`(`pn`, `soc`, `mag`, `position`, `designation`, `type`, `stk`)"
                + "  VALUES ('"+m.getPn()+"','"+m.getSoc()+"','"+m.getMag()+"','"+m.getPosition()+"','"+m.getDesignation()+"','"+m.getType()+"','"+m.getStk()+"')";
        ste.executeUpdate(requeteInsert);
    }
    
     public ObservableList<Stock> Affichertout_stocks() throws SQLException {
        ObservableList<Stock> listStock = FXCollections.observableArrayList();
      try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM stock");
            
            ResultSet rs = preparedStatement.executeQuery();
             Stock u = new Stock();
            while (rs.next()) {
               int id=rs.getInt("id");
               String pn=rs.getString("pn");
               String soc=rs.getString("soc");
               String mag=rs.getString("mag");
               String position=rs.getString("position");
               String designation =rs.getString("designation");
               int type =rs.getInt("type");
               int stk =rs.getInt("stk");
               
                
                listStock.add(new Stock(id, pn, soc, mag, position, designation, type, stk));
            }
        } catch (SQLException e) {
            System.out.println("Erreur d'affichage: " + e.getMessage());
        }
        return listStock;
    }
    
        public Stock StockParId(int id_user) throws SQLException {
    List<Stock> arr=new ArrayList<>();
    //JSONArray roles = new JSONArray();
        
    Statement ste = cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from personnel where id='"+id_user+"'");
    Stock u = new Stock();
     while (rs.next()) {                
              int id=rs.getInt("id");
               String pn=rs.getString("pn");
               String soc=rs.getString("soc");
               String mag=rs.getString("mag");
               String position=rs.getString("position");
               String designation =rs.getString("designation");
               int type =rs.getInt("type");
               int stk =rs.getInt("stk");
                
                u= new Stock(id, pn, soc, mag, position, designation, type, stk);
  
               
     }
     return u;
           
    }
    
        public List<Stock> StockParPN(String spn) throws SQLException {
    List<Stock> arr=new ArrayList<>();
    //JSONArray roles = new JSONArray();
        
    Statement ste = cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from stock where pn LIKE '%"+spn+"%'");
    Stock u = new Stock();
     while (rs.next()) {                
              int id=rs.getInt("id");
               String pn=rs.getString("pn");
               String soc=rs.getString("soc");
               String mag=rs.getString("mag");
               String position=rs.getString("position");
               String designation =rs.getString("designation");
               int type =rs.getInt("type");
               int stk =rs.getInt("stk");
                
                arr.add(new Stock(id, pn, soc, mag, position, designation, type, stk));
  
               
     }
     return arr;
           
    }
          public void modifierStock(Stock m) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            
            String req = "UPDATE `stock` SET `pn`=?,`soc`=?,"
                    + "`mag`=?,`position`=?,`designation`=?,`type`=?"
                    + ",`stk`=? WHERE `id`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            //String nt = String.valueOf(m.getNumtel());
            ps.setString(1, m.getPn());
            ps.setString(2, m.getSoc());
            ps.setString(3, m.getMag());
            ps.setString(4, m.getPosition());
            ps.setString(5, m.getDesignation());
            ps.setInt(6, m.getType());
            ps.setInt(7, m.getStk());
            ps.setInt(8, m.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
          

    }
          public Boolean StockParPN1(String spn) throws SQLException {
 
    //JSONArray roles = new JSONArray();
        
    Statement ste = cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from stock where pn = '"+spn+"'");
    Stock u = new Stock();
    if(rs.next()) {                
             
                
             return true;
  
               
     }
     return false;
           
    }
}
