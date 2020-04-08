/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.comments;

import dunggla.utils.DBUtilies;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class CommentsDAO implements Serializable{
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    // Close connection with SQL Server
    private void closeConnnection() throws SQLException{
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (con != null) {
            con.close();
        }
    }
    
    private List<CommentsDTO> listCmt;

    public List<CommentsDTO> getListCmt() {
        return listCmt;
    }
    
    // Get list comment by title
    public void getCommentByTitle(String titleInfo) throws SQLException, NamingException{
        con = DBUtilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select IDComment, Comment, Name, Title "
                        + "From Comments "
                        + "Where Title = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, titleInfo);
                rs = stmt.executeQuery();
                while (rs.next()) {                    
                    int idCmt = rs.getInt("IDComment");
                    String comment = rs.getString("Comment");
                    String name = rs.getString("Name");
                    String title = rs.getString("Title");
                    CommentsDTO dto = new CommentsDTO(idCmt, name, comment, title);
                    
                    if (this.listCmt == null) {
                        this.listCmt = new ArrayList<>();
                    }
                    this.listCmt.add(dto);
                }
            }
        } finally {
            closeConnnection();
        }
    }
    
    // Get max id cmt to insert new comment ( id = max + 1)
    private int getLastIDComment() throws NamingException, SQLException{
        con = DBUtilies.makeConnection();
        int lastID = 0;
        try {
            if (con != null) {
                String sql = "Select MAX(IDComment) as maxID "
                        + "From Comments ";
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    lastID = rs.getInt("maxID");
                }
            }
        } finally {
            closeConnnection();
        }
        return lastID;
    }
    
    // Insert comment
    public boolean addComment(String cmt, String name, String title) throws SQLException, NamingException{        
        int idCmt = getLastIDComment() + 1;
        con = DBUtilies.makeConnection();
        boolean check = false;
        try {
            if (con != null) {
                String sql = "Insert Comments(IDComment,Name,Comment,Title) "
                        + "Values(?,?,?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, idCmt);
                stmt.setString(2, name);
                stmt.setString(3, cmt);
                stmt.setString(4, title);
                
                check = stmt.executeUpdate() > 0;
            }
        } finally {
            closeConnnection();
        }
        return check;
    }
    
    // Delete comment by email
    public boolean deleteCmt(String email) throws NamingException, SQLException{
        con = DBUtilies.makeConnection();
        boolean check = false;
        try {
            if (con!=null) {
                String sql = "Delete from Comments "
                        + "Where Name = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, email);
                check = stmt.executeUpdate() > 0;
            }
        } finally {
            closeConnnection();
        }
        return check;
    }
    
    // Delete comment by title
    public boolean deleteCmtByTitle(String title) throws NamingException, SQLException{
        con = DBUtilies.makeConnection();
        boolean check = false;
        try {
            if (con != null) {
                String sql = "Delete from Comments "
                        + "Where Title = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, title);
                check = stmt.executeUpdate() > 0;
                
            }
        } finally {
            closeConnnection();
        }
        return check;
    }
    
    // find comment of user
    public int findCmt(String email) throws NamingException,SQLException{
        con = DBUtilies.makeConnection();
        int count = 0;
        try {
            String sql = "Select COUNT(IDComment) as quanCmt "
                    + "From Comments "
                    + "Where Name = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("quanCmt");
            }
        } finally {
            closeConnnection();
        }
        return count;
    }
}
