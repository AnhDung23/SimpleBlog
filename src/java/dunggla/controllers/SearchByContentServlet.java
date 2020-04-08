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
public class SearchByContentServlet extends HttpServlet {

    private final int SIZE_OF_A_PAGE = 20;
    private final String SHOW_ALL_PAGE = "DispatchController?btAction=ShowAll";
    private final String SEARCH_PAGE = "homePage.jsp";

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
        int firstIndex = 0;
        int numOfRows = 0;
        String status = "Active";
        try {
            if (numPage == null) {
                numPage = "1";
            }
            String searchValue = request.getParameter("txtSearchValue");

            // Search fiel is not empty
            if (!searchValue.trim().equals("")) {
                ArticleDAO dao = new ArticleDAO();
                PagingTable paging = new PagingTable();
                
                // Compute num of page to paging 
                numOfRows = dao.getNumOfArticlesMemberSearch(searchValue.trim(), status);
                paging.setNumberOfRows(numOfRows);
                numberOfPages = paging.findNumberOfPages(SIZE_OF_A_PAGE);
                firstIndex = Integer.parseInt(numPage) * SIZE_OF_A_PAGE - SIZE_OF_A_PAGE;

                // Search by conntent
                dao.searchByContentMember(searchValue.trim(), firstIndex, SIZE_OF_A_PAGE, status);
                List<ArticleDTO> list = dao.getList();
                request.setAttribute("LIST", list);

                HttpSession session = request.getSession();
                session.setAttribute("NUMBER_OF_PAGE", numberOfPages);
                request.setAttribute("SIZE_OF_PAGE", SIZE_OF_A_PAGE);
            } else {
                request.setAttribute("MESSAGE", "Search by content is requied");
            }
            url = SEARCH_PAGE;
        } catch (NumberFormatException e) {
            log("SearchByContentServlet_NumberFormatException " + e.getMessage());
        } catch (NamingException e) {
            log("SearchByContentServlet_NamingException " + e.getMessage());
        } catch (SQLException e) {
            log("SearchByContentServlet_SQLException " + e.getMessage());
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
