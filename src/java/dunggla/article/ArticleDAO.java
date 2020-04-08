/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.article;

import dunggla.utils.DBUtilies;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class ArticleDAO implements Serializable {

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
        if (con != null) {
            con.close();
        }
    }

    private List<ArticleDTO> list;

    public List<ArticleDTO> getList() {
        return list;
    }

    // Get num of rows to compute num of page by Admin (show all)
    public int getNumOfArticlesShowAllAdmin()
            throws SQLException, NamingException {
        con = DBUtilies.makeConnection();
        int numOfArticle = 0;
        try {
            if (con != null) {
                String sql = "Select COUNT(Title) as NumOfArticle "
                        + "from Article";
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    numOfArticle = rs.getInt("NumOfArticle");
                }
            }
        } finally {
            closeConnection();
        }
        return numOfArticle;
    }

    // Get num of rows to compute num of page by Member (show all)
    public int getNumOfArticlesShowAllMember(String status)
            throws SQLException, NamingException {
        con = DBUtilies.makeConnection();
        int numOfArticle = 0;
        try {
            if (con != null) {
                String sql = "Select COUNT(Title) as NumOfArticle "
                        + "From Article "
                        + "Where StatusArticle = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, status);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    numOfArticle = rs.getInt("NumOfArticle");
                }
            }
        } finally {
            closeConnection();
        }
        return numOfArticle;
    }

    // Get num of rows to compute num of page by Member (Search)
    public int getNumOfArticlesMemberSearch(String searchValue, String statusInfo)
            throws SQLException, NamingException {
        con = DBUtilies.makeConnection();
        int numOfArticle = 0;
        try {
            if (con != null) {
                String sql = "Select COUNT(Title) as NumOfArticle "
                        + "From Article "
                        + "Where Content like ? and StatusArticle = ? ";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, "%" + searchValue + "%");
                stmt.setString(2, statusInfo);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    numOfArticle = rs.getInt("NumOfArticle");
                }
            }
        } finally {
            closeConnection();
        }
        return numOfArticle;
    }

    // Get num of rows to compute num of page by Admin (Search all info)
    public int getNumOfArticlesAdmin(String searchContent, String statusInfo, String searchTitle)
            throws SQLException, NamingException {
        con = DBUtilies.makeConnection();
        int numOfArticle = 0;
        try {
            if (con != null) {
                String sql = "Select COUNT(Title) as NumOfArticle "
                        + "From Article "
                        + "Where Content like ? and StatusArticle = ? and Title like ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, "%" + searchContent + "%");
                stmt.setString(2, statusInfo);
                stmt.setString(3, "%" + searchTitle + "%");
                rs = stmt.executeQuery();
                if (rs.next()) {
                    numOfArticle = rs.getInt("NumOfArticle");
                }
            }
        } finally {
            closeConnection();
        }
        return numOfArticle;
    }

    // Get num of rows to compute num of page by Admin (don't search status)
    public int getNumOfArticlesAdminNoStatus(String searchContent, String searchTitle)
            throws SQLException, NamingException {
        con = DBUtilies.makeConnection();
        int numOfArticle = 0;
        try {
            String sql = "Select COUNT(Title) as NumOfArticle "
                    + "From Article "
                    + "Where Content like ? and Title like ? ";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + searchContent + "%");
            stmt.setString(2, "%" + searchTitle + "%");
            rs = stmt.executeQuery();

            if (rs.next()) {
                    numOfArticle = rs.getInt("NumOfArticle");
                }
        } finally {
            closeConnection();
        }
        return numOfArticle;
    }
       
    // Get list all article by Member
    public void showAllOfMember(int firstIndex, int size, String statusInfo) throws NamingException, SQLException {
        con = DBUtilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select Title, ShortDescription, Content, Author, PostingDate, StatusArticle "
                        + "From Article  "
                        + "Where StatusArticle = ? "
                        + "order by PostingDate DESC "
                        + "offset ? rows "
                        + "fetch next ? rows only";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, statusInfo);
                stmt.setInt(2, firstIndex);
                stmt.setInt(3, size);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    String title = rs.getString("Title");
                    String shortDescription = rs.getString("ShortDescription");
                    String content = rs.getString("Content");
                    String author = rs.getString("Author");
                    Timestamp date = rs.getTimestamp("PostingDate");
                    String status = rs.getString("StatusArticle");
                    ArticleDTO dto = new ArticleDTO(title, shortDescription, content, author, date, status);

                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }

                    this.list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
    }

    // Get list all article by Admin
    public void showAllOfAdmin(int firstIndex, int size) throws NamingException, SQLException {
        con = DBUtilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select Title, ShortDescription, Content, Author, PostingDate, StatusArticle "
                        + "From Article "
                        + "order by PostingDate DESC "
                        + "offset ? rows "
                        + "fetch next ? rows only ";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, firstIndex);
                stmt.setInt(2, size);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    String title = rs.getString("Title");
                    String shortDescription = rs.getString("ShortDescription");
                    String content = rs.getString("Content");
                    String author = rs.getString("Author");
                    Timestamp date = rs.getTimestamp("PostingDate");
                    String status = rs.getString("StatusArticle");
                    ArticleDTO dto = new ArticleDTO(title, shortDescription, content, author, date, status);

                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }

                    this.list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
    }

    // Get list article search content by Member 
    public void searchByContentMember(String searchValue, int firstIndex, int size, String statusInfo) throws SQLException, NamingException {
        con = DBUtilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select Title, ShortDescription, Content, Author, PostingDate, StatusArticle "
                        + "From Article "
                        + "where Content like ? and StatusArticle = ? "
                        + "order by PostingDate DESC "
                        + "offset ? rows "
                        + "fetch next ? rows only";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, "%" + searchValue + "%");
                stmt.setString(2, statusInfo);
                stmt.setInt(3, firstIndex);
                stmt.setInt(4, size);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    String title = rs.getString("Title");
                    String shortDescription = rs.getString("ShortDescription");
                    String content = rs.getString("Content");
                    String author = rs.getString("Author");
                    Timestamp date = rs.getTimestamp("PostingDate");
                    String status = rs.getString("StatusArticle");
                    ArticleDTO dto = new ArticleDTO(title, shortDescription, content, author, date, status);

                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }
                    this.list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
    }

    // Get article by title 
    public ArticleDTO getArticleByTitle(String titleInfo) throws SQLException, NamingException {
        con = DBUtilies.makeConnection();
        ArticleDTO dto = null;
        try {
            if (con != null) {
                String sql = "Select Title, ShortDescription, Content, Author, PostingDate, StatusArticle "
                        + "From Article "
                        + "Where Title = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, titleInfo);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    String title = rs.getString("Title");
                    String shortDescription = rs.getString("ShortDescription");
                    String content = rs.getString("Content");
                    String author = rs.getString("Author");
                    Timestamp date = rs.getTimestamp("PostingDate");
                    String status = rs.getString("StatusArticle");

                    dto = new ArticleDTO(title, shortDescription, content, author, date, status);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    // Post Article
    public boolean postArticle(String title, String shortDescription, String content, String author, Timestamp date, String status)
            throws SQLException, NamingException {
        con = DBUtilies.makeConnection();
        boolean check = false;
        try {
            String sql = "Insert Article(Title,ShortDescription,Content,Author,PostingDate,StatusArticle) "
                    + "Values(?,?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, shortDescription);
            stmt.setString(3, content);
            stmt.setString(4, author);
            stmt.setTimestamp(5, date);
            stmt.setString(6, status);
            check = stmt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    // Get list article search by Admin (don't search status)
    public void searchByAdminNoStatus(String infoContent, String infoTitle, int firstIndex, int size)
            throws SQLException, NamingException {
        con = DBUtilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select Title, ShortDescription, Content, Author, PostingDate, StatusArticle "
                        + "From Article "
                        + "Where Content like ? and Title like ? "
                        + "order by PostingDate DESC "
                        + "offset ? rows "
                        + "fetch next ? rows only ";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, "%" + infoContent + "%");
                stmt.setString(2, "%" + infoTitle + "%");
                stmt.setInt(3, firstIndex);
                stmt.setInt(4, size);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    String title = rs.getString("Title");
                    String shortDescription = rs.getString("ShortDescription");
                    String content = rs.getString("Content");
                    String author = rs.getString("Author");
                    Timestamp date = rs.getTimestamp("PostingDate");
                    String status = rs.getString("StatusArticle");

                    ArticleDTO dto = new ArticleDTO(title, shortDescription, content, author, date, status);

                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }
                    this.list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
    }
    
    // Get list article search all information by Admin
    public void searchByAdmin(String infoContent, String infoTitle, String infoStatus, int firstIndex, int size)
            throws NamingException, SQLException {
        con = DBUtilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select Title, ShortDescription, Content, Author, PostingDate, StatusArticle "
                        + "From Article "
                        + "Where Content like ? and Title like ? and StatusArticle = ? "
                        + "order by PostingDate DESC "
                        + "offset ? rows "
                        + "fetch next ? rows only";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, "%" + infoContent + "%");
                stmt.setString(2, "%" + infoTitle + "%");
                stmt.setString(3, infoStatus);
                stmt.setInt(4, firstIndex);
                stmt.setInt(5, size);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    String title = rs.getString("Title");
                    String shortDescription = rs.getString("ShortDescription");
                    String content = rs.getString("Content");
                    String author = rs.getString("Author");
                    Timestamp date = rs.getTimestamp("PostingDate");
                    String status = rs.getString("StatusArticle");

                    ArticleDTO dto = new ArticleDTO(title, shortDescription, content, author, date, status);

                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }
                    this.list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
    }

    // Accept a article
    public boolean acceptArticle(String newStatus, String title) throws SQLException, NamingException {
        con = DBUtilies.makeConnection();
        boolean check = false;
        try {
            if (con != null) {
                String sql = "Update Article "
                        + "set StatusArticle = ? "
                        + "where Title = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, newStatus);
                stmt.setString(2, title);

                check = stmt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    // Get list articles that need deleting (Search all info)
    public void createListDeleteAllArticle(String searchContent, String searchTitle, String statusInfo)
            throws SQLException, NamingException {
        con = DBUtilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select Title, ShortDescription, Content, Author, PostingDate, StatusArticle "
                        + "From Article "
                        + "where Content like ? and Title like ? and StatusArticle = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, "%" + searchContent + "%");
                stmt.setString(2, "%" + searchTitle + "%");
                stmt.setString(3, statusInfo);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    String title = rs.getString("Title");
                    String shortDescription = rs.getString("ShortDescription");
                    String content = rs.getString("Content");
                    String author = rs.getString("Author");
                    Timestamp date = rs.getTimestamp("PostingDate");
                    String status = rs.getString("StatusArticle");
                    ArticleDTO dto = new ArticleDTO(title, shortDescription, content, author, date, status);

                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }

                    this.list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
    }

    //  Get list articles that need deleting (don't search status)
    public void createListDeleteAllArticleNoStatus(String searchContent, String searchTitle)
            throws NamingException, SQLException {
        con = DBUtilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select Title, ShortDescription, Content, Author, PostingDate, StatusArticle "
                        + "From Article "
                        + "Where Title like ? and Content like ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, "%" + searchTitle + "%");
                stmt.setString(2, "%" + searchContent + "%");
                rs = stmt.executeQuery();

                while (rs.next()) {
                    String title = rs.getString("Title");
                    String shortDescription = rs.getString("ShortDescription");
                    String content = rs.getString("Content");
                    String author = rs.getString("Author");
                    Timestamp date = rs.getTimestamp("PostingDate");
                    String status = rs.getString("StatusArticle");
                    ArticleDTO dto = new ArticleDTO(title, shortDescription, content, author, date, status);

                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }

                    this.list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
    }

    // Get list articles that need deleting (default page)
    public void createListDeleteAllNoInfoSearch() throws NamingException, SQLException {
        con = DBUtilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select Title, ShortDescription, Content, Author, PostingDate, StatusArticle "
                        + "From Article ";
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    String title = rs.getString("Title");
                    String shortDescription = rs.getString("ShortDescription");
                    String content = rs.getString("Content");
                    String author = rs.getString("Author");
                    Timestamp date = rs.getTimestamp("PostingDate");
                    String status = rs.getString("StatusArticle");
                    ArticleDTO dto = new ArticleDTO(title, shortDescription, content, author, date, status);

                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }

                    this.list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
    }
    
    // Get old status by title to restore
    public String getOldStatusByTitle(String title) throws NamingException, SQLException{
        con = DBUtilies.makeConnection();
        String oldStatus = "";
        try {
            if (con != null) {
                String sql = "Select OldStatus "
                        + "From Article "
                        + "Where Title = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, title);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    oldStatus = rs.getString("OldStatus");
                }
            }
        } finally {
            closeConnection();
        }
        return oldStatus;
    }
    
    // Get present status that need for restore
    public String getPresentStatusByTitle(String title) throws NamingException, SQLException{
        con = DBUtilies.makeConnection();
        String oldStatus = "";
        try {
            if (con != null) {
                String sql = "Select StatusArticle "
                        + "From Article "
                        + "Where Title = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, title);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    oldStatus = rs.getString("StatusArticle");
                }
            }
        } finally {
            closeConnection();
        }
        return oldStatus;
    }
    
    // restore article
    public boolean undoDelete(String title, String oldStatus) throws NamingException, SQLException{
        con = DBUtilies.makeConnection();
        boolean check = false;
        try {
            if (con != null) {
                String sql = "Update Article "
                        + "Set StatusArticle = ?, OldStatus = ? "
                        + "Where Title = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, oldStatus);
                stmt.setString(2, "");
                stmt.setString(3, title);
                
                check = stmt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
    // Delete article
    public boolean deleteArticle(String title, String oldStatus, String newStatus)
        throws NamingException, SQLException{
        con = DBUtilies.makeConnection();
        boolean check = false;
        try {
            if (con != null) {
                String sql = "Update Article "
                        + "set StatusArticle = ?, OldStatus = ? "
                        + "where Title = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, newStatus);
                stmt.setString(2, oldStatus);
                stmt.setString(3, title);

                check = stmt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
}
