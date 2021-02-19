/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
public class AdminFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        HttpServletResponse res = (HttpServletResponse) response;
        int role =  (int) session.getAttribute("ROLEUSER");
        if(role != 1){
            res.sendRedirect("home");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        
    }
    
}