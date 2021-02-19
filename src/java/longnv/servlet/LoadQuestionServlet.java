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
import longnv.question.QuestionDAO;
import longnv.question.QuestionDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Asus
 */
@WebServlet(name = "LoadQuestionServlet", urlPatterns = {"/LoadQuestionServlet"})
public class LoadQuestionServlet extends HttpServlet {

    private final String LOAD_ANSWER = "loadAnswer";
    private final String QUIZ_PAGE = "quiz";
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("txtSubjectID");
        String url = QUIZ_PAGE;
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("ANSWER_AND_QUESTION");
//            if(session.getAttribute("ANSWER_AND_QUESTION") == null){
                QuestionDAO dao = new QuestionDAO();
                ArrayList<QuestionDTO> listQuestion = dao.getTenQuesttion(id);
                if(listQuestion != null){
                    if(listQuestion.size() == 10){
                        session.setAttribute("QUESTIONS", listQuestion);
                        session.setAttribute("SUBJECTID", id);
                        url = LOAD_ANSWER;
                    }
                    
                }
//            }
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
