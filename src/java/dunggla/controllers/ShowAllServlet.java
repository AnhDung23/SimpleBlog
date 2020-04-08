/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.controllers;

import dunggla.article.ArticleDAO;
import dunggla.article.ArticleDTO;
import dunggla.paging.PagingTable;
import dunggla.registration.RegistrationDTO;
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
public class ShowAllServlet extends HttpServlet {

    private final int PAGE_SIZE = 20;
    private final String MEMBER_PAGE = "homePage.jsp";
    private final String ADMIN_PAGE = "admin.jsp";

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
        String url = MEMBER_PAGE;
        String numPage = request.getParameter("txtPage");
        int numOfRows = 0;
        int numOfpages = 0;
        int firstIndex = 0;
        String status = "";

        try {
            if (numPage == null) {
                numPage = "1";
            }

            HttpSession session = request.getSession();
            RegistrationDTO dto = (RegistrationDTO) session.getAttribute("ACCOUNT_DTO");

            ArticleDAO dao = new ArticleDAO();
            // Member or default page
            if (dto == null || dto.getRole().equals("member")) {
                status = "Active";
                numOfRows = dao.getNumOfArticlesShowAllMember(status);
            } else {
                // Admin
                url = ADMIN_PAGE;
                numOfRows = dao.getNumOfArticlesShowAllAdmin();
            }

            PagingTable paging = new PagingTable();
            paging.setNumberOfRows(numOfRows);
            numOfpages = paging.findNumberOfPages(PAGE_SIZE);
            firstIndex = Integer.parseInt(numPage) * PAGE_SIZE - PAGE_SIZE;

            // Member or default page
            if (status.equals("Active")) {
                dao.showAllOfMember(firstIndex, PAGE_SIZE, status);
            } else {
                // Admin
                dao.showAllOfAdmin(firstIndex, PAGE_SIZE);
            }

            List<ArticleDTO> list = dao.getList();
            request.setAttribute("LIST", list);

            session.setAttribute("NUMBER_OF_PAGE", numOfpages);
            request.setAttribute("SIZE_OF_PAGE", PAGE_SIZE);
        } catch (NumberFormatException e) {
            log("ShowAllServlet_NumberFormatException " + e.getMessage());
        } catch (NamingException e) {
            log("ShowAllServlet_NamingException " + e.getMessage());
        } catch (SQLException e) {
            log("ShowAllServlet_SQLException " + e.getMessage());
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
