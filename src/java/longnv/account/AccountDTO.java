/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.account;

import java.io.Serializable;

/**
 *
 * @author Asus
 */
public class AccountDTO implements Serializable{
    private String email;
    private String name;
    private String pass;
    private boolean role;
    private boolean status;

    public AccountDTO(String email, String name, String pass, boolean role, boolean status) {
        this.email = email;
        this.name = name;
        this.pass = pass;
        this.role = role;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
