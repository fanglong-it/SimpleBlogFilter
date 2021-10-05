/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phuochg.account.AccountDAO;
import phuochg.account.AccountDTO;

/**
 *
 * @author cunpl
 */
public class ConfirmCodeServlet extends HttpServlet {

    private static final String LOGIN_PAGE = "loginPage";
    private static final String CONFIRM_PAGE = "confirmCodePage";

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
        String url = LOGIN_PAGE;

        ServletContext context = request.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITE_MAP");
        url = (String) siteMap.get(url);

        try {
            HttpSession session = request.getSession();
            String Code = (String) session.getAttribute("CODE");

            String email = request.getParameter("email");
            String subCode = request.getParameter("subCode");
            AccountDTO acc = new AccountDTO(email, "", "", "Active");
            AccountDAO accDao = new AccountDAO();
            String msg = "";
            if (Code.equals(subCode)) {
                accDao.updateAccountStatus(acc);
                url = (String) siteMap.get(LOGIN_PAGE);
            } else {
                msg = "The code not match! Check your mail carefully1";
                request.setAttribute("CONFIRM_MSG", msg);
                url = (String) siteMap.get(CONFIRM_PAGE);
            }

        } catch (Exception e) {
            log("Error at ConfirmCodeServlet:" + e.toString());
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
