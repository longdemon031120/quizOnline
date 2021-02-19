/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.answer;

import java.io.Serializable;

/**
 *
 * @author Asus
 */
public class AnswerDTO implements Serializable{
    private String answerID;
    private String answerContent;
    private boolean answerCorrect;
    private String QuestionID;

    public AnswerDTO(String answerID, String answerContent, boolean answerCorrect, String QuestionID) {
        this.answerID = answerID;
        this.answerContent = answerContent;
        this.answerCorrect = answerCorrect;
        this.QuestionID = QuestionID;
    }

    public AnswerDTO(String answerID, String answerContent, boolean answerCorrect) {
        this.answerID = answerID;
        this.answerContent = answerContent;
        this.answerCorrect = answerCorrect;
    }

    public String getAnswerID() {
        return answerID;
    }

    public void setAnswerID(String answerID) {
        this.answerID = answerID;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public boolean isAnswerCorrect() {
        return answerCorrect;
    }

    public void setAnswerCorrect(boolean answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public String getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(String QuestionID) {
        this.QuestionID = QuestionID;
    }
    
    
    
}
