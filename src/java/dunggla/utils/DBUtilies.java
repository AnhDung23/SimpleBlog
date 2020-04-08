/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.utils;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Admin
 */
public class DBUtilies implements Serializable{
    public static Connection makeConnection() throws NamingException,SQLException{
        
        Context ctx = new InitialContext();
        Context tomcatCtx = (Context) ctx.lookup("java:comp/env");
        DataSource ds = (DataSource) tomcatCtx.lookup("lab1");
        
        Connection con = ds.getConnection();
        return con;
    }
    
    public static String makeEncryptPassword(String password) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return new BigInteger(1, md.digest(password.getBytes())).toString();
    }
}
