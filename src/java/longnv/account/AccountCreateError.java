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
public class AccountCreateError implements Serializable{
    private String emailErr;
    private String passwordLengthErr;
    private String confirmErr;
    private String fullnameErr;
    private String emailIsExisted;
    private String creationSuccess;


    public AccountCreateError() {
    }

    public AccountCreateError(String emailErr, String passwordLengthErr, String confirmErr, String fullnameErr, String emailIsExisted, String creationSuccess) {
        this.emailErr = emailErr;
        this.passwordLengthErr = passwordLengthErr;
        this.confirmErr = confirmErr;
        this.fullnameErr = fullnameErr;
        this.emailIsExisted = emailIsExisted;
        this.creationSuccess = creationSuccess;
    }


    public String getCreationSuccess() {
        return creationSuccess;
    }

    public void setCreationSuccess(String creationSuccess) {
        this.creationSuccess = creationSuccess;
    }

    
    
    public String getFullnameErr() {
        return fullnameErr;
    }

    public void setFullnameErr(String fullnameErr) {
        this.fullnameErr = fullnameErr;
    }

    public String getEmailIsExisted() {
        return emailIsExisted;
    }

    public void setEmailIsExisted(String emailIsExisted) {
        this.emailIsExisted = emailIsExisted;
    }

    public String getEmailErr() {
        return emailErr;
    }

    public void setEmailErr(String emailErr) {
        this.emailErr = emailErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getConfirmErr() {
        return confirmErr;
    }

    public void setConfirmErr(String confirmErr) {
        this.confirmErr = confirmErr;
    }

    public String getFullnameLengthErr() {
        return fullnameErr;
    }

    public void setFullnameLengthErr(String fullnameLengthErr) {
        this.fullnameErr = fullnameLengthErr;
    }


}
