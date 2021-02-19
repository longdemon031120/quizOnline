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
import javax.servlet.http.HttpSession;
import longnv.question.QuestionDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author Asus
 */
@WebServlet(name = "SearchQuestionServlet", urlPatterns = {"/SearchQuestionServlet"})
public class SearchQuestionServlet extends HttpServlet {

    private final String HOME_ADMIN_PAGE = "admin_home";
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        
        // Servlet nay dung de count num of page with searchValue
        String url = HOME_ADMIN_PAGE;       
        
        try {
            HttpSession session = request.getSession();
            String searchValue = request.getParameter("txtSearchValue");
            String subjectID = request.getParameter("txtSubjectID");
            if(searchValue != null && !searchValue.equals("")){
                QuestionDAO dao = new QuestionDAO();
                
                int numOfPage;
                numOfPage = dao.getNumOfPageWithSearch(subjectID, searchValue);
                
                if(numOfPage > 0){
                    session.setAttribute("NUMOFPAGE", numOfPage);
                
                    url = "loadQuestionWithSearch?page=1&txtSubjectID=" + subjectID + "&txtSearchValue=" + searchValue;
                }  
            } else {
                url = "countNumOfPage?txtSubjectID=" + subjectID;
            }
        } catch(SQLException | NamingException e){
            LOGGER.error(e);
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
