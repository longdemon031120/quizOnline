/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnv.exam.ExamDAO;
import longnv.examDetail.ExamDetailDAO;
import longnv.examDetailAnswer.ExamDetailAnswerDAO;
import longnv.examDetailAnswer.ExamDetailAnswerDTO;
import longnv.question.QuestionDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Asus
 */
@WebServlet(name = "DetailExamServlet", urlPatterns = {"/DetailExamServlet"})
public class DetailExamServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String examID = request.getParameter("examID");
            HttpSession session = request.getSession();
            session.removeAttribute("BACKHOME");
            ExamDAO daoExam = new ExamDAO();
            ExamDetailDAO daoDetail = new ExamDetailDAO();
            ExamDetailAnswerDAO daoExamAnswer = new ExamDetailAnswerDAO();
            ArrayList<QuestionDTO> listQuestion = daoDetail.getQuestionInExam(examID);
            Map<QuestionDTO, ArrayList<ExamDetailAnswerDTO>> answerAndQuestionComplete = new LinkedHashMap<>();
            int rate = daoExam.getRate(examID);
            ArrayList<ExamDetailAnswerDTO> listAnswerComplete = daoExamAnswer.getCompleteAnswer(examID, listQuestion);
            for (QuestionDTO questionDTO : listQuestion) {
                ArrayList<ExamDetailAnswerDTO> listAnswerForQuestion = new ArrayList<>();
                for (ExamDetailAnswerDTO examDetailAnswerDTO : listAnswerComplete) {
                    if(questionDTO.getQuestionID().equals(examDetailAnswerDTO.getQuestionID())){
                        listAnswerForQuestion.add(examDetailAnswerDTO);
                    }
                }
                answerAndQuestionComplete.put(questionDTO, listAnswerForQuestion);
            }
            session.setAttribute("COMPLETED_QUESTION", answerAndQuestionComplete);
            session.setAttribute("RATE", rate);
        } catch (SQLException | NamingException e) {
            LOGGER.error(e);
        }finally {
            response.sendRedirect("result");
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
