/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.controllers;

import dunggla.article.ArticleDAO;
import dunggla.article.ArticleDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class DeleteAllArticleServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.jsp";

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
        String newStatus = "Deleted";
        boolean result = false;
        try {

            String searchContent = request.getParameter("txtSearchContent");
            String searchTitle = request.getParameter("txtSearchByTitle");
            String status = request.getParameter("txtSearchBy");
            ArticleDAO dao = new ArticleDAO();

            // Search fiel is not empty
            if (!searchContent.equals("")) {
                // Don't search status
                if (status == null || status.equals("All")) {
                    dao.createListDeleteAllArticleNoStatus(searchContent, searchTitle);
                } else {
                    // search all info
                    dao.createListDeleteAllArticle(searchContent, searchTitle, status);
                }
            } else {
                // Search fiel is empty
                dao.createListDeleteAllNoInfoSearch();
            }

            List<ArticleDTO> list = dao.getList();
            if (list.size() > 0) {
                boolean check = true;
                for (ArticleDTO dto : list) {
                    String title = dto.getTitle();
                    String oldStatus = dto.getStatus();
                    if (!oldStatus.equals("Deleted")) {
                        result = dao.deleteArticle(title, oldStatus, newStatus);
                        if (!result) {
                            check = false;
                        }
                    } else {
                        result = true;
                    }
                }
                if (check == true && result == true) {
                    if (searchContent.equals("")) {
                        url = "DispatchController?btAction=ShowAll";
                    } else {
                        url = "DispatchController?btAction=Search+Article&txtSearchContent=" + searchContent + "&txtSearchByTitle=" + searchTitle + "&txtSearchBy=" + newStatus;
                    }
                }
            }
        } catch (NamingException e) {
            log("DeleteAllArticleServlet_NamingException " + e.getMessage());
        } catch (SQLException e) {
            log("DeleteAllArticleServlet_SQLException " + e.getMessage());
        } finally {
            response.sendRedirect(url);
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
