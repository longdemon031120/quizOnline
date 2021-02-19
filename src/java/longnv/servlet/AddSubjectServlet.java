/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longnv.subject.SubjectDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author Asus
 */
@WebServlet(name = "AddSubjectServlet", urlPatterns = {"/AddSubjectServlet"})
public class AddSubjectServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    private boolean error = false;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String subID = request.getParameter("txtSubID");
        String subName = request.getParameter("txtSubName");
        try {
            
            SubjectDAO dao = new SubjectDAO();
            dao.addSubject(subID, subName);
        } catch (NamingException e) {
            LOGGER.error(e);
        } catch (SQLException e){
            String errorMess = e.getMessage();
            LOGGER.error(e);
            if(errorMess.contains("duplicate")){
                error = true;
                request.setAttribute("CREATEERROR", subID + " is existed");
            }
        } finally {
            if(error){
                RequestDispatcher rd = request.getRequestDispatcher("loadSubject");
                rd.forward(request, response);
            } else {
                response.sendRedirect("loadSubject");
            }
            
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
