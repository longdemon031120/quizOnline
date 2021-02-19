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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longnv.answer.AnswerDAO;
import longnv.answer.AnswerDTO;
import longnv.question.QuestionDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author Asus
 */
@WebServlet(name = "SaveUpdateServlet", urlPatterns = {"/SaveUpdateServlet"})
public class SaveUpdateServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String questionID = request.getParameter("txtQuestionID");
        String subjectID = request.getParameter("txtSubjectID");
        try {
            String radio = request.getParameter("chkAnswer");
            String correctAnswer = request.getParameter(radio);
            ArrayList<AnswerDTO> listAnswer = new ArrayList<>();
            for (int i = 1; i <= 4; i++) {
                String answerID = request.getParameter("txtAnswer" + i);
                String answer = request.getParameter(answerID);
                if(answer.equals(correctAnswer)){
                    listAnswer.add(new AnswerDTO(answerID, answer, true));
                } else {
                    listAnswer.add(new AnswerDTO(answerID, answer, false));
                }
                
            }
            String questionTitle = request.getParameter("txtQuestionTitle");            
            QuestionDAO daoQues = new QuestionDAO();
            if(daoQues.updateQuestion(questionID, questionTitle)){                
                AnswerDAO daoAnswer = new AnswerDAO();
                daoAnswer.updateAnswer(listAnswer);
            }
            
        } catch (NamingException e) {
            LOGGER.error(e);
        } catch (SQLException e){
            String errorMess = e.getMessage();
            LOGGER.error(e);
            if(errorMess.contains("duplicate")){
                request.setAttribute("CREATEERROR", questionID + " is existed");
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher("countNumOfPage?txtSubjectID=" + subjectID);
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
