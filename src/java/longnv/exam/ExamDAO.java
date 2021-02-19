/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import longnv.connectDB.DBHelper;

/**
 *
 * @author Asus
 */
public class ExamDAO {
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
    
        public boolean saveExam(String examID, String accountID, String subjectID, int scores) throws NamingException, SQLException{
        boolean result = false;
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "INSERT INTO Exam (examID, accountID, subjectID, ExamDate, scores) values(?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, examID);
                stm.setString(2, accountID);
                stm.setString(3, subjectID);
                stm.setString(4, java.time.LocalDate.now() + "");
                stm.setInt(5, scores);
                stm.executeUpdate();
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }
        
        public void deleteExame(String examID) throws NamingException, SQLException{
            try {
                conn = DBHelper.getConnection();
                if(conn != null){
                    String sql = "DELETE FROM Exame WHERE ExamID=?";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, examID);
                    stm.executeUpdate();
                }
            } finally {
                closeConnection();
            }
        }
    
    public ArrayList<ExamDTO> getExam(String user, int page) throws NamingException, SQLException{
        ArrayList<ExamDTO> listExam = null;
        try {
           conn = DBHelper.getConnection();
           if(conn != null){
               String sql = "select ExamID, SubjectID, ExamDate, scores from Exam where AccountID = ? order by ExamDate DESC offset (?-1)*10 row fetch next 10 rows only";
               
               stm = conn.prepareStatement(sql);
               stm.setString(1, user);
               stm.setInt(2, page);
               rs = stm.executeQuery();
               listExam = new ArrayList<>();
               while(rs.next()){
                   ExamDTO dto = new ExamDTO(rs.getString("ExamID"), rs.getDate("ExamDate"), rs.getInt("scores") , rs.getString("SubjectID"));
                   listExam.add(dto);
               }
           }
        } finally {
            closeConnection();
        }
        return listExam;
    }   
    
    public ArrayList<ExamDTO> getExamWithFilter(String user, int page, String subject) throws NamingException, SQLException{
        ArrayList<ExamDTO> listExam = null;
        try {
           conn = DBHelper.getConnection();
           if(conn != null){
               String sql = "select ExamID, SubjectID, ExamDate, scores from Exam where AccountID = ? and SubjectID = ? order by ExamDate DESC offset (?-1)*10 row fetch next 10 rows only";
               
               stm = conn.prepareStatement(sql);
               stm.setString(1, user);
               stm.setString(2, subject);
               stm.setInt(3, page);
               rs = stm.executeQuery();
               listExam = new ArrayList<>();
               while(rs.next()){
                   ExamDTO dto = new ExamDTO(rs.getString("ExamID"), rs.getDate("ExamDate"), rs.getInt("scores") , rs.getString("SubjectID"));
                   listExam.add(dto);
               }
           }
        } finally {
            closeConnection();
        }
        return listExam;
    }   
    
    public int getRate(String examID) throws NamingException, SQLException{
        int rate = 0;
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "select scores from Exam where ExamID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, examID);
                rs = stm.executeQuery();
                if(rs.next()){
                    rate = rs.getInt("scores");
                }
            }
        } finally {
            closeConnection();
        }
        return rate;
    }
    
    public int countPageForHistory(String email) throws NamingException, SQLException {
        int numOfPage = 0;
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "select count(ExamID) as numOfExam from Exam where AccountID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if(rs.next()){
                    int numOfExam = rs.getInt("numOfExam");
                    if(numOfExam % 10 != 0){
                        numOfPage = numOfExam / 10 + 1; 
                    } else {
                        numOfPage = numOfExam / 10;
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return numOfPage;
    } 
    public int countPageWithFilter(String email, String subject) throws NamingException, SQLException {
        int numOfPage = 0;
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "select count(ExamID) as numOfExam from Exam where AccountID = ? and SubjectID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, subject);
                rs = stm.executeQuery();
                if(rs.next()){
                    int numOfExam = rs.getInt("numOfExam");
                    if(numOfExam % 10 != 0){
                        numOfPage = numOfExam / 10 + 1; 
                    } else {
                        numOfPage = numOfExam / 10;
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return numOfPage;
    } 
}
