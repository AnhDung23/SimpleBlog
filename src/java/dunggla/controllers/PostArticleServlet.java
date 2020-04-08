/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.controllers;

import dunggla.article.ArticleDAO;
import dunggla.article.ArticleError;
import dunggla.registration.RegistrationDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
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
public class PostArticleServlet extends HttpServlet {

    private final String ERROR_PAGE = "postArticle.jsp";
    private final String POST_PAGE = "postArticle.jsp";
    private final String LOGIN_PAGE = "login.jsp";

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
        String flag = request.getParameter("flag");

        ArticleError error = new ArticleError();
        boolean foundErr = false;
        try {
            
            // Logined
            if (flag == null || !flag.equals("loginToPostArticle")) {
                String title = request.getParameter("txtTitle");
                String shortDescription = request.getParameter("txtShortDescription");
                String content = request.getParameter("txtContent");

                HttpSession session = request.getSession(false);
                if (session != null) {
                    // Get user post article
                    RegistrationDTO dto = (RegistrationDTO) session.getAttribute("ACCOUNT_DTO");
                    if (dto != null) {
                        String author = dto.getEmail();

                        // Get current time
                        long millis = System.currentTimeMillis();
                        Timestamp date = new Timestamp(millis);
                        String status = "New";

                        if (title.trim().equals("") || title.trim().length() > 50) {
                            error.setTitleLengthErr("Title length must be from 1 to 50 chars");
                            foundErr = true;
                        }
                        if (shortDescription.trim().equals("") || shortDescription.trim().length() > 255) {
                            error.setShortDescriptionLengthErr("Short Description must be from 1 to 255 chars");
                            foundErr = true;
                        }
                        if (content.trim().equals("")) {
                            error.setContentFullFieldErr("Full fill content field");
                            foundErr = true;
                        }

                        if (foundErr) {
                            request.setAttribute("ERROR", error);
                        } else {
                            ArticleDAO dao = new ArticleDAO();
                            boolean check = dao.postArticle(title, shortDescription, content, author, date, status);
                            if (check) {
                                request.setAttribute("MESSAGE", "Please wait for the Admin's approval");
                                url = POST_PAGE;
                            }
                        }
                    } else {
                        request.setAttribute("ERROR", "You need login to execute this function");
                        url = "error.jsp";
                    }
                }
            } else {
                // Haven't login yet
                url = LOGIN_PAGE;
                request.setAttribute("FLAG", flag);
            }

        } catch (SQLException e) {
            log("PostArticleServlet_SQLException " + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                error.setTitleIsExisted("Title is existed");
                request.setAttribute("ERROR", error);
            }
        } catch (NamingException e) {
            log("PostArticleServlet_NamingException " + e.getMessage());
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
