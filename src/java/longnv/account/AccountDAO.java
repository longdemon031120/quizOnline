/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import longnv.connectDB.DBHelper;

/**
 *
 * @author Asus
 */
public class AccountDAO {
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
    
    public String checkLogin(String userID, String password) throws SQLException, NamingException{
        String result = "false";
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                
                String sqlCheckEmail = "Select Email from Account Where Email = ? and status = 1";
                stm = conn.prepareStatement(sqlCheckEmail);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if(rs.next()){
                    result = "half_true";
                    String sqlCheckPass = "Select Email from Account Where Email = ? and Password = ?";
                    stm = conn.prepareStatement(sqlCheckPass);
                    stm.setString(1, userID);
                    stm.setString(2, password);
                    rs = stm.executeQuery();
                    if(rs.next()){
                        result = "true";
                    }
                }
            }
        } finally {
            closeConnection();
        }
        
        return result;  
    }
    public String getName(String email) throws NamingException, SQLException{
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "Select name from Account Where email = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if(rs.next()){
                    return rs.getString("name");
                }
            }
        } finally {
            closeConnection();
        }
        return null;
    }
    public int getRole(String email) throws NamingException, SQLException{
        try {
            conn = DBHelper.getConnection();
            if(conn != null){
                String sql = "Select role from Account Where email = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if(rs.next()){
                    return rs.getInt("role");
                }
            }
        } finally {
            closeConnection();
        }
        return 0;
    }
    public boolean createAccount(AccountDTO dto) throws NamingException, SQLException{
        try{
            conn = DBHelper.getConnection();
            if(conn != null){
            String sql = "Insert into Account(email, password, name, role, status) Values(?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getEmail());
            stm.setString(2, dto.getPass());
            stm.setString(3, dto.getName());
            stm.setBoolean(4, dto.isRole());
            stm.setBoolean(5, dto.isStatus());
            
            if(stm.executeUpdate() > 0){
                return  true;
            }
            
            } 
        }finally {
            if(stm != null)
                stm.close();
            if(conn != null)
                conn.close();    
        }
        return false;
        
    }
}
