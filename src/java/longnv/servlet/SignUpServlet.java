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
import longnv.account.AccountCreateError;
import longnv.account.AccountDAO;
import longnv.account.AccountDTO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;


/**
 *
 * @author Asus
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {
    
    private final String LOGIN_PAGE = "loginE";
    private String mess;
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = LOGIN_PAGE;
        
        String email = request.getParameter("txtEmail").trim();
        String name = request.getParameter("txtName").trim();
        String password = request.getParameter("txtPassword").trim();
        String confirm = request.getParameter("txtConfirmPass").trim();
        AccountCreateError errors = new AccountCreateError();
        
        try {
            boolean notify = false;
            if(email != null){
                if(!email.matches("^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$") || email.length() > 30){
                    notify = true;
                    errors.setEmailErr("Email: max length is 30, contain only one “@” character, do not contain special characters");
                }
            }
            if(name != null){
                if(!name.contains(" ")){
                    notify = true;
                    errors.setFullnameLengthErr("The name must contain 2 words");
                }
            }
            if(password != null || confirm != null){
                if(password.trim().length() < 3 || password.trim().length() > 20){
                    notify = true;
                    errors.setPasswordLengthErr("Password is required from 3 to 20 char");
                } else if (!password.trim().equals(confirm)) {
                    notify = true;
                    errors.setConfirmErr("Password was wrong");
                }
            }
            if(notify){
                request.setAttribute("CREATEERROR", errors);
            } else {
                AccountDAO dao = new AccountDAO();
                String passSha256 = DigestUtils.sha256Hex(password); 
                //System.out.println(passSha256);
                AccountDTO dto = new AccountDTO(email, name, passSha256, false, true);
                try {
                    Boolean result = dao.createAccount(dto);
                    if(result){
                        mess = "Sign up successfully";
                        request.setAttribute("CREATEERROR", errors);
                        url = "loginE?mess=" + mess;
//                        request.removeAttribute("");
                    }
                } catch (NamingException e) {
                    LOGGER.error(e);
                } catch (SQLException e){
                    String errorMess = e.getMessage();
                    LOGGER.error(e);
                    if(errorMess.contains("duplicate")){
                        errors.setEmailIsExisted(email + " is existed");
                        request.setAttribute("CREATEERROR", errors);
                    }
                }              
                
            }
        } finally {
            if(mess == null){
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                response.setStatus(307);
                response.setHeader("Location", url);
                //response.sendRedirect(url);
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
