/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
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
@WebServlet(name = "AddQuestionServlet", urlPatterns = {"/AddQuestionServlet"})
public class AddQuestionServlet extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    private boolean error = false;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String questionID = request.getParameter("txtQuestionID");
        String subjectID = request.getParameter("txtSubjectID");
        try {
            String radio = request.getParameter("chkAnswer");
            String corectAnswer = request.getParameter(radio);
            String answerA = request.getParameter("txtAnswerA");
            String answerB = request.getParameter("txtAnswerB");
            String answerC = request.getParameter("txtAnswerC");
            String answerD = request.getParameter("txtAnswerD");
            String[] groupAswer = {answerA, answerB, answerC, answerD};
            String questionTitle = request.getParameter("txtQuestionTitle");            
            QuestionDAO daoQues = new QuestionDAO();
            if(daoQues.addQuestion(questionID, questionTitle, subjectID)){
                ArrayList<AnswerDTO> listAnswer = new ArrayList<>();
                
                for (int i = 0; i < 4; i++) {
                    String answerID = UUID.randomUUID().toString();
                    AnswerDTO dto;
                    if(groupAswer[i].equals(corectAnswer)){
                        dto = new AnswerDTO( answerID, groupAswer[i], true, questionID);
                    }  else {
                        dto = new AnswerDTO( answerID, groupAswer[i], false, questionID);
                    }
                    listAnswer.add(dto);
                }
                
                AnswerDAO daoAnswer = new AnswerDAO();
                if(!daoAnswer.addAnswer(listAnswer)){
                    daoQues.removeQuestion(questionID);
                }
            }
            
        } catch (NamingException e) {
            LOGGER.error(e);
        } catch (SQLException e){
            String errorMess = e.getMessage();
            LOGGER.error(e);
            if(errorMess.contains("duplicate")){
                error = true;
                request.setAttribute("CREATEERROR", questionID + " is existed");
            }
        } finally {
            if(error){
                RequestDispatcher rd = request.getRequestDispatcher("countNumOfPage?txtSubjectID=" + subjectID);
                rd.forward(request, response);
            } else {
                response.sendRedirect("countNumOfPage?txtSubjectID=" + subjectID);
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
