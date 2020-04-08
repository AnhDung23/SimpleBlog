/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.controllers;

import dunggla.article.ArticleDAO;
import dunggla.article.ArticleDTO;
import dunggla.comments.CommentsDAO;
import dunggla.comments.CommentsDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
public class ShowDetailArticleServlet extends HttpServlet {
    private final String ERROR_PAGE = "error.jsp";
    private final String SHOW_DETAIL_PAGE = "detailArticle.jsp";
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
        String title = request.getParameter("txtTitle");       
        try {
            ArticleDAO daoArt = new ArticleDAO();
            ArticleDTO dtoArt = daoArt.getArticleByTitle(title);

            if (dtoArt != null) {
                request.setAttribute("DTO_ARTICLE", dtoArt);
            }
            
            CommentsDAO daoCmt = new CommentsDAO();
            daoCmt.getCommentByTitle(title);
            List<CommentsDTO> listCmt = daoCmt.getListCmt();
            
            request.setAttribute("LIST_CMT", listCmt);
            url = SHOW_DETAIL_PAGE;
            
        }catch(SQLException e){
            log("ShowDetailArticleServlet_SQLException "+e.getMessage());
        }catch(NamingException e){
            log("ShowDetailArticleServlet_NamingException "+e.getMessage());
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
