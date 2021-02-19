/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.naming.NamingException;
import longnv.connectDB.DBHelper;

/**
 *
 * @author Asus
 */
public class QuestionDAO {
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat dfDB = new SimpleDateFormat("yyyy-MM-dd");
    
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
    
    public ArrayList<QuestionDTO> getTenQuesttion(String subID) throws NamingException, SQLException{
        ArrayList<QuestionDTO> listDTO = null;
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "select QuestionID, Title from Question where subjectID = ? and status = 'true' order by newId() DESC offset 1 row fetch next 10 rows only";
                stm = conn.prepareStatement(sql);
                stm.setString(1, subID);
                rs = stm.executeQuery();
                listDTO = new ArrayList<>();
                while(rs.next()){
                    QuestionDTO dto = new QuestionDTO(rs.getString("QuestionID"), rs.getString("Title"));
                    listDTO.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        
        return listDTO;
    }
    
    public int getNumOfPage(String subjectID) throws NamingException, SQLException{
            int numOfPage = 0;

        try {
                conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "select count(QuestionID) as numOfQuestion from Question where SubjectID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, subjectID);
                rs = stm.executeQuery();
                if(rs.next()){
                    int numOfQuestion = rs.getInt("numOfQuestion");
                    if(numOfQuestion % 10 != 0){
                        numOfPage = numOfQuestion / 10 + 1; 
                    } else {
                        numOfPage = numOfQuestion / 10;
                    }
                }
            }
        
        } finally {
            closeConnection();
        }
        return numOfPage;
    }
    
    public int getNumOfPageWithSearch(String subjectID, String searchKey) throws NamingException, SQLException{
            int numOfPage = 0;

        try {
                conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "select count(QuestionID) as numOfQuestion from Question where SubjectID = ? and Title like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, subjectID);
                stm.setString(2, "%" + searchKey + "%");
                rs = stm.executeQuery();
                if(rs.next()){
                    int numOfPost = rs.getInt("numOfQuestion");
                    if(numOfPost % 10 != 0){
                        numOfPage = numOfPost / 10 + 1; 
                    } else {
                        numOfPage = numOfPost / 10;
                    }
                }
            }
        
        } finally {
            closeConnection();
        }
        return numOfPage;
    }
    
    public ArrayList<QuestionDTO> getQuestionByAdmin(int page, String subjectID) throws NamingException, SQLException, ParseException{
        ArrayList<QuestionDTO> listArt = null;

        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "select QuestionID, title, status from Question where subjectID = ? order by createDate DESC, QuestionID DESC offset (?-1)*10 row fetch next 10 rows only";
                
                stm = conn.prepareStatement(sql);
                stm.setString(1, subjectID);
                stm.setInt(2, page);
                rs = stm.executeQuery();
                listArt = new ArrayList<>();
                while(rs.next()){
                    String id = rs.getString("QuestionID");
                    String title = rs.getString("Title");
                    boolean status = rs.getBoolean("status");
                    
                     QuestionDTO dto = new QuestionDTO(id, title, status);
                    
                    listArt.add(dto);
                }

            }
        } finally {
            closeConnection();
        }
        
        return listArt;
    }
    
    public ArrayList<QuestionDTO> loadQuestionWithKeySearch(int page, String subjectID, String searchKey) throws NamingException, SQLException, ParseException{
        ArrayList<QuestionDTO> listArt = null;

        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "select QuestionID, title, status from Question where subjectID = ? and Title like ? order by createDate DESC, QuestionID DESC offset (?-1)*10 row fetch next 10 rows only";
                
                stm = conn.prepareStatement(sql);
                stm.setString(1, subjectID);
                stm.setString(2, "%" + searchKey + "%");
                stm.setInt(3, page);
                rs = stm.executeQuery();
                listArt = new ArrayList<>();
                while(rs.next()){
                    String id = rs.getString("QuestionID");
                    String title = rs.getString("Title");
                    boolean status = rs.getBoolean("status");
                    
                     QuestionDTO dto = new QuestionDTO(id, title, status);
                    
                    listArt.add(dto);
                }

            }
        } finally {
            closeConnection();
        }
        
        return listArt;
    }
    
    public boolean deleteQuestion(String questionID) throws NamingException, SQLException{
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "Update Question set status = 0 where QuestionID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, questionID);
                return (stm.executeUpdate() > 0);
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    
    public boolean restoreQuestion(String questionID) throws NamingException, SQLException{
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "Update Question set status = 1 where QuestionID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, questionID);
                return (stm.executeUpdate() > 0);
                
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    
    public boolean addQuestion(String questionID, String title, String subjectID) throws NamingException, SQLException{
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "insert into Question (QuestionID, Title, createDate, subjectID, status) values(?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, questionID);
                stm.setString(2, title);
                stm.setString(3, java.time.LocalDate.now() + "");
                stm.setString(4, subjectID);
                stm.setBoolean(5, true);
                if(stm.executeUpdate() > 0){
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    
    public boolean updateQuestion(String questionID, String title) throws NamingException, SQLException{
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "UPDATE Question SET QuestionID = ?, Title = ? where QuestionID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, questionID);
                stm.setString(2, title);
                stm.setString(3, questionID);
                
                if(stm.executeUpdate() > 0){
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    
    public void removeQuestion(String questionID) throws NamingException, SQLException{
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "Delete from where questionId = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, questionID);
                stm.executeUpdate();
            }
        } finally {
            closeConnection();
        }
    }
}
