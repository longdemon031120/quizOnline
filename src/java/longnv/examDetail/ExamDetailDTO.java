/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.examDetail;

import java.io.Serializable;

/**
 *
 * @author Asus
 */
public class ExamDetailDTO implements Serializable{
    private String examID;
    private String questionID;
    private String questionTitle;
    private String answerCheckedID;
    private boolean correct;

    public ExamDetailDTO(String examID, String questionID, String questionTitle, String answerCheckedID, boolean correct) {
        this.examID = examID;
        this.questionID = questionID;
        this.questionTitle = questionTitle;
        this.answerCheckedID = answerCheckedID;
        this.correct = correct;
    }

    public String getExamID() {
        return examID;
    }

    public void setExamID(String examID) {
        this.examID = examID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getAnswerCheckedID() {
        return answerCheckedID;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setAnswerCheckedID(String answerCheckedID) {
        this.answerCheckedID = answerCheckedID;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    
    
}
