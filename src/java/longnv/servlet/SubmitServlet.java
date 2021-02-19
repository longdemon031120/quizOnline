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
import java.util.UUID;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnv.answer.AnswerDTO;
import longnv.exam.ExamDAO;
import longnv.examDetail.ExamDetailDAO;
import longnv.examDetail.ExamDetailDTO;
import longnv.examDetailAnswer.ExamDetailAnswerDAO;
import longnv.examDetailAnswer.ExamDetailAnswerDTO;
import longnv.question.QuestionDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Asus
 */
@WebServlet(name = "SubmitServlet", urlPatterns = {"/SubmitServlet"})
public class SubmitServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            ArrayList<String> listAnswerCorrectID = (ArrayList<String>) session.getAttribute("CORRECT_ANSWER");
            ArrayList<QuestionDTO> listQuestion = (ArrayList<QuestionDTO>) session.getAttribute("QUESTIONS");
            ArrayList<String> listAnswerChecked = new ArrayList<>();
            Map<QuestionDTO, ArrayList<AnswerDTO>> answerAndQuestion = (Map<QuestionDTO, ArrayList<AnswerDTO>>) session.getAttribute("ANSWER_AND_QUESTION");
            Map<QuestionDTO, ArrayList<ExamDetailAnswerDTO>> answerAndQuestionComplete = null;
            String subjectID = (String) session.getAttribute("SUBJECTID");
            if(listAnswerCorrectID != null && listQuestion != null && answerAndQuestion != null){
                int rate = 0;
                for (QuestionDTO question : listQuestion) {
                    if(request.getParameter(question.getQuestionID()) != null){
                        listAnswerChecked.add(request.getParameter(question.getQuestionID()));
                    }             
                }
                for (String answerCheck : listAnswerChecked) {
                    if(listAnswerCorrectID.contains(answerCheck)){
                        rate++;
                    }
                }

                String examID = UUID.randomUUID().toString();
                String accountID = (String) session.getAttribute("EMAILUSER");

                ExamDAO dao = new ExamDAO();
                boolean resultSaveExam = dao.saveExam(examID, accountID, subjectID, rate);

                int numOfAnswer = 1;
                if(resultSaveExam){
                    ArrayList examDetail = new ArrayList();
                    for (QuestionDTO questionDTO : answerAndQuestion.keySet()) { // duyet qua tat ca Question 
                        //for (String answerChecked : listAnswerChecked) {        // duyet qua cac answer da check
                            ArrayList<AnswerDTO> answerForQuestion = answerAndQuestion.get(questionDTO); // lay 4 answer cua quetion 
                            for (AnswerDTO answerDTO : answerForQuestion) {   //duyet qua cac answer trong 4 answer
                                if(listAnswerChecked.contains(answerDTO.getAnswerID()) && answerDTO.isAnswerCorrect()){ //if answer == answer da check va true
                                    ExamDetailDTO examDetailDTO = new ExamDetailDTO(examID, questionDTO.getQuestionID(), questionDTO.getQuestionTitle(), answerDTO.getAnswerID(), true); 
                                    examDetail.add(examDetailDTO);
                                    numOfAnswer = 1;
                                    break;
                                } else if (listAnswerChecked.contains(answerDTO.getAnswerID()) && !answerDTO.isAnswerCorrect()) { 
                                    ExamDetailDTO examDetailDTO = new ExamDetailDTO(examID, questionDTO.getQuestionID(), questionDTO.getQuestionTitle(), answerDTO.getAnswerID(), false);
                                    examDetail.add(examDetailDTO);
                                    numOfAnswer = 1;
                                    break;
                                } else {
                                    if(numOfAnswer == 4){
                                        ExamDetailDTO examDetailDTO = new ExamDetailDTO(examID, questionDTO.getQuestionID(), questionDTO.getQuestionTitle(), null, false);
                                        examDetail.add(examDetailDTO);
                                        numOfAnswer = 1;
                                        break;
                                    }
                                    
                                }
                                numOfAnswer++;
                            }
                       // } 
                    }

                    ExamDetailDAO daoDetail = new ExamDetailDAO();
                    if(daoDetail.saveDetail(examDetail)){
                        ExamDetailAnswerDAO daoExamAnswer = new ExamDetailAnswerDAO();
                        if(daoExamAnswer.saveExamDetaiAnswer(answerAndQuestion, listAnswerChecked, examID)){
                            answerAndQuestionComplete = new LinkedHashMap<>();
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
                        } else {
                            daoDetail.deleteExameDeatil(examID);
                            dao.deleteExame(examID);
                        }
                    } else {
                        dao.deleteExame(examID);
                    }
                }

                //request.setAttribute("CORRECT_ANSWER", session.getAttribute("CORRECT_ANSWER"));
                session.removeAttribute("CORRECT_ANSWER");
                session.setAttribute("COMPLETED_QUESTION", answerAndQuestionComplete);
                session.removeAttribute("ANSWER_AND_QUESTION");
                //request.setAttribute("LIST_ANSWER_CHECKED", listAnswerChecked);
                session.setAttribute("RATE", rate);
                session.setAttribute("BACKHOME", true);
            }    
        } catch(SQLException | NamingException e){
            LOGGER.error(e);
        } finally {
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
