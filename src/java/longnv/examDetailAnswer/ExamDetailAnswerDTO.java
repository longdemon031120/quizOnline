/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.examDetailAnswer;

import java.io.Serializable;

/**
 *
 * @author Asus
 */
public class ExamDetailAnswerDTO implements Serializable{
    private String examID;
    private String questionID;
    private String answerContent;
    private boolean checked;
    private boolean correct;

    public ExamDetailAnswerDTO(String questionID, String AnswerContent, boolean checked, boolean correct) {
        this.questionID = questionID;
        this.answerContent = AnswerContent;
        this.checked = checked;
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

    public void setQuestionID(String QuestionID) {
        this.questionID = QuestionID;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String AnswerContent) {
        this.answerContent = AnswerContent;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    
    
}
