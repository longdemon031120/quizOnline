/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.subject;

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
public class SubjectDAO {
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
    
    public ArrayList<SubjectDTO> getSubject() throws NamingException, SQLException{
        ArrayList<SubjectDTO> listDTO = null;
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "Select subID, subName from Subject where active = 1 order by subName DESC";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                listDTO = new ArrayList<>();
                while(rs.next()){
                    SubjectDTO dto = new SubjectDTO(rs.getString("subID"), rs.getString("subName"));
                    listDTO.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listDTO;
    }
    public ArrayList<SubjectDTO> getSubjectByAdmin() throws NamingException, SQLException{
        ArrayList<SubjectDTO> listDTO = null;
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "Select subID, subName, active from Subject order by subName DESC";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                listDTO = new ArrayList<>();
                while(rs.next()){
                    SubjectDTO dto = new SubjectDTO(rs.getString("subID"), rs.getString("subName"), rs.getBoolean("active"));
                    listDTO.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listDTO;
    }
    
    public boolean addSubject(String id, String name) throws NamingException, SQLException{
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "Insert into Subject (subID, subName) values (?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, id);
                stm.setString(2, name);
                return (stm.executeUpdate() > 0);
                
            }
        } finally {
            closeConnection();;
        }
        return false;
    }
    public boolean deleteSubject(String id) throws NamingException, SQLException{
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "Update Subject set active = 0 where SubID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, id);
                return (stm.executeUpdate() > 0);
                
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    public boolean restoreSubject(String id) throws NamingException, SQLException{
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "Update Subject set active = 1 where SubID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, id);
                return (stm.executeUpdate() > 0);
                
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
