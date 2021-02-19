/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.examDetailAnswer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javax.naming.NamingException;
import longnv.answer.AnswerDTO;
import longnv.connectDB.DBHelper;
import longnv.question.QuestionDTO;

/**
 *
 * @author Asus
 */
public class ExamDetailAnswerDAO {
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    private void closeConnection() throws SQLException{
        if(rs != null){
            rs.close();
        }
        if(stm != null){
            stm.close();
        }
        if(conn != null){
            conn.close();
        }
        
    }
    
    public boolean saveExamDetaiAnswer(Map<QuestionDTO, ArrayList<AnswerDTO>> answerAndQuestion, ArrayList<String> listAnswerChecked, String examID) throws SQLException, NamingException{
        boolean result = false;
        try {
            conn = DBHelper.getConnection();
            conn.setAutoCommit(false);
            if(conn != null){
                String sql = "INSERT INTO ExamDetailAnswer (ExamID, QuestionID, AnswerID, AnswerContent, Checked, Correct) values (?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                for (QuestionDTO questionDTO : answerAndQuestion.keySet()) {
                    ArrayList<AnswerDTO> listAnswer = answerAndQuestion.get(questionDTO);
                    for (AnswerDTO answerDTO : listAnswer) {
                        stm.setString(1, examID);
                        stm.setString(2, questionDTO.getQuestionID());
                        stm.setString(3, answerDTO.getAnswerID());
                        String test = answerDTO.getAnswerContent();
                        stm.setString(4, answerDTO.getAnswerContent());
                        if(listAnswerChecked.contains(answerDTO.getAnswerID())){
                            stm.setBoolean(5, true);
                        } else {
                            stm.setBoolean(5, false);
                        }
                        if(answerDTO.isAnswerCorrect()){
                            stm.setBoolean(6, true);
                        } else {
                            stm.setBoolean(6, false);
                        }
                        stm.executeUpdate();
                    }
                }
                conn.commit();
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public ArrayList<ExamDetailAnswerDTO> getCompleteAnswer(String examID, ArrayList<QuestionDTO> listQuestion) throws NamingException, SQLException{
        ArrayList<ExamDetailAnswerDTO> listAnswerComplete= null;
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "Select QuestionID, AnswerContent, Checked, Correct from ExamDetailAnswer where QuestionID in (?,?,?,?,?,?,?,?,?,?) and examID = ?";
                stm = conn.prepareStatement(sql);
                int count = 1;
                for (QuestionDTO questionDTO : listQuestion) {
                    //String test = questionDTO.getQuestionID();
                    stm.setString(count, questionDTO.getQuestionID());
                    count++;
                }
                stm.setString(11, examID);
                rs = stm.executeQuery();
                listAnswerComplete = new ArrayList<>();
                while(rs.next()){
                    ExamDetailAnswerDTO dto = new ExamDetailAnswerDTO(rs.getString("QuestionID"), rs.getString("AnswerContent"), rs.getBoolean("Checked"), rs.getBoolean("Correct"));
                    listAnswerComplete.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listAnswerComplete;
    }
}
