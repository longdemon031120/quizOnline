/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.examDetail;

import java.io.Serializable;
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
public class ExamDetailDAO implements Serializable{
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
    
    public boolean saveDetail(ArrayList<ExamDetailDTO> examDetail) throws NamingException, SQLException{
        boolean result = false;
        try {
            conn = DBHelper.getConnection();
            conn.setAutoCommit(false);
            
            if(conn != null){
                for (ExamDetailDTO examDetailDTO : examDetail) {
                    String sql = "INSERT INTO ExamDetail (ExamID, QuestionID, QuestionTitle, AnswerCheckedID, Correct) values (?,?,?,?,?)";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, examDetailDTO.getExamID());
                    stm.setString(2, examDetailDTO.getQuestionID());
                    stm.setString(3, examDetailDTO.getQuestionTitle());
                    if(examDetailDTO.getAnswerCheckedID() == null){
                        stm.setNull(4, java.sql.Types.INTEGER);
                    } else {
                        stm.setString(4, examDetailDTO.getAnswerCheckedID());
                    }
                    
                    stm.setBoolean(5, examDetailDTO.isCorrect());
                    stm.executeUpdate();
                }
                conn.commit();
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public void deleteExameDeatil(String examID) throws NamingException, SQLException{
            try {
                conn = DBHelper.getConnection();
                if(conn != null){
                    String sql = "DELETE FROM ExameDetail WHERE ExamID=?";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, examID);
                    stm.executeUpdate();
                }
            } finally {
                closeConnection();
            }
        }
    
    public ArrayList<QuestionDTO> getQuestionInExam(String examID) throws NamingException, SQLException{
        ArrayList<QuestionDTO> listQuestion = null;
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "Select QuestionID, QuestionTitle from ExamDetail where examID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, examID);
                rs = stm.executeQuery();
                listQuestion = new ArrayList<>();
                while(rs.next()){
                    QuestionDTO dto = new QuestionDTO(rs.getString("QuestionID"), rs.getString("QuestionTitle"));
                    listQuestion.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listQuestion;
    }
}
