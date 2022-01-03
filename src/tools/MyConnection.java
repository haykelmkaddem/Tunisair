/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mohamed ben samir
 */
public class MyConnection {
    String url = "jdbc:mysql://localhost:3306/tunisairPfe";
     String login = "root";
     String pwd = "1234";
    public  static MyConnection db;
    public Connection cnx;
    private MyConnection() {
         try {
             cnx=DriverManager.getConnection(url, login, pwd);
             System.out.println("connexion etablie");
         } catch (SQLException ex) {
             System.out.println(ex);
         }
    }
    
    public Connection  getConnection()
    {
    return cnx;
    }     
    public static MyConnection getInstance()
    {if(db==null)
        db=new MyConnection();
    return db;
    } 
}
