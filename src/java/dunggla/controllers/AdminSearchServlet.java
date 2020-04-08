/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.controllers;

import dunggla.article.ArticleDAO;
import dunggla.article.ArticleDTO;
import dunggla.paging.PagingTable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
public class AdminSearchServlet extends HttpServlet {

    private final int SIZE_PAGE = 20;
    private final String SHOW_ALL_PAGE = "DispatchController?btAction=ShowAll";
    private final String SEARCH_PAGE = "admin.jsp";

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
        String url = SHOW_ALL_PAGE;
        String numPage = request.getParameter("txtPage");
        int numberOfPages = 0;
        int numberOfRows = 0;
        int firstIndex = 0;

        try {
            if (numPage == null) {
                numPage = "1";
            }

            String searchContent = request.getParameter("txtSearchContent");
            String searchTitle = request.getParameter("txtSearchByTitle");
            String status = request.getParameter("txtSearchBy");

            request.setAttribute("STATUS_SEARCH", status);
            ArticleDAO dao = new ArticleDAO();

            // Search fiel is not empty
            if (!searchContent.trim().equals("")) {
                PagingTable paging = new PagingTable();
                
                // Don't search status
                if (status == null || status.equals("All")) {
                    status = "";
                    numberOfRows = dao.getNumOfArticlesAdminNoStatus(searchContent.trim(), searchTitle.trim());
                } else {
                    // search all info
                    numberOfRows = dao.getNumOfArticlesAdmin(searchContent.trim(), status, searchTitle.trim());
                }
                paging.setNumberOfRows(numberOfRows);
                numberOfPages = paging.findNumberOfPages(SIZE_PAGE);
                firstIndex = Integer.parseInt(numPage) * SIZE_PAGE - SIZE_PAGE;

                // Don't search status
                if (status.equals("")) {
                    dao.searchByAdminNoStatus(searchContent.trim(), searchTitle.trim(), firstIndex, SIZE_PAGE);
                } else {
                    // search all info
                    dao.searchByAdmin(searchContent.trim(), searchTitle.trim(), status, firstIndex, SIZE_PAGE);
                }

                List<ArticleDTO> list = dao.getList();

                HttpSession session = request.getSession();
                session.setAttribute("NUMBER_OF_PAGE", numberOfPages);
                request.setAttribute("LIST", list);
                request.setAttribute("SIZE_OF_PAGE", SIZE_PAGE);

            } else {
                request.setAttribute("MESSAGE", "Search by content is requied");
            }
            url = SEARCH_PAGE;
        } catch (NumberFormatException e) {
            log("AdminSearchServlet_NumberFormatException " + e.getMessage());
        } catch (SQLException e) {
            log("AdminSearchServlet_SQLException " + e.getMessage());
        } catch (NamingException e) {
            log("AdminSearchServlet_NamingException " + e.getMessage());
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
