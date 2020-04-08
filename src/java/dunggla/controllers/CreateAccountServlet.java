/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.controllers;

import dunggla.registration.RegistrationDAO;
import dunggla.registration.RegistrationError;
import dunggla.utils.DBUtilies;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Random;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class CreateAccountServlet extends HttpServlet {
    private final String ERROR_PAGE = "registration.jsp";
    private final String HOME_PAGE = "verify.jsp";
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
        boolean foundError = false;
        RegistrationError errors = new RegistrationError();
        
        try {
            String email = request.getParameter("txtEmail");
            String name = request.getParameter("txtName");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            String role = request.getParameter("txtRole");
            String status = request.getParameter("txtStatus");

            if (email.trim().length() > 40 || email.trim().length() == 0) {
                foundError = true;
                errors.setEmailLengthError("Email length requires smaller than or equal 40");
            } else if (!errors.checkSyntaxEmail(email.trim())) {
                foundError = true;
                errors.setEmailSyntaxError("Email syntax requires email@address.com");
            }

            if (name.trim().length() < 2 || name.trim().length() > 50) {
                foundError = true;
                errors.setNameLengthError("Name length requires from 2 to 50 chars");
            }

            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundError = true;
                errors.setPasswordLengthError("Password length requires from 6 to 30 chars");
            } else if (!confirm.trim().equals(password.trim())) {
                foundError = true;
                errors.setConfirmNotMatchError("Confirm not match password");
            }

            if (foundError) {
                request.setAttribute("ERROR", errors);
            } else {
                RegistrationDAO dao = new RegistrationDAO();
                String passwordEncrypt = DBUtilies.makeEncryptPassword(password);
                Random ran = new Random();
                int verify = ran.nextInt(9999);
                
                String code = Integer.toString(verify);
                System.out.println(code);
                System.out.println(verify);
                boolean result = dao.createAccount(email, name, passwordEncrypt, role, "New", code);
                if (result) {
                    request.setAttribute("MESSAGE", "Create account successfull");
                    request.setAttribute("EMAIL", email);
                    url = HOME_PAGE;
                }
            }
        } catch (SQLException e) {
            log("CreateAccountServlet_SQLException " + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                errors.setEmailExistedError("Email is existed");
                request.setAttribute("ERROR", errors);
            }
        } catch (NamingException e) {
            log("CreateAccountServlet_NamingException " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            log("CreateAccountServlet_NoSuchAlgorithmException "+e.getMessage());
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
