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
public class AccountLoginError implements Serializable{
    private String usernameNotValid;
    private String usernameValid;
    private String passwordNotValid;

    public AccountLoginError() {
    }

    public String getUsernameValid() {
        return usernameValid;
    }

    public void setUsernameValid(String usernameValid) {
        this.usernameValid = usernameValid;
    }

    public AccountLoginError(String usernameNotValid, String usernameValid, String passwordNotValid) {
        this.usernameNotValid = usernameNotValid;
        this.usernameValid = usernameValid;
        this.passwordNotValid = passwordNotValid;
    }


    public String getUsernameNotValid() {
        return usernameNotValid;
    }

    public void setUsernameNotValid(String usernameNotValid) {
        this.usernameNotValid = usernameNotValid;
    }

    public String getPasswordNotValid() {
        return passwordNotValid;
    }

    public void setPasswordNotValid(String passwordNotValid) {
        this.passwordNotValid = passwordNotValid;
    }
    
    
    
}
