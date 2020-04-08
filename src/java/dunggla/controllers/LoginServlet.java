/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.controllers;

import dunggla.registration.RegistrationDAO;
import dunggla.registration.RegistrationDTO;
import dunggla.utils.DBUtilies;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {

    private final String ERROR_PAGE = "login.jsp";
    private final String MEMBER_PAGE = "DispatchController?btAction=ShowAll";
    private final String ADMIN_PAGE = "DispatchController?btAction=ShowAll";
    private final String POST_ARTICLE_PAGE = "postArticle.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;
        String flag = request.getParameter("txtFlag");

        try {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String passwordEncryp = DBUtilies.makeEncryptPassword(password);
            RegistrationDAO dao = new RegistrationDAO();

            String role = dao.checkLogin(email, passwordEncryp);
            if (role.equals("admin")) {
                url = ADMIN_PAGE;
            } else if (role.equals("member")) {
                // if member haven't login yet but post article  
                if (flag.equals("loginToPostArticle")) {
                    url = POST_ARTICLE_PAGE;
                } else {
                    url = MEMBER_PAGE;
                }
            } else {
                request.setAttribute("ERROR", "Invalid email or password!!!");
            }

            if (role.equals("admin") || role.equals("member")) {
                // Save info user 
                HttpSession session = request.getSession();
                RegistrationDTO dto = dao.getAccount(email, passwordEncryp);
                session.setAttribute("ACCOUNT_DTO", dto);
            }

        } catch (NoSuchAlgorithmException e) {
            log("LoginServlet_NoSuchAlgorithmException " + e.getMessage());
        } catch (SQLException e) {
            log("LoginServlet_SQLException " + e.getMessage());
        } catch (NamingException e) {
            log("LoginServlet_NamingException " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
