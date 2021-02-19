/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.subject;

import java.io.Serializable;

/**
 *
 * @author Asus
 */
public class SubjectDTO implements Serializable{
    private String subjectID;
    private String subjectName;
    private boolean active;

    public SubjectDTO(String subjectID, String subjectName) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
    }

    public SubjectDTO(String subjectID, String subjectName, boolean active) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.active = active;
    }

    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
    
}
