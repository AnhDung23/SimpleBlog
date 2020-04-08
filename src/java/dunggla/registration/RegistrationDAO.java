/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.registration;

import dunggla.email.Email;
import dunggla.utils.DBUtilies;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class RegistrationDAO implements Serializable {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    // Close connection with SQL Server
    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

    // Create new account
    public boolean createAccount(String email, String name, String password, String role, String status, String code)
            throws NamingException, SQLException {
        con = DBUtilies.makeConnection();
        boolean check = false;
        try {
            if (con != null) {
                String sql = "Insert Into Registration(Email,Name,Password,Role,Status,Code) "
                        + "values(?,?,?,?,?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, email);
                stmt.setString(2, name);
                stmt.setString(3, password);
                stmt.setString(4, role);
                stmt.setString(5, status);
                stmt.setString(6, code);

                check = stmt.executeUpdate() > 0;
                if (check) {
                    Email e = new Email(email, code);
                    e.senMail();
                }
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    // check code verify account
    public boolean verifyAccount(String email, String code)
            throws NamingException, SQLException {
        con = DBUtilies.makeConnection();
        boolean check = false;
        try {
            if (con != null) {
                String sql = "Update Registration set Status='Active' where Email = ? and Code = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, email);
                stmt.setString(2, code);

                check = stmt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
    // check login user
    public String checkLogin(String email, String password)
            throws SQLException, NamingException {
        con = DBUtilies.makeConnection();
        String role = "failed";
        try {
            if (con != null) {
                String sql = "Select Role From Registration "
                        + "Where email=? and password=? and Status='Active'";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, email);
                stmt.setString(2, password);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    role = rs.getString("Role");
                }
            }
        } finally {
            closeConnection();
        }
        return role;
    }

    // get account for welcome, cmt, post article... function 
    public RegistrationDTO getAccount(String emailInfo, String passwordInfo)
            throws NamingException, SQLException {
        con = DBUtilies.makeConnection();
        RegistrationDTO dto = null;
        try {
            if (con != null) {
                String sql = "Select Email, Name, Password, Role, Status "
                        + "From Registration "
                        + "Where email=? and password=?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, emailInfo);
                stmt.setString(2, passwordInfo);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    String email = rs.getString("Email");
                    String password = rs.getString("Password");
                    String role = rs.getString("Role");
                    String name = rs.getString("Name");
                    String status = rs.getString("Status");
                    
                    dto = new RegistrationDTO(email, name, password, role, status);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    // Delete user
    public boolean deleteUser(String email, String status) throws NamingException, SQLException{
        con = DBUtilies.makeConnection();
        boolean check = false;
        try {
            if (con != null) {
                String sql = "Update Registration "
                        + "Set Status = ? "
                        + "Where Email = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, status);
                stmt.setString(2, email);
                check = stmt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
}
