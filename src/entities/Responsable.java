/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author mohamed ben samir
 */
public class Responsable {
    private int  id;
    private String nom ;
    private String login;
    private String password;
    private String role;

    public Responsable(int id, String nom, String login, String password, String role) {
        this.id = id;
        this.nom = nom;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Responsable() {
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    
    
    
}
