/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.controllers;

import dunggla.registration.RegistrationDTO;
import java.io.IOException;
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
public class DispatchController extends HttpServlet {

    private final String ERROR_PAGE = "error.jsp";
    private final String FIRST_PAGE = "DispatchController?btAction=ShowAll";
    private final String REGISTRATION_SERVLET = "CreateAccountServlet";
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String LOGOUT_SERVLET = "LogoutServlet";
    private final String SHOW_ALL_SERVLET = "ShowAllServlet";
    private final String SEARCH_SERVLET = "SearchByContentServlet";
    private final String SHOW_DETAIL_ARTICLE_SERVLET = "ShowDetailArticleServlet";
    private final String POST_ARTICLE_SERVLET = "PostArticleServlet";
    private final String POST_COMMENT_SERVLET = "PostCommentServlet";
    private final String ADMIN_SEARCH_SERVLET = "AdminSearchServlet";
    private final String APPROVAL_ARTICLE_SERVLET = "ApprovalArticleServlet";
    private final String DELETE_ARTICLE_SERVLET = "DeleteArticleServlet";
    private final String DELETE_ALL_ARTICLE_SERVLET = "DeleteAllArticleServlet";
    private final String UNDO_DELETE_ARTICLE_SERVLET = "UndoDeleteArticleServlet";
    private final String DELETE_CMT_SERVLET = "DeleteCmtServlet";
    private final String DELETE_USER_SERVLET = "DeleteMemberServlet";
    private final String VERIFY_SERVLET = "VerifyEmailServlet";

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

        try {
            String button = request.getParameter("btAction");
            RegistrationDTO dto = null;
            HttpSession session = request.getSession(false);
            if (session != null) {
                dto = (RegistrationDTO) session.getAttribute("ACCOUNT_DTO");
            }           

            if (button == null) {
                url = FIRST_PAGE;
            } else if (button.equals("Create Account")) {
                url = REGISTRATION_SERVLET;
            } else if (button.equals("Login")) {
                url = LOGIN_SERVLET;
            } else if (button.equals("Logout")) {
                url = LOGOUT_SERVLET;
            } else if (button.equals("ShowAll")) {
                url = SHOW_ALL_SERVLET;
            } else if (button.equals("Search")) {
                url = SEARCH_SERVLET;
            } else if (button.equals("ShowDetail")) {
                url = SHOW_DETAIL_ARTICLE_SERVLET;
            } else if (button.equals("Post")) {
                url = POST_ARTICLE_SERVLET;
            } else if (button.equals("Comment") && (dto != null && dto.getRole().equals("member"))) {
                url = POST_COMMENT_SERVLET;
            } else if (button.equals("Search Article") && (dto != null && dto.getRole().equals("admin"))) {
                url = ADMIN_SEARCH_SERVLET;
            } else if (button.equals("Accept") && (dto != null && dto.getRole().equals("admin"))) {
                url = APPROVAL_ARTICLE_SERVLET;
            } else if (button.equals("Delete") && (dto != null && dto.getRole().equals("admin"))) {
                url = DELETE_ARTICLE_SERVLET;
            } else if (button.equals("Delete All") && (dto != null && dto.getRole().equals("admin"))) {
                url = DELETE_ALL_ARTICLE_SERVLET;
            } else if (button.equals("Undo") && (dto != null && dto.getRole().equals("admin"))) {
                url = UNDO_DELETE_ARTICLE_SERVLET;
            } else if (button.equals("DeleteCmt") && (dto != null && dto.getRole().equals("admin"))) {
                url = DELETE_CMT_SERVLET;
            } else if (button.equals("DeleteEmail") && (dto != null && dto.getRole().equals("admin"))) {
                url = DELETE_USER_SERVLET;  
            }else if (button.equals("Sent")) {
                url = VERIFY_SERVLET;
            } else {
                request.setAttribute("ERROR", "Your action is invalid!!!");
            }
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
