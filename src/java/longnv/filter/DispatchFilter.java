/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnv.filter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Asus
 */
public class DispatchFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String filename = "/WEB-INF/indexPage.txt";
        String absolutePath = filterConfig.getServletContext().getRealPath(filename);
        HashMap<String, String> indexPage = new HashMap<>();
        FileReader f = null;                                             // read File 
        BufferedReader bf = null;                                        //
        try {                                                            //
            f = new FileReader(absolutePath);                            //
            bf = new BufferedReader(f);                                  //
            while (bf.ready()) {                                         //
                String nameAndValue = bf.readLine();                     //
                int indexOfEqualSign = nameAndValue.indexOf("=");             // get index of "="
                String name = nameAndValue.substring(0, indexOfEqualSign);    //get name
                String value = nameAndValue.substring(indexOfEqualSign + 1);  //get value
                indexPage.put(name, value);                                   // put it into HashMap
                ServletContext context = filterConfig.getServletContext();
                context.setAttribute("INDEXPAGE", indexPage);                 // put Hash Map into CONTEXT_SCOPE
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();               // get url form brower
        ServletContext context = request.getServletContext();   // go to CONTEXT_SCOPE
        HashMap<String, String> indexPage = (HashMap<String, String>) context.getAttribute("INDEXPAGE"); //get HashMap from CONTEXT_COPE
        try {
            int lastIndex = uri.lastIndexOf("/");             //get index of "/"
            String resource = uri.substring(lastIndex + 1);   // get resouce  
            String url;
            if(indexPage != null) {
               url = indexPage.get(resource);
              if(url!=null){
                  RequestDispatcher rd = req.getRequestDispatcher(url);
                  rd.forward(request, response);
              }else{                 
                  chain.doFilter(request, response);
              }
            }         
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        
    }
    
}