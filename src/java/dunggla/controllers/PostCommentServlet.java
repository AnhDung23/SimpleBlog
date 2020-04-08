/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.controllers;

import dunggla.comments.CommentsDAO;
import dunggla.registration.RegistrationDTO;
import java.io.IOException;
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
public class PostCommentServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.jsp";
    private final String POST_COMMENT_PAGE = "detailArticle.jsp";
    private final String POST_CMT_VIEW = "DispatchController?btAction=ShowDetail&txtTitle=";

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

        String url = POST_COMMENT_PAGE;
        String flag = request.getParameter("flag");

        try {

            String cmt = request.getParameter("txtCmt");
            String title = request.getParameter("txtTitle");
            HttpSession session = request.getSession(false);
            if (session != null) {
                // Get user post comment
                RegistrationDTO dto = (RegistrationDTO) session.getAttribute("ACCOUNT_DTO");
                if (dto != null) {
                    String name = dto.getEmail();
                    
                    // if comment fiel was empty
                    if (cmt.trim().equals("")) {
                        request.setAttribute("MESSAGE", "Full fill comment fiel");
                        url = POST_CMT_VIEW + title;
                    } else {
                        CommentsDAO dao = new CommentsDAO();
                        boolean check = dao.addComment(cmt, name, title);
                        if (check) {
                            url = POST_CMT_VIEW + title;
                        }
                    }
                }
            }

        } catch (SQLException e) {
            log("PostCommentServlet_SQLException " + e.getMessage());
        } catch (NamingException e) {
            log("PostCommentServlet_NamingException " + e.getMessage());
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
