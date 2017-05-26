/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

/**
 *
 * @author Kva
 */
import KQT.FRequestKQT;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DeclineFRequestServlet", urlPatterns = ("/decline_frequest"))
public class DeclineFRequestServlet extends HttpServlet{
    @EJB
    private FRequestKQT frequestKQT;
    
    protected void processRequest(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException{
        hsrsp.setContentType("text/html;Charset=UTF-8");
        HttpSession sess = hsrqst.getSession(true);
        Integer personId = (Integer)sess.getAttribute("personId");
        PrintWriter out = hsrsp.getWriter();
        try{
            Integer sourceId = Integer.parseInt(hsrqst.getParameter("sourceId"));
            if(frequestKQT.declineFRequest(sourceId, personId)){
                out.println("declined");
            }else{
                out.println("failed");
            }
        }finally{
            out.close();
        }
    }
    
    protected void deGet(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException{
        processRequest(hsrqst, hsrsp);
    }
    
    @Override
    protected void doPost(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException{
        processRequest(hsrqst, hsrsp);
    }
    
    public String getSrevletInfo(){
        return "short description";
    }
}
