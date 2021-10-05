/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.servlet;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phuochg.article.ArticleDAO;
import phuochg.article.ArticleDTO;

/**
 *
 * @author cunpl
 */
public class PostArticleServlet extends HttpServlet {

    private static final String HOME_PAGE_USER = "";
    private static final String POST_ARTICLE = "postArticle";

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
        String url = HOME_PAGE_USER;

        ServletContext context = request.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITE_MAP");
        url = (String) siteMap.get(url);

        try {
            String titleName = request.getParameter("titleName");
            String email = request.getParameter("email");
            String contentId = request.getParameter("contentId");
            String description = request.getParameter("description");
            ArticleDTO article = new ArticleDTO(titleName, description, contentId, email, "", "New");
            ArticleDAO articleDao = new ArticleDAO();
            String msg = "";
            if (articleDao.insertArticle(article)) {
                msg = "Post Success You can view process at View Request";
                url = (String) siteMap.get(HOME_PAGE_USER);
            } else {
                msg = "Post Fail try again";
                url = (String) siteMap.get(POST_ARTICLE);
            }
            request.setAttribute("SEARCH_MSG", msg);
        } catch (Exception e) {
            log("Error at PostArticleServlet:" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
