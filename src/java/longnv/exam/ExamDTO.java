/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.exam;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class ExamDTO implements Serializable{
    private String examID;
    private String accountID;
    private String subjectID;
    private Date examDate;
    private int scores;

    public ExamDTO(String examID, String accountID, Date examDate, int scores) {
        this.examID = examID;
        this.accountID = accountID;
        this.examDate = examDate;
        this.scores = scores;
    }

    public ExamDTO(String examID, Date examDate, int scores, String subjectID) {
        this.examID = examID;
        this.examDate = examDate;
        this.scores = scores;
        this.subjectID = subjectID;
    }

    public ExamDTO(String examID, String accountID, int scores) {
        this.examID = examID;
        this.accountID = accountID;
        this.scores = scores;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getExamID() {
        return examID;
    }

    public void setExamID(String examID) {
        this.examID = examID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }
    
    
}
