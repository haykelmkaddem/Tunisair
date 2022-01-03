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


public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    
    private Integer id;
    private String pn;
    private String soc;
    private String mag;
    private String position;
    private String designation;
    private Integer type;
    private Integer stk;

    public Stock() {
    }

    public Stock(Integer id, String pn, String soc, String mag, String position, String designation, Integer type, Integer stk) {
        this.id = id;
        this.pn = pn;
        this.soc = soc;
        this.mag = mag;
        this.position = position;
        this.designation = designation;
        this.type = type;
        this.stk = stk;
    }
    

    public Stock(Integer id) {
        this.id = id;
    }

    public Stock(Integer id, String pn) {
        this.id = id;
        this.pn = pn;
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

    public String getSoc() {
        return soc;
    }

    public void setSoc(String soc) {
        this.soc = soc;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStk() {
        return stk;
    }

    public void setStk(Integer stk) {
        this.stk = stk;
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
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Stock[ id=" + id + " ]";
    }
    
}
