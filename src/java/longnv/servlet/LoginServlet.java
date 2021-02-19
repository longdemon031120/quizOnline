/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.servlet;

import longnv.account.AccountDAO;
import longnv.account.AccountLoginError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Asus
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String LOAD_SUBJECT = "loadSubject";
    private final String LOGIN_JSP = "loginE";
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String url = LOGIN_JSP;
        try {
            // get username & password from loginPage.html
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String passSha256 = DigestUtils.sha256Hex(password);
            // call DAO
            AccountDAO dao = new AccountDAO();
            String result = dao.checkLogin(email, passSha256);
            if(result.equals("true")){
                
                url = LOAD_SUBJECT;
                String name = dao.getName(email);
                int role = dao.getRole(email);
                
                HttpSession session = request.getSession();
                session.setAttribute("NAMEUSER", name);
                session.setAttribute("EMAILUSER", email);
                session.setAttribute("ROLEUSER", role);
                
                Cookie cookie = new Cookie("USER", email+"-"+passSha256);                
                cookie.setMaxAge(60*5*3);
                response.addCookie(cookie);
                
            } else if (result.equals("half_true")){
                
                AccountLoginError error = new AccountLoginError();
                error.setPasswordNotValid("Wrong password");
                error.setUsernameValid(email);
                request.setAttribute("LOGINERROR", error);
            } else {
                
                AccountLoginError error = new AccountLoginError();
                error.setUsernameNotValid("Email does not exist");
                request.setAttribute("LOGINERROR", error);
            }
        } catch(NamingException | SQLException e){
            LOGGER.error(e);
        } finally {
            if(request.getAttribute("LOGINERROR") != null){
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                response.sendRedirect(url);
            }
            
            out.close();
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
