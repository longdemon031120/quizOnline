/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.question;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class QuestionDTO implements Serializable{
    private String questionID;
    private String questionTitle;
    private Date createDate;
    private boolean status;

    public QuestionDTO(String questionID, String questionTitle, Date createDate, boolean status) {
        this.questionID = questionID;
        this.questionTitle = questionTitle;
        this.createDate = createDate;
        this.status = status;
    }

    public QuestionDTO(String questionID, String questionTitle, boolean status) {
        this.questionID = questionID;
        this.questionTitle = questionTitle;
        this.status = status;
    }

    public QuestionDTO(String questionID, String questionTitle) {
        this.questionID = questionID;
        this.questionTitle = questionTitle;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
