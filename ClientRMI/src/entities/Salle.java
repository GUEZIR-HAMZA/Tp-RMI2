/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;

/**
 *
 * @author Guezir
 */
public class Salle implements Serializable {
    
   private int id;
    private String code;

    public Salle(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public Salle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

     @Override
    public String toString() {
        return  code ;
    }

    public Salle(String code) {
        this.code = code;
    }
    
    
    
}
