/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import longnv.connectDB.DBHelper;
import longnv.question.QuestionDTO;

/**
 *
 * @author Asus
 */
public class AnswerDAO {
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
    
    public ArrayList<AnswerDTO> getAnswer(ArrayList<QuestionDTO> listQuestion) throws SQLException, NamingException{
        ArrayList<AnswerDTO> listAnswer = null;
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "select AnswerID, AnswerContent, AnswerCorrect, QuestionID from Answer where QuestionID in (?,?,?,?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                int count = 1;
                for (QuestionDTO questionDTO : listQuestion) {
                    //String test = questionDTO.getQuestionID();
                    stm.setString(count, questionDTO.getQuestionID());
                    count++;
                }
                rs = stm.executeQuery();
                listAnswer = new ArrayList<>();
                while(rs.next()){
                    AnswerDTO dto = new AnswerDTO(rs.getString("AnswerID"), rs.getString("AnswerContent"), rs.getBoolean("AnswerCorrect"), rs.getString("QuestionID"));
                    listAnswer.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listAnswer;
    }
    
    public ArrayList<AnswerDTO> getAnswerForOneQuestion(String questionID) throws SQLException, NamingException{
        ArrayList<AnswerDTO> listAnswer = null;
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "select AnswerID, AnswerContent, AnswerCorrect from Answer where QuestionID = ?";
                stm = conn.prepareStatement(sql);
                
                stm.setString(1, questionID);
                
                rs = stm.executeQuery();
                listAnswer = new ArrayList<>();
                while(rs.next()){
                    String id = rs.getString("AnswerID");
                    String content = rs.getString("AnswerContent");
                    boolean correct = rs.getBoolean("AnswerCorrect");
                    AnswerDTO dto = new AnswerDTO(id, content, correct);
                    listAnswer.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listAnswer;
    }
    
    public boolean addAnswer(ArrayList<AnswerDTO> listAnswer) throws SQLException, NamingException{
        try {
            conn = DBHelper.getConnection();
            conn.setAutoCommit(false);
            if(conn!= null){
                for (AnswerDTO answerDTO : listAnswer) {
                    String sql = "insert into Answer (AnswerID, AnswerContent, AnswerCorrect, QuestionID) values(?,?,?,?)";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, answerDTO.getAnswerID());
                    stm.setString(2, answerDTO.getAnswerContent());
                    stm.setBoolean(3, answerDTO.isAnswerCorrect());
                    stm.setString(4, answerDTO.getQuestionID());
                    stm.executeUpdate();
                }
                conn.commit();
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    
    public boolean updateAnswer(ArrayList<AnswerDTO> listAnswer) throws SQLException, NamingException{
        try {
            conn = DBHelper.getConnection();
            conn.setAutoCommit(false);
            if(conn!= null){
                for (AnswerDTO answerDTO : listAnswer) {
                    String sql = "UPDATE Answer SET AnswerContent = ?, AnswerCorrect = ? where AnswerID = ?";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, answerDTO.getAnswerContent());
                    stm.setBoolean(2, answerDTO.isAnswerCorrect());
                    stm.setString(3, answerDTO.getAnswerID());
                    stm.executeUpdate();
                }
                conn.commit();
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
