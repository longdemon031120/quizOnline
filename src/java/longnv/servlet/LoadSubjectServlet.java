/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnv.subject.SubjectDAO;
import longnv.subject.SubjectDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Asus
 */
@WebServlet(name = "LoadSubjectServlet", urlPatterns = {"/LoadSubjectServlet"})
public class LoadSubjectServlet extends HttpServlet {

    private final String HOME_PAGE = "home";
    private final String HOME_ADMIN_PAGE = "admin_home";
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = HOME_PAGE;
        try {
            HttpSession session = request.getSession();
            int role = (int) session.getAttribute("ROLEUSER");
            if(role == 1){
                url = HOME_ADMIN_PAGE;
                SubjectDAO dao = new SubjectDAO();
                ArrayList<SubjectDTO> listDTO = dao.getSubjectByAdmin();
                if(listDTO != null){
                    session.setAttribute("SUBJECTADMIN", listDTO);
                }
            } else {
                SubjectDAO dao = new SubjectDAO();
                ArrayList<SubjectDTO> listDTO = dao.getSubject();
                if(listDTO != null){
                    session.setAttribute("SUBJECT", listDTO);
                }
            }
            
        } catch(SQLException | NamingException e){
            LOGGER.error(e);
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
