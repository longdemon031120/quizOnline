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
import longnv.answer.AnswerDAO;
import longnv.answer.AnswerDTO;
import longnv.question.QuestionDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Asus
 */
@WebServlet(name = "LoadAnswerServlet", urlPatterns = {"/LoadAnswerServlet"})
public class LoadAnswerServlet extends HttpServlet {

    private final String QUIZ_PAGE = "quiz";
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            AnswerDAO dao = new AnswerDAO();
            HttpSession session = request.getSession();
            ArrayList<QuestionDTO> listQuestion = (ArrayList<QuestionDTO>) session.getAttribute("QUESTIONS");
            ArrayList<String> correctAnswerID = new ArrayList<>();
            if(listQuestion == null || listQuestion.isEmpty()){
                session.removeAttribute("ANSWER_AND_QUESTION");
            }
            else 
            {
                ArrayList<AnswerDTO> listAnswer = dao.getAnswer(listQuestion);
            
                Map<QuestionDTO, ArrayList<AnswerDTO>> answerAndQuestion = new LinkedHashMap<>();

                for (QuestionDTO questionDTO : listQuestion) {
                    ArrayList<AnswerDTO> listAnswerForQuestion = new ArrayList<>();
                    for (AnswerDTO answerDTO : listAnswer) {
                        if(questionDTO.getQuestionID().equals(answerDTO.getQuestionID())){
                            listAnswerForQuestion.add(answerDTO);
                        }
                    }
                    answerAndQuestion.put(questionDTO, listAnswerForQuestion);
                }
                for (AnswerDTO answerDTO : listAnswer) {
                    if(answerDTO.isAnswerCorrect()){
                        correctAnswerID.add(answerDTO.getAnswerID());
                    }
                }
                if(!answerAndQuestion.isEmpty()){
                    session.setAttribute("ANSWER_AND_QUESTION", answerAndQuestion);
                    session.setAttribute("CORRECT_ANSWER", correctAnswerID);
                } else {
                    session.removeAttribute("QUESTIONS");
                    session.removeAttribute("ANSWER_AND_QUESTION");
                }
                
            }
        } catch(SQLException | NamingException e){
            LOGGER.error(e);
        }finally {
            response.sendRedirect(QUIZ_PAGE);
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
